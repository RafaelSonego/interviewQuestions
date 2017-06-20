package com.sonego.interviewQuestions.dao;

import java.util.List;

import com.sonego.interviewQuestions.model.User;

public interface UserDao {
	
	void save(User user);
	
	void update(User user);
	
	List<User> searchUsers();
	
	User searchUserByID(int userId);
	
	List<User> searchUsersByFirstName(String firstName);
	
	User searchUserByEmail(String email);
	
	User searchUser(String login, String psw);
	
}
