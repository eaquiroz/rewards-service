package com.rewards.service.intermediate;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.rewards.service.Constants;
import com.rewards.service.dto.Rewards;
import com.rewards.service.entity.Customer;
import com.rewards.service.entity.Purchase;
import com.rewards.service.exception.ResourceNotFoundException;
import com.rewards.service.repository.CustomerRepo;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;
	
	@Mock
	private CustomerRepo customerRepo;
	
	@Captor
	private ArgumentCaptor<String> userIdCaptor;
	
	@Test
	void testProcessRewards() {
		//when
		Purchase purchase = new Purchase();
		purchase.setAmount(120);
		Customer customer = new Customer();
		customer.setUserId(Constants.USER_ID);
		customer.setName("john smith");
		customer.setPurchase(purchase);
		when(customerRepo.findById(Mockito.anyString())).thenReturn(Optional.of(customer));
		//then
		
		Rewards rewards = customerService.processRewards(Constants.USER_ID);
		
		//return
		assertEquals(customer.getName(), rewards.getName());
		assertEquals("90", rewards.getRewards().toString());
		verify(customerRepo, times(1)).findById(userIdCaptor.capture());
		assertEquals(Constants.USER_ID, userIdCaptor.getValue());
	}
	
	@Test
	void testProcessRewardsCustomerNotFound() {
		//when
		when(customerRepo.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
		//then
		
		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> customerService.processRewards(Constants.USER_ID));
		
		//return
		assertEquals("Customer not found", exception.getMessage());
		verify(customerRepo, times(1)).findById(userIdCaptor.capture());
		assertEquals(Constants.USER_ID, userIdCaptor.getValue());
	}

}
