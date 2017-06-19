package com.sonego.interviewQuestions.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sonego.interviewQuestions.dao.GenericDao;
import com.sonego.interviewQuestions.dao.UsuarioDao;
import com.sonego.interviewQuestions.model.Usuario;

@Repository
public class UsuarioDaoImpl extends GenericDao implements UsuarioDao {
	static Logger log = Logger.getLogger(UsuarioDaoImpl.class);

	public void salvar(Usuario user) {
		try {
			log.debug("Inserindo objeto na base, classe DAO");
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO USUARIO (NOME, EMAIL, IDADE) VALUES (?, ?, ?) ");
			Object[] parametros = new Object[]{user.getNome(), user.getEmail(), user.getIdade()};
			template.update(sql.toString(), parametros);
			log.debug("Objeto inserido na base classe dao");
		} catch (Exception e) {
			log.error("Erro na classe DAO", e);
			throw new RuntimeException(e);
		} 
	}

	public void atualizar(Usuario user) {
		try {
			log.debug("Atualizando objeto na base");
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE USUARIO SET ");
			sb.append("	NOME = ? ,");
			sb.append("	EMAIL = ? ,");
			sb.append("	IDADE = ? ");
			sb.append("WHERE ");
			sb.append(" ID = ? ");
			Object[] parametros = new Object[]{user.getNome(), user.getEmail(), user.getIdade(), user.getId()};
			template.update(sb.toString(), parametros);
			log.debug("Objeto atualizado com sucesso - classe dao");
		} catch (Exception e) {
			log.error("Erro na classe DAO", e);
			throw new RuntimeException(e);
		} 
	}

	public List<Usuario> recuperarUser() {
		try {
			log.debug("Consultando objetos na base - classe dao");
			String sql = "SELECT * FROM USUARIO ";
			List<Usuario> list = template.query(sql, new UsuarioMapper());
			log.debug("Metodo executado com sucesso - classe dao");
			return list;
		} catch (Exception e) {
			log.error("Erro na classe DAO", e);
			throw new RuntimeException(e);
		} 
	}

	public Usuario recuperarUser(int id) {
		try {
			log.debug("Recuperando objeto especifico - classe dao");
			String sql = "SELECT * FROM USUARIO WHERE ID = ? ";
			Object[] parametros = new Object[]{id};
			Usuario user = template.queryForObject(sql, parametros, new UsuarioMapper());
			log.debug("Metodo executado com sucesso - classe dao");
			return user;
		} catch (final EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			log.error("Erro na classe DAO", e);
			throw new RuntimeException(e);
		} 
	}

	public Usuario recuperarUser(String nome) {
		try {
			log.debug("Recuperando objeto especifico - classe dao");
			String sql = "SELECT * FROM USUARIO WHERE NOME = ? ";
			Object[] parametros = new Object[]{nome};
			Usuario user = template.queryForObject(sql, parametros, new UsuarioMapper());
			log.debug("Metodo executado com sucesso - classe dao");
			return user;
		} catch (Exception e) {
			log.error("Erro na classe DAO", e);
			throw new RuntimeException(e);
		} 
	}
	
	private class UsuarioMapper implements RowMapper<Usuario> {

		@Override
		public Usuario mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			Usuario user = new Usuario();
			user.setId(rs.getInt("ID"));
			user.setNome(rs.getString("NOME"));
			user.setEmail(rs.getString("EMAIL"));
			user.setIdade(rs.getInt("IDADE"));
			return user;
		}
	}
}
