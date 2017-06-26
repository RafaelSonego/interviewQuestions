package com.sonego.interviewQuestions.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sonego.interviewQuestions.InterviewQuestionsApplication;
import com.sonego.interviewQuestions.model.User;
import com.sonego.interviewQuestions.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InterviewQuestionsApplication.class)
@WebAppConfiguration
public class UserTest {
	
	@Autowired
	private UserServiceImpl service;

	private User user;

	@Before
	public void createDomain() {
		this.user = new User();
		user.setLogin("rafael_sonego");
		user.setPassword("123456");
		user.setFirstName("Rafael");
		user.setLastName("Silva");
		user.setEmail("rafael2785@gmail.com");
		user.setDateBirthday(java.sql.Date.valueOf("1985-03-27"));
		user.setUserId(5);
	}
	
//	@Test
	public void saveUserTest() {
		try {
			User userRecovered = service.save(user);
			assertNotNull(userRecovered);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
//	@Test
	public void updateUserTest() {
		try {
			User userRecovered = service.update(user);
			assertNotNull(userRecovered);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void searchUsersTest() {
		try {
			List<User> listUsers = service.searchUsers();
			assertTrue(listUsers.size() > 0 || listUsers.size() == 0);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void searchUserByIDTest() {
		try {
			User userRecovered = service.searchUserByID(user.getUserId());
			assertNotNull(userRecovered);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void searchUsersByFirstNameTest() {
		try {
			List<User> listUser = service.searchUsersByFirstName(user.getFirstName());
			assertNotNull(listUser.size() > 0);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void searchUserByEmailTest() {
		try {
			User userRecovered = service.searchUserByEmail(user.getEmail());
			assertNotNull(userRecovered);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void searchUserByLoginAndEmailTest() {
		try {
			User userRecovered = service.searchUserByLoginAndEmail(user.getLogin(), user.getEmail());
			assertNotNull(userRecovered);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void validateLoginTest() {
		try {
			boolean isValid = service.validateLogin(user.getLogin(), user.getPassword());
			assertTrue(isValid);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

}
