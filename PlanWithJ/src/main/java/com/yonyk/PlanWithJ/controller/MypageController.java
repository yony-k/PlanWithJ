package com.yonyk.PlanWithJ.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/mypage")
public class MypageController {

	@GetMapping("/list")
	public String getMethodName(@RequestParam String param) {
		return new String();
	}
	
}
