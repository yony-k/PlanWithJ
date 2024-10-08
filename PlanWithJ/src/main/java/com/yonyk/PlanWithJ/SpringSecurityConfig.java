package com.yonyk.PlanWithJ;

import java.util.Collections;

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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.yonyk.PlanWithJ.jwt.JwtAuthenticationFilter;
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
	
	private final JwtUtil jwtUtil;
	private final PrincipalDetailsService principalDetailsService;
	private final AuthenticationConfiguration authenticationConfiguration;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		System.out.println("smith12 :"+enc.encode("smith12"));
		return enc;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
	
	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilter() {
		return new JwtAuthorizationFilter(jwtUtil,principalDetailsService);
	}
	
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil);
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return filter;
    }

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (webSecurity) -> webSecurity.ignoring().requestMatchers("/resources/**", "/ignore2");
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.addExposedHeader("AccessToken");
            config.addExposedHeader("RefreshToken");
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:8899"));
            config.setAllowCredentials(true);
            return config;
        };
    }
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.csrf(csrfConf -> csrfConf.disable())
			.cors(c-> c.configurationSource(corsConfigurationSource()))
			.formLogin(loginConf -> loginConf.disable())
			.authorizeHttpRequests(authz -> authz
				.requestMatchers("/user/login").permitAll()
				.requestMatchers("/user/sign-up").permitAll()
				.anyRequest().authenticated());
		
		http.addFilterBefore(jwtAuthorizationFilter(), JwtAuthenticationFilter.class);
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
