package com.yonyk.PlanWithJ.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yonyk.PlanWithJ.entity.Summary;
import com.yonyk.PlanWithJ.repocustom.SummaryRepositoryCustom;

public interface SummaryRepository extends JpaRepository<Summary, Integer>, SummaryRepositoryCustom{

}
