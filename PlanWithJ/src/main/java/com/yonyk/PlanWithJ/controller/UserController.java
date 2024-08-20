package com.yonyk.PlanWithJ.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yonyk.PlanWithJ.dto.RegistDTO;
import com.yonyk.PlanWithJ.entity.User;
import com.yonyk.PlanWithJ.jwt.JwtDTO;
import com.yonyk.PlanWithJ.jwt.JwtUtil;
import com.yonyk.PlanWithJ.jwt.PrincipalDetails;
import com.yonyk.PlanWithJ.svc.UserSVC;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserSVC usvc; 
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/sign-up")
	public ResponseEntity<String> postMethodName(@RequestBody RegistDTO regist) {
		User user = usvc.regist(regist);
		if(user.getUser_id()!=null) {
			return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("회원가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/refresh_token")
	public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> refresh_Token) {
		String token = refresh_Token.get("token");
		if(jwtUtil.validateToken(token)) {
			String user_id = jwtUtil.getUsernameFromToken(token);
			UserDetails userDetails = usvc.findByID(user_id);
			JwtDTO jwtDTO = jwtUtil.getNewToken(userDetails);
			
			return ResponseEntity.ok(jwtDTO);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");
		}
	}
	
}
