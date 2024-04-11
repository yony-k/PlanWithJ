package com.yonyk.PlanWithJ.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@PostMapping("sign-up")
	public String postMethodName() {
		System.out.println("들어옴");
		return "회원가입시도";
	}
	

}
