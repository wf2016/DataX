package com.fri.sjcs.csd.service;

import java.util.List;

import com.fri.sjcs.csd.dao.mapper.ConsumerMapper;
import com.fri.sjcs.model.entity.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;



@Service
public class ConsumerService {
	@Autowired
	private ConsumerMapper dao;
	
	public Integer count() throws Exception {
		try {
			return dao.count();
		} catch (BadSqlGrammarException e) {
			if (e.getSQLException().getSQLState().equals("42X05")) {
				dao.create();
			}
		}
		return 0;
	}
	
	public void create(String id, String name, String age, String sex) throws Exception {
		Consumer obj = new Consumer();
		obj.setId(Long.parseLong(id));
		obj.setName(name);
		obj.setAge(age);
		obj.setSex(sex);
		dao.insert(obj);
	}
	
	public List<Consumer> retrieveAll() throws Exception {
		return dao.selectAll();
	}
	
	public List<Consumer> retrieveSeveral(String limit, String offset) throws Exception {
		return dao.selectSeveral(Integer.parseInt(limit), Integer.parseInt(offset));
	}
	
	public void update(String id, String name, String age, String sex) throws Exception {
		Consumer obj = new Consumer();
		obj.setId(Long.parseLong(id));
		obj.setName(name);
		obj.setAge(age);
		obj.setSex(sex);
		dao.update(obj);
	}
	
	public void delete(List<String> ids) throws Exception {
		dao.delete(ids);
	}
}
