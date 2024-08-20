package com.yonyk.PlanWithJ.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "summary")
@Entity
public class Summary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int snum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	private String companion;
	private LocalDateTime start_date;
	private LocalDateTime end_date;
	private String title;
	private String place;
	private String transportation;
	private long budget;
	private int draft;
	private int reviewd;
	
	@Builder.Default
	@OneToMany(mappedBy = "summary")
	private List<Activity> activityList = new ArrayList();
	
}
