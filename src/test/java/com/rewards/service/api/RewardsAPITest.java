package com.rewards.service.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rewards.service.Constants;
import com.rewards.service.dto.Rewards;
import com.rewards.service.intermediate.CustomerService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class RewardsAPITest {

	@InjectMocks
	private RewardsAPI rewardsApi;
	
	@Mock
	private CustomerService customerService;
	
	@Captor
	private ArgumentCaptor<String> userIdCaptor;
	
	@Test
	void testRewards() {
		//when
		Rewards rewards = new Rewards();
		rewards.setName("john smith");
		rewards.setRewards(10);
		when(customerService.processRewards(Mockito.anyString())).thenReturn(rewards);
		
		//then
		ResponseEntity<Rewards> response = rewardsApi.rewards(Constants.USER_ID);
		
		//return
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(customerService, times(1)).processRewards(userIdCaptor.capture());
		assertEquals(Constants.USER_ID, userIdCaptor.getValue());
	}
}
