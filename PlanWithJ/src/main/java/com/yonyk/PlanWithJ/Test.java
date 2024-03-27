package com.yonyk.PlanWithJ;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "TEST_USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Test {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="userid")
	private int userid;
	
	@Column(name="name")
	private String name;
}
