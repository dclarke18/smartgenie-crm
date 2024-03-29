/*
 * Copyright BLC IT Services Ltd 2015
 */
package uk.co.blc_services.smartgenie.rest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import uk.co.blc_services.smartgenie.domain.Job;

/**
 * @author dave.clarke@blc-services.co.uk
 * 
 */
public interface JobRepository extends JpaRepository<Job, Long> {
	List<Job> findByOrderId(@Param("orderId") String orderId);

}
