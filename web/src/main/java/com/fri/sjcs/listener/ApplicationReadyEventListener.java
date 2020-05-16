package com.fri.sjcs.listener;

import com.fri.sjcs.csdm.dao.interfaces.RwxxDao;
import com.fri.sjcs.csdm.service.ConsumerService;
import com.fri.sjcs.csdm.service.RwxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;



@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {
	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private RwxxDao rwxxDao;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		try {
			consumerService.count();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			rwxxDao.selectCount(null);
		} catch (BadSqlGrammarException e){
			e.printStackTrace();
			rwxxDao.createtb("rwxx");
		}
	}
}
