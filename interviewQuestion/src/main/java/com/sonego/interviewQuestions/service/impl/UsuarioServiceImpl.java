package com.sonego.interviewQuestions.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonego.interviewQuestions.dao.UserDao;
import com.sonego.interviewQuestions.model.User;
import com.sonego.interviewQuestions.service.UserService;

@Service
public class UsuarioServiceImpl implements UserService {

	static Logger log = Logger.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UserDao dao;
	
	@Override
	public User save(User user) throws Exception {
		try {
			dao.save(user);
			User userRecovered = searchUserByEmail(user.getEmail());
			return userRecovered;
		} catch (Exception ex) {
			log.error("Error method save", ex);
			throw ex;
		}
	}

	@Override
	public User update(User user) throws Exception {
		try {
			dao.update(user);
			User userRecovered = searchUserByEmail(user.getEmail());
			return userRecovered;
		} catch (Exception ex) {
			log.error("Error method update", ex);
			throw ex;
		}
	}

	@Override
	public List<User> searchUsers() throws Exception {
		try {
			List<User> list = dao.searchUsers();
			return list;
		} catch (Exception ex) {
			log.error("Error method searchUsers", ex);
			throw ex;
		}
	}
	
	@Override
	public List<User> searchUsersByFirstName(String firstName) throws Exception {
		try {
			List<User> list = dao.searchUsersByFirstName(firstName);
			return list;
		} catch (Exception ex) {
			log.error("Error method searchUsersByFirstName", ex);
			throw ex;
		}
	}

	@Override
	public User searchUserByID(int id) throws Exception {
		try {
			User list = dao.searchUserByID(id);
			return list;
		} catch (Exception ex) {
			log.error("Error method searchUserByID", ex);
			throw ex;
		}
	}

	@Override
	public User searchUserByEmail(String nome) throws Exception {
		try {
			User list = dao.searchUserByEmail(nome);
			return list;
		} catch (Exception ex) {
			log.error("Error method searchUserByEmail", ex);
			throw ex;
		}
	}
}
