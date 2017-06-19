package com.sonego.interviewQuestions.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonego.interviewQuestions.dao.UsuarioDao;
import com.sonego.interviewQuestions.model.Usuario;
import com.sonego.interviewQuestions.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	static Logger log = Logger.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioDao dao;
	
	@Override
	public Usuario salvar(Usuario user) throws Exception {
		try {
			log.debug("Iniciando metodo salvar");
			dao.salvar(user);
			Usuario usuarioRecuperado = recuperarUser(user.getNome());
			log.debug("Metodo Finalizado com sucesso - Objeto salvo na base");
			return usuarioRecuperado;
		} catch (Exception ex) {
			log.error("Falha ao executar", ex);
			throw ex;
		}
	}

	@Override
	public Usuario atualizar(Usuario user) throws Exception {
		try {
			log.debug("Iniciando metodo atualizar");
			dao.atualizar(user);
			Usuario usuarioRecuperado = recuperarUser(user.getNome());
			log.debug("Metodo Finalizado com sucesso - Objeto atualizado na base - id: " + user.getId());
			return usuarioRecuperado;
		} catch (Exception ex) {
			log.error("Falha ao executar", ex);
			throw ex;
		}
	}

	@Override
	public List<Usuario> recuperarUser() throws Exception {
		try {
			log.debug("Iniciando metodo recuperarUser");
			List<Usuario> list = dao.recuperarUser();
			log.debug("Metodo Finalizado com sucesso - Lista de objetos recuperado da base");
			return list;
		} catch (Exception ex) {
			log.error("Falha ao executar", ex);
			throw ex;
		}
	}

	@Override
	public Usuario recuperarUser(int id) throws Exception {
		try {
			log.debug("Iniciando metodo recuperarUser");
			Usuario list = dao.recuperarUser(id);
			log.debug("Metodo Finalizado com sucesso - Objeto recuperado da base - id: " + id);
			return list;
		} catch (Exception ex) {
			log.error("Falha ao executar", ex);
			throw ex;
		}
	}

	@Override
	public Usuario recuperarUser(String nome) throws Exception {
		try {
			log.debug("Iniciando metodo recuperarUser");
			Usuario list = dao.recuperarUser(nome);
			log.debug("Metodo Finalizado com sucesso - Objeto recuperado da base - nome: " + nome);
			return list;
		} catch (Exception ex) {
			log.error("Falha ao executar", ex);
			throw ex;
		}
	}
}
