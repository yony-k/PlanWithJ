package com.yonyk.PlanWithJ;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestRepository tr;
	
	@Autowired
	private TestQueryDSLRepository tqr;
	
	@GetMapping("/test")
	public String test() {
		List<Test> list = tqr.findById(2);
		
		return list.get(0).toString();
	}
}
