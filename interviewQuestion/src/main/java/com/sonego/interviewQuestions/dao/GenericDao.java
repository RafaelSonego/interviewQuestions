package com.sonego.interviewQuestions.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class GenericDao {

	@Autowired
	protected JdbcTemplate	template;

}
