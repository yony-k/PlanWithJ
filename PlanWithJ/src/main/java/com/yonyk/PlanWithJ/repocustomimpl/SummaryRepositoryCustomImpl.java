package com.yonyk.PlanWithJ.repocustomimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yonyk.PlanWithJ.entity.QSummary;
import com.yonyk.PlanWithJ.entity.Summary;
import com.yonyk.PlanWithJ.repocustom.SummaryRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class SummaryRepositoryCustomImpl implements SummaryRepositoryCustom{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Summary> findByUser_Id(String user_id) {
		
		JPAQueryFactory factory = new JPAQueryFactory(entityManager);
		QSummary summary = QSummary.summary;
		
		return factory.selectFrom(summary)
				.where(summary.user.user_id.eq(user_id))
				.fetch();
	}
	
}
