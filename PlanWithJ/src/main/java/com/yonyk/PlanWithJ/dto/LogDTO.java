package com.yonyk.PlanWithJ.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogDTO {
	private SummaryDTO summary;
	private List<ActivityDTO> activityList;
}
