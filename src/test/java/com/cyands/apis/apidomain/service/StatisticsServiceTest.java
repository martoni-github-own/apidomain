package com.cyands.apis.apidomain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cyands.apis.apidomain.entity.Domain;
import com.cyands.apis.apidomain.entity.Statistics;
import com.cyands.apis.apidomain.repository.StatisticsRepository;
import com.cyands.apis.apidomain.type.Category;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest {

	@Mock
	private StatisticsRepository statisticsRepository;
	
	@Mock
	private DomainService domainService;
	
	@InjectMocks
	private StatisticsService statisticsService;
	
	private Statistics statistics1;
	private Domain domain1;
	private Domain domain2;
	private Domain domain3;
	
	@BeforeEach
	void init() {
		statistics1=new Statistics();
		statistics1.setCountOfBlockedMalwareAndFishing(0L);
		statistics1.setCountOfBlockedNotMalwareAndFishing(0L);
		domain1=new Domain();
		domain1.setDomain("www.instagram.com");
		domain1.setCategory(Category.SOCIAL_MEDIA);
		domain1.setBlocked(true);
		domain2=new Domain();
		domain2.setDomain("phishing.badinternetdomain.com");
		domain2.setCategory(Category.MALWARE_AND_PHISHING);
		domain2.setBlocked(true);
		domain3=new Domain();
		domain3.setDomain("en.wikipedia.org");
		domain3.setCategory(Category.UNKNOWN);
		domain3.setBlocked(false);
	}
	
	@Test
	void doStatistics() {
		//given
		given(statisticsRepository.findById(anyLong())).willReturn(Optional.of(statistics1));
		//when
		boolean result=statisticsService.doStatistics(domain1);
		//then
		assertThat(result).isEqualTo(true);
		assertThat(statistics1.getCountOfBlockedNotMalwareAndFishing()).isEqualTo(1L);
		
		//when
		result=statisticsService.doStatistics(domain2);
		//then
		assertThat(result).isEqualTo(true);
		assertThat(statistics1.getCountOfBlockedMalwareAndFishing()).isEqualTo(1L);
		
		//when
		result=statisticsService.doStatistics(domain3);
		//then
		assertThat(result).isEqualTo(true);
		assertThat(statistics1.getCountOfBlockedMalwareAndFishing()).isEqualTo(1L);
		assertThat(statistics1.getCountOfBlockedNotMalwareAndFishing()).isEqualTo(1L);
	}
}
