package com.sonego.interviewQuestions.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonego.interviewQuestions.dao.UserDao;
import com.sonego.interviewQuestions.dao.filterSearch.UserFilterSearch;
import com.sonego.interviewQuestions.model.User;
import com.sonego.interviewQuestions.service.UserService;
import com.sonego.interviewQuestions.util.EncryptData;

@Service
public class UserServiceImpl implements UserService {

	static Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao dao;
	
	public UserFilterSearch createUserFilter() {
		return new UserFilterSearch();
	}
	
	@Override
	public User save(User user) throws Exception {
		try {
			String pswEncrypt = EncryptData.encryptPassword(user.getPassword());
			user.setPassword(pswEncrypt);
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
			UserFilterSearch filter = createUserFilter();
			filter.toUserId(id);
			User user = dao.searchUserByFilter(filter);
			return user;
		} catch (Exception ex) {
			log.error("Error method searchUserByID", ex);
			throw ex;
		}
	}

	@Override
	public User searchUserByEmail(String email) throws Exception {
		try {
			UserFilterSearch filter = createUserFilter();
			filter.toEmail(email);
			User user = dao.searchUserByFilter(filter);
			return user;
		} catch (Exception ex) {
			log.error("Error method searchUserByEmail", ex);
			throw ex;
		}
	}
	
	@Override
	public User searchUserByLoginAndEmail(String login, String email) throws Exception {
		try {
			UserFilterSearch filter = createUserFilter();
			filter.toLogin(login).toEmail(email);
			User user = dao.searchUserByFilter(filter);
			return user;
		} catch (Exception ex) {
			log.error("Error method searchUserByLoginAndEmail", ex);
			throw ex;
		}
	}

	@Override
	public boolean validateLogin(String login, String psw) throws Exception {
		try{
			UserFilterSearch filter = createUserFilter();
			String pswEncrypt = EncryptData.encryptPassword(psw);
			filter.toLogin(login).toPassword(pswEncrypt);
			User user = dao.searchUserByFilter(filter);
			return user != null;
		}catch(Exception ex){
			log.error("Error validate login user", ex);
			throw ex;
		}
	}
}
