package com.sonego.interviewQuestions.service;

import java.util.List;

import com.sonego.interviewQuestions.model.User;

public interface UserService {

	User save(User user) throws Exception;
	
	User update(User user) throws Exception;
	
	List<User> searchUsers() throws Exception;
	
	User searchUserByID(int userId) throws Exception;
	
	List<User> searchUsersByFirstName(String firstName) throws Exception;
	
	User searchUserByEmail(String email) throws Exception;
	
	

}
