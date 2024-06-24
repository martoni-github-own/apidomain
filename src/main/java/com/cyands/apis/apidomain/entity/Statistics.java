package com.cyands.apis.apidomain.entity;


import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Statistics {
	
	@Id
	private Long id;
	private Long countOfBlockedMalwareAndFishing;
	private Long countOfBlockedNotMalwareAndFishing;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCountOfBlockedMalwareAndFishing() {
		return countOfBlockedMalwareAndFishing;
	}
	public void setCountOfBlockedMalwareAndFishing(Long countOfBlockedMalwareAndFishing) {
		this.countOfBlockedMalwareAndFishing = countOfBlockedMalwareAndFishing;
	}
	public Long getCountOfBlockedNotMalwareAndFishing() {
		return countOfBlockedNotMalwareAndFishing;
	}
	public void setCountOfBlockedNotMalwareAndFishing(Long countOfBlockedNotMalwareAndFishing) {
		this.countOfBlockedNotMalwareAndFishing = countOfBlockedNotMalwareAndFishing;
	}
	
	}
