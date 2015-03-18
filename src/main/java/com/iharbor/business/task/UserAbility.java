package com.iharbor.business.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserAbility {

	@Scheduled(cron="1 0 0 * * ?")
	public void updateUserDailyAbility(){
		
	}
}
