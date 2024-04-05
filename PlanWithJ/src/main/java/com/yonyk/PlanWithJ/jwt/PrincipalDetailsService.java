package com.yonyk.PlanWithJ.jwt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yonyk.PlanWithJ.dto.User;
import com.yonyk.PlanWithJ.repo.UserRepository;

public class PrincipalDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("해당하는 아이디의 회원이 없습니다."));
		
		return new PrincipalDetails(user);
	}

}
