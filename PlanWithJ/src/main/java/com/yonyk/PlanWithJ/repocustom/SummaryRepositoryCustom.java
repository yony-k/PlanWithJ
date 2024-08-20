package com.yonyk.PlanWithJ.repocustom;

import java.util.List;

import com.yonyk.PlanWithJ.entity.Summary;

public interface SummaryRepositoryCustom {
	List<Summary> findByUser_Id(String user_id);
}
