package com.yonyk.PlanWithJ.jwt;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
	
	private final JwtUtil jwtUtil;
	private final PrincipalDetailsService principalDetailsService;
	
    private static final String CHARACTER_ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "application/json";
	
    @Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    	String path = request.getRequestURI();
    	
    	return path.equals("/user/refresh_token");
	}
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = jwtUtil.getJwtFromHeader(request);
		
		if(token!=null) {
			if(!jwtUtil.validateToken(token)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("Invalid or missing JWT token");
				return;
			} else {
				Authentication authentication = jwtUtil.getAuthentication(token);
		        SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}

}
