package com.yonyk.PlanWithJ.dto;

import java.time.LocalDateTime;

import com.yonyk.PlanWithJ.entity.Summary;
import com.yonyk.PlanWithJ.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class SummaryDTO {
	
	private int snum;
	private String user_id;
	private String companion;
	private LocalDateTime start_date;
	private LocalDateTime end_date;
	private String title;
	private String place;
	private String transportation;
	private long budget;
	private int draft;
	private int reviewd;
	
	public SummaryDTO(Summary summary) {
		this.snum = summary.getSnum();
		this.user_id = summary.getUser().getUser_id();
		this.companion = summary.getCompanion();
		this.start_date = summary.getStart_date();
		this.end_date = summary.getEnd_date();
		this.title = summary.getTitle();
		this.place = summary.getPlace();
		this.transportation = summary.getTransportation();
		this.budget = summary.getBudget();
		this.draft = summary.getDraft();
		this.reviewd = summary.getReviewd();
	}
	
	public Summary toSummary(User user,int draft) {
		return Summary.builder()
		.user(user)
		.snum(this.snum)
		.companion(this.companion)
		.start_date(this.start_date)
		.end_date(this.end_date)
		.title(this.title)
		.place(this.place)
		.transportation(this.transportation)
		.budget(this.budget)
		.draft(draft)
		.build();
	}
}
