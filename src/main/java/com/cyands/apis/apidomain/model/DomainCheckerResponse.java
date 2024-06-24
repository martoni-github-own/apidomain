package com.cyands.apis.apidomain.model;

import com.cyands.apis.apidomain.entity.Domain;
import com.cyands.apis.apidomain.type.Category;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class DomainCheckerResponse {

	private String domain;
	@Enumerated(EnumType.STRING)
    private Category category;
	private boolean blocked;
	
	public DomainCheckerResponse(Domain domainEntity) {
		this.domain=domainEntity.getDomain();
		this.category=domainEntity.getCategory();
		this.blocked=domainEntity.isBlocked();
	}
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
}
