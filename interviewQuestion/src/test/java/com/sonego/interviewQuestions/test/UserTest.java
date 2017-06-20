package com.sonego.interviewQuestions.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sonego.interviewQuestions.InterviewQuestionsApplication;
import com.sonego.interviewQuestions.dao.impl.UserDaoImpl;
import com.sonego.interviewQuestions.model.User;
import com.sonego.interviewQuestions.service.impl.UserServiceImpl;
import com.sonego.interviewQuestions.util.EncryptData;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InterviewQuestionsApplication.class)
@WebAppConfiguration
public class UserTest {

	@Mock
	private UserDaoImpl dao;

	@InjectMocks
	@Spy
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
		user.setUserId(1);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveUserTest() {
		try {
			doNothing().when(dao).save(user);
			when(service.save(user)).thenReturn(user);

			User userRecovered = service.save(user);
			
			verify(dao, times(2)).save(user);
			verify(service, times(2)).searchUserByEmail(user.getEmail());
			
			assertNotNull(userRecovered);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void updateUserTest() {
		try {
			doNothing().when(dao).update(user);
			when(service.update(user)).thenReturn(user);

			User userRecovered = service.update(user);
			
			verify(dao, times(2)).update(user);
			verify(service, times(2)).searchUserByEmail(user.getEmail());
			
			assertNotNull(userRecovered);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void searchUsersTest() {
		try {
			when(dao.searchUsers()).thenReturn(Arrays.asList(user));

			List<User> listUsers = service.searchUsers();
			
			verify(dao, times(1)).searchUsers();
			
			assertTrue(listUsers.size() > 0);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void searchUserByIDTest() {
		try {
			when(service.searchUserByID(user.getUserId())).thenReturn(user);

			User userRecovered = service.searchUserByID(user.getUserId());
			
			verify(dao, times(1)).searchUserByID(user.getUserId());
			
			assertNotNull(userRecovered);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void searchUsersByFirstNameTest() {
		try {
			when(service.searchUsersByFirstName(user.getFirstName())).thenReturn(Arrays.asList(user));
			
			List<User> listUser = service.searchUsersByFirstName(user.getFirstName());
			
			verify(dao, times(1)).searchUsersByFirstName(user.getFirstName());
			
			assertNotNull(listUser.size() > 0);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void searchUserByEmailTest() {
		try {
			when(service.searchUserByEmail(user.getEmail())).thenReturn(user);
			
			User userRecovered = service.searchUserByEmail(user.getEmail());

			verify(dao, times(1)).searchUserByEmail(user.getEmail());
			
			assertNotNull(userRecovered);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void validateLoginTest() {
		try {
			when(dao.searchUser(user.getLogin(), EncryptData.encryptPassword(user.getPassword()))).thenReturn(user);
			
			boolean isValid = service.validateLogin(user.getLogin(), user.getPassword());
			
			assertTrue(isValid);
			
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	

}
