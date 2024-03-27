package com.yonyk.PlanWithJ;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TestQueryDSLRepository {

	private final JPAQueryFactory factory;

	public List<Test> findById(int id) {
		QTest test = QTest.test;
		return factory.selectFrom(test).where(test.id.eq(id)).fetch();
	}

}
