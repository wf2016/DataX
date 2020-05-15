package com.fri.sjcs.listener;

import com.fri.sjcs.csd.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;



@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {
	@Autowired
	private ConsumerService consumerService;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		try {
			consumerService.count();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
