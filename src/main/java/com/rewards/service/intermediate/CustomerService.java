package com.rewards.service.intermediate;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewards.service.dto.Rewards;
import com.rewards.service.entity.Customer;
import com.rewards.service.entity.Purchase;
import com.rewards.service.exception.ResourceNotFoundException;
import com.rewards.service.repository.CustomerRepo;

import lombok.extern.slf4j.Slf4j;

/**
 * Customer service layer - It will process the rewards of given userId
 * 
 */
@Service
@Slf4j
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	
	/**
	 * This method will fetch the purchase of given user Id. 
	 * If Customer is found then it process for rewards.
	 * If Customer is not found then it will through the {@link ResourceNotFoundException}
	 * Calculation will be done of rewards as below.
	 * For every dollar spent over $50 on the transaction, the customer receives one point.
	 * In addition, for every dollar spent over $100, the customer receives another point.
	 * Example: for a $120 purchase, the customer receives(120 - 50) x 1 + (120 - 100) x 1 = 90 points
	 * 
	 * @param userId
	 * @return Rewards
	 */
	public Rewards processRewards(String userId) {
		log.debug("Processing rewards. User Id: {}", userId);
		Optional<Customer> optionalCustomer = customerRepo.findById(userId);
		if (optionalCustomer.isPresent()) {
			log.debug("{} is present", userId);
			Customer customer = optionalCustomer.get();
			Rewards rewards = new Rewards();
			rewards.setName(customer.getName());
			rewards.setRewards(0);
			Purchase purchase = customer.getPurchase();
			log.debug("{} purchase is present", userId);
			int receivedRewards = 0;
			if (purchase.getAmount() > 50 && purchase.getAmount() <= 100) {
				receivedRewards = (purchase.getAmount() - 50) * 1;
			}
			if (purchase.getAmount() > 100) {
				receivedRewards = (purchase.getAmount() - 50) * 1 + (purchase.getAmount() - 100) * 1;
			}
			log.trace("User {} received rewards points {}", userId, receivedRewards);
			rewards.setRewards(receivedRewards);
			log.debug("Processed rewards. User Id: {}", userId);
			return rewards;
		} else {
			throw new ResourceNotFoundException("Customer not found");
		}
	}
}