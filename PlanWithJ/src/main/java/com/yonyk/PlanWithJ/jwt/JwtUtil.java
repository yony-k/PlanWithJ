package com.yonyk.PlanWithJ.jwt;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yonyk.PlanWithJ.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtUtil {
	public static final String AccessToken = "AccessToken"; 
	public static final String RefreshToken = "RefreshToken";
	public static final String BEARER_PREFIX = "Bearer "; 
	private final Key key;
	
	
	@Autowired
	private PrincipalDetailsService principalDetailsService;
	
	public JwtUtil(@Value("${spring.security.jwt.secret}") String secretKey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
    public JwtDTO getLoginToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        String user_id = authentication.getName();
        String nickName = ((PrincipalDetails)authentication.getPrincipal())
        		.getUser().getName();
        
        return generateJwtDTO(authorities, user_id, nickName);
    }
    
    // 리퀘스트에서 토큰 빼내는 메소드
    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
    
    // 토큰 검사 메소드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }
    
    // 토큰에서 정보 빼내는 메소드
    public Authentication getAuthentication(String token) {
    	Claims userinfo = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    	
    	UserDetails userDetails = principalDetailsService.loadUserByUsername(userinfo.getSubject());
    	return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
    
    // 토큰에서 아이디 빼내는 메소드
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    
    // 리프레시 토큰 요청 시 새로운 액세스 토큰 만드는 메소드
    public JwtDTO getNewToken(UserDetails userDetails) {
    	String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    	String user_id = userDetails.getUsername();
    	String nickName = ((PrincipalDetails)userDetails).getUser().getName();
   
    	return generateJwtDTO(authorities, user_id, nickName);
    }
    
    // jwtDTO 생성 메소드
    public JwtDTO generateJwtDTO(String authorities, String user_id, String nickName) {
    	
    	long now = (new Date()).getTime();

        Date accessTokenExpiresIn = new Date(now + 86400000);
        String accessToken = Jwts.builder()
                .setSubject(user_id)
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
        		.setSubject(user_id)
                .setExpiration(new Date(now + 2592000000L))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtDTO.builder()
                .accessToken(JwtUtil.BEARER_PREFIX + accessToken)
                .refreshToken(refreshToken)
                .nickName(nickName)
                .build();
    }
    
    
}
