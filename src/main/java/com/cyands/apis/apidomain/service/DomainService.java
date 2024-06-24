package com.cyands.apis.apidomain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyands.apis.apidomain.entity.Domain;
import com.cyands.apis.apidomain.repository.DomainRepository;


@Service
public class DomainService {
	
	private final static Logger LOGGER=LoggerFactory.getLogger(DomainService.class);
	
	@Autowired
	private DomainRepository repo;
	
	@Autowired
	private StatisticsService statisticsService;

	public Domain getDomainByUrl(String url) {
		String domainname=null;
		try {
			domainname=getDomainnameFromUrl(url);
		} catch(Exception ex) {
			LOGGER.error(ex.getMessage()+": "+url);
			return null;
		}
		Domain domain=repo.findByDomainContaining(domainname);
		if(domain==null) {
			domain=new Domain();
			domain.setDomain(domainname);
		}
		if(!statisticsService.doStatistics(domain)) {
			LOGGER.error("Statistics are not updated for "+domain.getDomain()+"!");
		}
		return domain;
	}
	
	private String getDomainnameFromUrl(final String url) throws Exception {
		String domainname=url;
		if(domainname==null) {
			throw new Exception("No URL provided.");
		}
		if(!domainname.startsWith("http://")&&!domainname.startsWith("https://")) {
			throw new Exception("No protocol provided.");
		} else if(domainname.startsWith("http://")) {
			domainname=domainname.substring(7);
		} else if(domainname.startsWith("https://")) {
			domainname=domainname.substring(8);
		}
		int pos=domainname.indexOf("/");
		if(pos==-1) {
			throw new Exception("Malformed URL");
		} else {
			domainname=domainname.substring(0,pos);
		}
		return domainname;
	}
}
