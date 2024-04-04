package com.yonyk.PlanWithJ.jwt;

import java.io.IOException;

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
	
	private final JwtUtil jp;
	private final PrincipalDetailsService pds;
	
    private static final String CHARACTER_ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "application/json";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String servletPath = request.getServletPath();
		
		// /users/sing-in 으로 요청이 들어온 경우 다음 필터로 넘김
        if(servletPath.contains("/users/sing-in")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        final String jwtToken;
        final String username;
        
        
        
	}
	
	

}
