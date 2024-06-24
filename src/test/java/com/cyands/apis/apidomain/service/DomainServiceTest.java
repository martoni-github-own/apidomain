package com.cyands.apis.apidomain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cyands.apis.apidomain.entity.Domain;
import com.cyands.apis.apidomain.repository.DomainRepository;
import com.cyands.apis.apidomain.repository.StatisticsRepository;
import com.cyands.apis.apidomain.type.Category;

@ExtendWith(MockitoExtension.class)
public class DomainServiceTest {
	
	@Mock
	private DomainRepository domainRepository;
	
	@Mock
	private StatisticsRepository statisticsRepository;
	
	@Mock
	private StatisticsService statisticsService;
	
	@InjectMocks
	private DomainService domainService;
	
	private Domain domain1;
	private Domain domain2;
	
	@BeforeEach
	void init() {
		domain1=new Domain();
		domain1.setDomain("www.instagram.com");
		domain1.setCategory(Category.SOCIAL_MEDIA);
		domain1.setBlocked(true);
		
		domain2=new Domain();
		domain2.setDomain("en.wikipedia.org");
		domain2.setCategory(Category.UNKNOWN);
		domain2.setBlocked(false);
	}
	
	@Test
	void getDomainByUrl1() {
		given(domainRepository.findByDomainContaining("www.instagram.com")).willReturn(domain1);
		//given  
		String url1="https://www.instagram.com/cyandigitalsecurity";
		//when
		Domain domain=domainService.getDomainByUrl(url1);
		//then
		assertThat(domain).isEqualTo(domain1);
	}
	
	@Test
	void getDomainByUrl2() {
		given(domainRepository.findByDomainContaining("en.wikipedia.org")).willReturn(domain2);
		//given 
		String url2="https://en.wikipedia.org/wiki/Alan_Turing";
		//when
		Domain domain=domainService.getDomainByUrl(url2);
		//then
		assertThat(domain).isEqualTo(domain2);
	}
	
}
