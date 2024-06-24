package com.cyands.apis.apidomain.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyands.apis.apidomain.entity.Domain;
import com.cyands.apis.apidomain.entity.Statistics;
import com.cyands.apis.apidomain.repository.StatisticsRepository;
import com.cyands.apis.apidomain.type.Category;
import com.cyands.apis.apidomain.type.StatisticsBlockedKind;

import jakarta.transaction.Transactional;

@Service
public class StatisticsService {
	
	private final static Logger LOGGER=LoggerFactory.getLogger(StatisticsService.class);
	
	@Autowired
	private StatisticsRepository repo;
	
	public Statistics getStatistics() {
		Optional<Statistics> optional = repo.findById(1L);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			LOGGER.error("Statistics not found!");
			return null;
		}
	}
	
	public boolean doStatistics(Domain domain) {
		if(domain.isBlocked()) {
			boolean result=false;
			if(domain.getCategory()==Category.MALWARE_AND_PHISHING) {
				result=incrementBlockedMalwareAndFishing(StatisticsBlockedKind.BLOCKED_MALWARE_AND_FISHING);
			} else {
				result=incrementBlockedMalwareAndFishing(StatisticsBlockedKind.BLOCKED_NOT_MALWARE_AND_FISHING);
			}
			return result;
		}
		return true;
		
	}

	@Transactional
	public boolean incrementBlockedMalwareAndFishing(StatisticsBlockedKind kind) {
		Statistics statistics = getStatistics();
		if(statistics==null) {
			return false;
		}
		if(kind==StatisticsBlockedKind.BLOCKED_MALWARE_AND_FISHING) {
			statistics.setCountOfBlockedMalwareAndFishing(statistics.getCountOfBlockedMalwareAndFishing()+1);
		} else if(kind==StatisticsBlockedKind.BLOCKED_NOT_MALWARE_AND_FISHING) {
			statistics.setCountOfBlockedNotMalwareAndFishing(statistics.getCountOfBlockedNotMalwareAndFishing()+1);
		}
		repo.saveAndFlush(statistics);
		return true;
	}
	
}
