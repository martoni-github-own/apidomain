package com.cyands.apis.apidomain.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyands.apis.apidomain.entity.Domain;
import com.cyands.apis.apidomain.entity.Statistics;
import com.cyands.apis.apidomain.model.DomainCheckerRequest;
import com.cyands.apis.apidomain.model.DomainCheckerResponse;
import com.cyands.apis.apidomain.model.StatisticsResponse;
import com.cyands.apis.apidomain.service.DomainService;
import com.cyands.apis.apidomain.service.StatisticsService;

@RestController
@RequestMapping(value = "/cyanapp/api")
public class DomainController {
	
	@Autowired
	private DomainService domainService;
	
	@Autowired
	private StatisticsService statisticsService;
	
	@PostMapping(value="/domain-checker", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> domainCheck(@RequestBody final DomainCheckerRequest request) {
		Domain domain=domainService.getDomainByUrl(request.getUrl());
		if(domain==null) {
			return new ResponseEntity<Object>("Invalid url: "+request.getUrl(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(new DomainCheckerResponse(domain), HttpStatus.OK);
	}
	
	@GetMapping(value="/statistics", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object>  getStatistics() {
		Statistics statistics=statisticsService.getStatistics();
		if(statistics==null) {
			return new ResponseEntity<Object>("No statistics found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(new StatisticsResponse(statistics), HttpStatus.OK);
	}
}
