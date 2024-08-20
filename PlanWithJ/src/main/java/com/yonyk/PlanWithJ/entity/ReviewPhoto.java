package com.yonyk.PlanWithJ.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "review_pic")
@Entity
public class ReviewPhoto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rvpnum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="rvnum")
	private Review review;
	
	private String path;
}
