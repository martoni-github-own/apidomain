package com.cyands.apis.apidomain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyands.apis.apidomain.entity.Domain;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Long> {

	Domain findByDomainContaining(String domain);
}
