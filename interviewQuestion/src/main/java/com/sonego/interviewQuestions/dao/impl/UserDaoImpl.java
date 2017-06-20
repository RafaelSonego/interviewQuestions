package com.sonego.interviewQuestions.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sonego.interviewQuestions.dao.GenericDao;
import com.sonego.interviewQuestions.dao.UserDao;
import com.sonego.interviewQuestions.model.User;

@Repository
public class UserDaoImpl extends GenericDao implements UserDao {
	static Logger log = Logger.getLogger(UserDaoImpl.class);

	public void save(User user) {
		try {
			log.debug(" STARTING METHOD SAVE DAO ");
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO TBL_USER ( ");
			sql.append("	DS_LOGIN, ");
			sql.append("	DS_PASSWORD, ");
			sql.append("	DS_EMAIL, ");
			sql.append("	DS_FIRSTNAME, ");
			sql.append("	DS_LASTNAME, ");
			sql.append("	DT_BIRTHDAY, ");
			sql.append("	DT_CREATE, ");
			sql.append("	DT_UPDATE ");
			sql.append(" ) ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) ");
			Object[] parametros = new Object[]{user.getLogin(), user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getDateBirthday()};
			template.update(sql.toString(), parametros);
			log.debug("SUCCESS METHOD SAVE DAO ");
		} catch (Exception e) {
			log.error("ERROR METHOD SAVE DAO ", e);
			throw new RuntimeException(e);
		} 
	}

	public void update(User user) {
		try {
			log.debug("STARTING METHOD UPDATE DAO");
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE TBL_USER SET ");
			sql.append("	DS_EMAIL = ? , ");
			sql.append("	DS_FIRSTNAME = ? , ");
			sql.append("	DS_LASTNAME = ? , ");
			sql.append("	DT_BIRTHDAY = ? , ");
			sql.append("	DT_UPDATE = CURRENT_TIMESTAMP ");
			sql.append("WHERE ");
			sql.append(" PK_USER = ? ");
			Object[] parametros = new Object[]{user.getEmail(), user.getFirstName(), user.getLastName(), user.getDateBirthday(), user.getUserId()};
			template.update(sql.toString(), parametros);
			log.debug("SUCCESS METHOD UPDATE DAO ");
		} catch (Exception e) {
			log.error("ERROR METHOD UPDATE DAO ", e);
			throw new RuntimeException(e);
		} 
	}

	public List<User> searchUsers() {
		try {
			log.debug("SEARCHING USERS ... ");
			String sql = "SELECT * FROM TBL_USER ";
			List<User> list = template.query(sql, new UserMapper());
			log.debug("SUCCESS TO RECOVER USERS ");
			return list;
		} catch (Exception e) {
			log.error("ERROR METHOD SEARCHUSERS DAO ", e);
			throw new RuntimeException(e);
		} 
	}

	public User searchUserByID(int id) {
		try {
			log.debug("SEARCHING USERS BY ID ... ");
			String sql = "SELECT * FROM TBL_USER WHERE PK_USER = ? ";
			Object[] parameters = new Object[]{id};
			User user = template.queryForObject(sql, parameters, new UserMapper());
			log.debug("USER RECOVERED ");
			return user;
		} catch (final EmptyResultDataAccessException e) {
			log.debug("NO USER RECOVERED ");
			return null;
		} catch (Exception e) {
			log.error("ERROR METHOD SEARCHUSER BY ID DAO ", e);
			throw new RuntimeException(e);
		} 
	}

	public List<User> searchUsersByFirstName(String firstName) {
		try {
			log.debug("SEARCHING USERS BY FIRST NAME ... ");
			String sql = "SELECT * FROM TBL_USER WHERE DS_FIRSTNAME = ? ";
			List<User> list = template.query(sql, new UserMapper(), firstName);
			log.debug("SUCCESS TO RECOVER USERS ");
			return list;
		} catch (Exception e) {
			log.error("ERROR METHOD SEARCHUSER BY FIRST NAME DAO ", e);
			throw new RuntimeException(e);
		} 
	}
	
	public User searchUserByEmail(String email) {
		try {
			log.debug("SEARCHING USERS BY EMAIL ... ");
			String sql = "SELECT * FROM TBL_USER WHERE DS_EMAIL = ? ";
			Object[] parameters = new Object[]{email};
			User user = template.queryForObject(sql, parameters, new UserMapper());
			log.debug("USER RECOVERED ");
			return user;
		} catch (final EmptyResultDataAccessException e) {
			log.debug("NO USER RECOVERED ");
			return null;
		} catch (Exception e) {
			log.error("ERROR METHOD SEARCHUSER BY EMAIL DAO ", e);
			throw new RuntimeException(e);
		} 
	}
	
	public User searchUser(String login, String psw) {
		try {
			log.debug("SEARCHING USERS BY EMAIL ... ");
			String sql = "SELECT * FROM TBL_USER WHERE DS_LOGIN = ? AND DS_PASSWORD = ? ";
			Object[] parameters = new Object[]{login, psw};
			User user = template.queryForObject(sql, parameters, new UserMapper());
			log.debug("USER RECOVERED ");
			return user;
		} catch (final EmptyResultDataAccessException e) {
			log.debug("NO USER RECOVERED ");
			return null;
		} catch (Exception e) {
			log.error("ERROR METHOD SEARCHUSER DAO ", e);
			throw new RuntimeException(e);
		} 
	}
	
	private class UserMapper implements RowMapper<User> {

		@Override
		public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			User user = new User();
			user.setUserId(rs.getInt("PK_USER"));
			user.setLogin(rs.getString("DS_LOGIN"));
			user.setEmail(rs.getString("DS_EMAIL"));
			user.setFirstName(rs.getString("DS_FIRSTNAME"));
			user.setLastName(rs.getString("DS_LASTNAME"));
			user.setDateBirthday(rs.getDate("DT_BIRTHDAY"));
			return user;
		}
	}
}
