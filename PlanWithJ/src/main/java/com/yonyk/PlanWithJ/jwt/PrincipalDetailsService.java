package com.yonyk.PlanWithJ.jwt;

import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyk.PlanWithJ.dto.User;
import com.yonyk.PlanWithJ.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("해당하는 아이디의 회원이 없습니다."));
		Hibernate.initialize(user.getUser_roles());
		return new PrincipalDetails(user);
	}

}
