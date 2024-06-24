package com.cyands.apis.apidomain.model;

import com.cyands.apis.apidomain.entity.Statistics;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StatisticsResponse {
	
	@JsonProperty("count_of_blocked_malware_and_fishing")
	private Long countOfBlockedMalwareAndFishing;
	@JsonProperty("count_of_blocked_not_malware_and_fishing")
	private Long countOfBlockedNotMalwareAndFishing;
	
	public StatisticsResponse(Statistics statistics) {
		this.countOfBlockedMalwareAndFishing=statistics.getCountOfBlockedMalwareAndFishing();
		this.countOfBlockedNotMalwareAndFishing=statistics.getCountOfBlockedNotMalwareAndFishing();		
	}
}
