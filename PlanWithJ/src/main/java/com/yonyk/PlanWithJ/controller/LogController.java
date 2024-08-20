package com.yonyk.PlanWithJ.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yonyk.PlanWithJ.dto.SummaryDTO;
import com.yonyk.PlanWithJ.dto.ActivityDTO;
import com.yonyk.PlanWithJ.dto.LogDTO;
import com.yonyk.PlanWithJ.entity.Summary;
import com.yonyk.PlanWithJ.entity.User;
import com.yonyk.PlanWithJ.entity.Activity;
import com.yonyk.PlanWithJ.jwt.PrincipalDetails;
import com.yonyk.PlanWithJ.repo.ActivityRepository;
import com.yonyk.PlanWithJ.repo.SummaryRepository;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/log")
public class LogController {
	/*
	 * 여행 목록
	 * 일정 저장
	 * 일정 수정
	 * 일정 삭제
	 */

	@Autowired
	private SummaryRepository summaryRepository;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	//summary 목록
	@GetMapping("/summaryList")
	public ResponseEntity<List<SummaryDTO>> getSummaryList(@AuthenticationPrincipal PrincipalDetails user) {
		
		String user_id = user.getUser().getUser_id();
		List<Summary> summaryList = summaryRepository.findByUser_Id(user_id);
		
		List<SummaryDTO> summaryDTOList = summaryList.stream()
				.map(SummaryDTO::new)
				.collect(Collectors.toList());
		
		if(summaryDTOList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(summaryDTOList,HttpStatus.OK);
		}
	}
	
	//activity 목록
	@GetMapping("/activityList/{snum}")
	public ResponseEntity<List<ActivityDTO>> getActivityList(@PathVariable("snum") int snum) {
		
		Summary summary = summaryRepository.findById(snum).get();
		List<Activity> activityList = summary.getActivityList();
		
		List<ActivityDTO> activityDTOList = activityList.stream()
				.map(ActivityDTO::new)
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(activityDTOList,HttpStatus.OK);
	}
	
	//일정 추가
	@PostMapping("/addLog")
	public ResponseEntity<String> addLog(@AuthenticationPrincipal PrincipalDetails user,
			@RequestBody LogDTO log) {
		
		User saveUser = user.getUser();
		Summary summary = log.getSummary().toSummary(saveUser, 0);
		
		Summary savedSummary = summaryRepository.save(summary);
		
		List<Activity> activities = log.getActivityList().stream()
				.map(ActivityDTO -> ActivityDTO.toActivity(savedSummary))
				.collect(Collectors.toList());
		
		
		activityRepository.saveAll(activities);
		
		return ResponseEntity.ok("로그 추가 완료");
	}
	
		
	//수정할 여행/일정목록
	@GetMapping("/editLog/{snum}")
	public ResponseEntity<LogDTO> getLog(@PathVariable("snum") int snum) {
		Summary summary = summaryRepository.findById(snum).get();
		SummaryDTO summaryDTO = new SummaryDTO(summary);
		
		List<Activity> activityList = summary.getActivityList();
		List<ActivityDTO> activityDTOList = activityList.stream()
				.map(ActivityDTO::new)
				.collect(Collectors.toList());
		
		LogDTO logDTO = LogDTO.builder()
				.summary(summaryDTO)
				.activityList(activityDTOList)
				.build();
		return new ResponseEntity<>(logDTO, HttpStatus.OK);
	}
	
	//수정
	@PostMapping("/editLog")
	public ResponseEntity<String> editLog(@AuthenticationPrincipal PrincipalDetails user,
			@RequestBody LogDTO log) {
		
		User saveUser = user.getUser();
		
		SummaryDTO summaryDTO = log.getSummary();
		List<ActivityDTO> activityDTOList = log.getActivityList();
		
		Summary summary = summaryDTO.toSummary(saveUser, 0);
		Summary savedSummary = summaryRepository.save(summary);
		
		List<Activity> activities = activityDTOList.stream()
				.map(ActivityDTO -> ActivityDTO.toActivity(savedSummary))
				.collect(Collectors.toList());
		activityRepository.saveAll(activities);
		
		return ResponseEntity.ok("수정 완료");
	}
	
	
	

}
