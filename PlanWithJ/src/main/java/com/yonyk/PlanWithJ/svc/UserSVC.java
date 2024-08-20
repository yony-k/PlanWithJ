package com.yonyk.PlanWithJ.svc;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yonyk.PlanWithJ.dto.RegistDTO;
import com.yonyk.PlanWithJ.entity.Role;
import com.yonyk.PlanWithJ.entity.User;
import com.yonyk.PlanWithJ.entity.User_Role;
import com.yonyk.PlanWithJ.jwt.PrincipalDetails;
import com.yonyk.PlanWithJ.repo.RoleRepository;
import com.yonyk.PlanWithJ.repo.UserRepository;

@Service
public class UserSVC {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder enc;
	
	public UserDetails findByID(String user_id) {
		User user = userRepository.findById(user_id).get();
		return new PrincipalDetails(user);
	}
	
	public User regist(RegistDTO regist) {
		
		User user = User.builder()
				.user_id(regist.getUser_id())
				.pwd(enc.encode(regist.getPwd()))
				.email(regist.getEmail())
				.name(regist.getName())
				.user_roles(new ArrayList())
				.build();
		
		Role role = roleRepository.findById("user").get();
		
		User_Role user_role = User_Role.builder()
				.user(user)
				.role(role)
				.build();
		
		user.getUser_roles().add(user_role);
		
		return userRepository.save(user);
	};
	
}
