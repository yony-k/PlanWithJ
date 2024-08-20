package com.yonyk.PlanWithJ.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Table(name = "user_data")
@Entity
public class User {
	
	@Id
	private String user_id;
	
	private String name;
	private String pwd;
	private String email;
	
	@Transient
	private String confirmPwd;
	
	@Builder.Default
	@OneToMany(mappedBy = "user")
	private List<User_Role> user_roles = new ArrayList();
}
