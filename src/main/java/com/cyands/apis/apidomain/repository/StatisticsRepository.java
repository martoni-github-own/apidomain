package com.cyands.apis.apidomain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyands.apis.apidomain.entity.Statistics;
import com.cyands.apis.apidomain.type.StatisticsBlockedKind;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
	
}
