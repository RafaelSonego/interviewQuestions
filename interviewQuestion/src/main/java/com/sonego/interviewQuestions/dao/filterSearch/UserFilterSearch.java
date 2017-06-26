package com.sonego.interviewQuestions.dao.filterSearch;

import org.springframework.stereotype.Component;

@Component
public class UserFilterSearch {

	private Integer userId;
	private String login;
	private String password;
	private String email;
	private String firstName;
	private String lastName;

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Integer getUserId() {
		return userId;
	}

	public UserFilterSearch toLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public UserFilterSearch toLogin(String login) {
		this.login = login;
		return this;
	}

	public UserFilterSearch toPassword(String password) {
		this.password = password;
		return this;
	}

	public UserFilterSearch toEmail(String email) {
		this.email = email;
		return this;
	}

	public UserFilterSearch toFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public UserFilterSearch toUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

}
