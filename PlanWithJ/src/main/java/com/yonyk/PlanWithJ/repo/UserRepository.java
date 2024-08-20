package com.yonyk.PlanWithJ.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.yonyk.PlanWithJ.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	
}
