package com.yonyk.PlanWithJ.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "activity")
@Entity
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int anum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="snum")
	private Summary summary;
	
	private LocalTime required_time;
	private LocalDateTime start_time;
	private LocalDateTime end_time;
	private String type;
	private long cost;
	private String title;
	private String todo;
	private int position;

}
