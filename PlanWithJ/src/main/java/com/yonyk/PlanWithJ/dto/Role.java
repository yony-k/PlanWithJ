package com.yonyk.PlanWithJ.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "role")
@SequenceGenerator(
        name="Role_SEQ_GENERATOR",
        sequenceName = "Role_SEQ",
        initialValue = 5, allocationSize = 50
)
@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Role_SEQ_GENERATOR")
	private int id;
	
	@Column
	private String rname;
}
