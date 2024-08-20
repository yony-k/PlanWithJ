package com.yonyk.PlanWithJ.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.yonyk.PlanWithJ.entity.Activity;
import com.yonyk.PlanWithJ.entity.Summary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ActivityDTO {
	
	private int anum;
	private int snum;
	private LocalTime required_time;
	private LocalDateTime start_time;
	private LocalDateTime end_time;
	private String type;
	private long cost;
	private String title;
	private String todo;
	private int position;
	
	public ActivityDTO(Activity activity) {
		this.anum = activity.getAnum();
		this.snum = activity.getSummary().getSnum();
		this.required_time = activity.getRequired_time();
		this.start_time = activity.getStart_time();
		this.end_time = activity.getEnd_time();
		this.type = activity.getType();
		this.cost = activity.getCost();
		this.title = activity.getTitle();
		this.todo = activity.getTodo();
		this.position = activity.getPosition();
	}
	
	public Activity toActivity(Summary summary) {
		return Activity.builder()
		.anum(this.anum)
		.summary(summary)
		.required_time(this.required_time)
		.start_time(this.start_time)
		.end_time(this.end_time)
		.type(this.type)
		.cost(this.cost)
		.title(this.title)
		.todo(this.todo)
		.position(this.position)
		.build();
	};

}
