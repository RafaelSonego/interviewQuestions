package com.sonego.interviewQuestions.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sonego.interviewQuestions.InterviewQuestionsApplication;
import com.sonego.interviewQuestions.model.User;
import com.sonego.interviewQuestions.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InterviewQuestionsApplication.class)
@WebAppConfiguration
public class UserTest {

	@Autowired
	private UserService service;
	
	private User user;

	@Before
	public void createDomain() {
		this.user = new User();
		user.setLogin("rafael_sonego11");
		user.setPassword("123456");
		user.setFirstName("Rafael11");
		user.setLastName("Silva11");
		user.setEmail("rafael278115@gmail.com");
		user.setDateBirthday(java.sql.Date.valueOf("1985-03-27"));
	}

	@Test
	public void saveUserTest() {
		try {
			User userRecovered = service.save(user);
			assertNotNull(userRecovered);
		} catch (Exception e) {
			assertTrue(false);
		}

	}

}
