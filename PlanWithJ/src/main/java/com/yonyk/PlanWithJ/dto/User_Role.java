package com.yonyk.PlanWithJ.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "user_role")
@Entity
public class User_Role {
	
	@Id
	private int urnum;
	
	@ManyToOne
	@JoinColumn(name= "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name= "role_id")
	private Role role;
}
