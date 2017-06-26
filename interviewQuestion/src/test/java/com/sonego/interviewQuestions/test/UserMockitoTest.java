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
import com.sonego.interviewQuestions.dao.filterSearch.UserFilterSearch;
import com.sonego.interviewQuestions.dao.impl.UserDaoImpl;
import com.sonego.interviewQuestions.model.User;
import com.sonego.interviewQuestions.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InterviewQuestionsApplication.class)
@WebAppConfiguration
public class UserMockitoTest {

	@Mock
	private UserDaoImpl userDAOMock;

	@Spy
	private UserServiceImpl userFilterSearchSpy;
	
	@InjectMocks
	@Spy
	private UserServiceImpl userServiceSpy;

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
			UserFilterSearch filter = new UserFilterSearch();
			when(userServiceSpy.createUserFilter()).thenReturn(filter);
			
			doNothing().when(userDAOMock).save(user);
			when(userServiceSpy.save(user)).thenReturn(user);

			User userRecovered = userServiceSpy.save(user);
			
			verify(userDAOMock, times(2)).save(user);
			verify(userServiceSpy, times(2)).searchUserByEmail(user.getEmail());
			assertNotNull(userRecovered);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void updateUserTest() {
		try {
			UserFilterSearch filter = new UserFilterSearch();
			when(userServiceSpy.createUserFilter()).thenReturn(filter);
			
			doNothing().when(userDAOMock).update(user);
			when(userServiceSpy.update(user)).thenReturn(user);

			User userRecovered = userServiceSpy.update(user);
			verify(userDAOMock, times(2)).update(user);
			verify(userServiceSpy, times(2)).searchUserByEmail(user.getEmail());
			assertNotNull(userRecovered);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void searchUsersTest() {
		try {
			when(userDAOMock.searchUsers()).thenReturn(Arrays.asList(user));

			List<User> listUsers = userServiceSpy.searchUsers();
			verify(userDAOMock, times(1)).searchUsers();
			assertTrue(listUsers.size() > 0);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void searchUserByIDTest() {
		try {
			UserFilterSearch filter = new UserFilterSearch();
			when(userServiceSpy.createUserFilter()).thenReturn(filter);
			
			when(userServiceSpy.searchUserByID(user.getUserId())).thenReturn(user);

			User userRecovered = userServiceSpy.searchUserByID(user.getUserId());
			assertNotNull(userRecovered);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void searchUsersByFirstNameTest() {
		try {
			when(userServiceSpy.searchUsersByFirstName(user.getFirstName())).thenReturn(Arrays.asList(user));

			List<User> listUser = userServiceSpy.searchUsersByFirstName(user.getFirstName());
			assertNotNull(listUser.size() > 0);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void searchUserByEmailTest() {
		try {
			UserFilterSearch filter = new UserFilterSearch();
			when(userServiceSpy.createUserFilter()).thenReturn(filter);
			
			when(userServiceSpy.searchUserByEmail(user.getEmail())).thenReturn(user);
			
			User userRecovered = userServiceSpy.searchUserByEmail(user.getEmail());
			assertNotNull(userRecovered);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void searchUserByLoginAndEmailTest() {
		try {
			UserFilterSearch filter = new UserFilterSearch();
			when(userServiceSpy.createUserFilter()).thenReturn(filter);
			
			when(userServiceSpy.searchUserByLoginAndEmail(user.getLogin(), user.getEmail())).thenReturn(user);
			
			User userRecovered = userServiceSpy.searchUserByLoginAndEmail(user.getLogin(), user.getEmail());
			assertNotNull(userRecovered);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void validateLoginTest() {
		try {
			UserFilterSearch filter = new UserFilterSearch();
			when(userServiceSpy.createUserFilter()).thenReturn(filter);
			when(userDAOMock.searchUserByFilter(filter)).thenReturn(user);
			boolean isValid = userServiceSpy.validateLogin(user.getLogin(), user.getPassword());
			assertTrue(isValid);
			
		} catch (Exception e) {
			assertTrue(false);
		}
	}

}
