package com.yonyk.PlanWithJ.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonyk.PlanWithJ.dto.User;

public interface UserRepository extends JpaRepository<User, String>{
	
}
