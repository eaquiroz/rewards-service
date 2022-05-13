package com.rewards.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.service.dto.Rewards;
import com.rewards.service.intermediate.CustomerService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * API Layer - It will expose the end-point contract.
 *
 */
@Slf4j
@RestController
@RequestMapping(path = "/rewards")
public class RewardsAPI {

	@Autowired
	private CustomerService customerService;
	
	/**
	 * It will handle the request to fetch the rewards of given userId
	 * 
	 * @param userId
	 * @return ResponseEntity<Rewards>
	 */
	@RequestMapping(value = "/{userId}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Rewards> rewards(@PathVariable("userId") String userId) {
		log.info("Fetch rewards request received. User Id: {}", userId);
		Rewards rewards = customerService.processRewards(userId);
		log.info("Fetch rewards response sending. User Id: {}", userId);
		return new ResponseEntity<Rewards>(rewards, HttpStatus.OK);
	}
}
