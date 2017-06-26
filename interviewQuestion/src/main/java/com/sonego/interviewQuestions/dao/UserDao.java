package com.sonego.interviewQuestions.dao;

import java.util.List;

import com.sonego.interviewQuestions.dao.filterSearch.UserFilterSearch;
import com.sonego.interviewQuestions.model.User;

public interface UserDao {
	
	void save(User user);
	
	void update(User user);
	
	List<User> searchUsers();

	List<User> searchUsersByFirstName(String firstName);
	
	User searchUserByFilter(UserFilterSearch filter);
	
}
