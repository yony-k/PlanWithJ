package com.yonyk.PlanWithJ;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.yonyk.PlanWithJ.jwt.JwtAuthorizationFilter;
import com.yonyk.PlanWithJ.jwt.JwtUtil;
import com.yonyk.PlanWithJ.jwt.PrincipalDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {
	
	private final JwtUtil JwtUtil;
	private final PrincipalDetailsService pds;
	private final AuthenticationConfiguration authenticationConfiguration;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		return enc;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
	
	@Bean
	public JwtAuthorizationFilter jwtAuthenticationFilter() {
		JwtAuthorizationFilter filter = new JwtAuthorizationFilter(JwtUtil,pds);
		return null;
	}

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (webSecurity) -> webSecurity.ignoring().requestMatchers("/resources/**", "/ignore2");
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(authz -> authz
				.requestMatchers("/main").permitAll()
				.requestMatchers("/user/login").permitAll()
				.requestMatchers("/user/sing-in").permitAll()
				.anyRequest().authenticated())
			.csrf(csrfConf -> csrfConf.disable())
			.formLogin(loginConf -> loginConf.disable());
		
		return http.build();
	}
}
