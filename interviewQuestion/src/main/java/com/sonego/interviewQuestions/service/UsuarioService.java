package com.sonego.interviewQuestions.service;

import java.util.List;

import com.sonego.interviewQuestions.model.Usuario;

public interface UsuarioService {

	Usuario salvar(Usuario user) throws Exception;

	Usuario atualizar(Usuario user) throws Exception;

	List<Usuario> recuperarUser() throws Exception;

	Usuario recuperarUser(int id) throws Exception;
	
	Usuario recuperarUser(String nome) throws Exception;

}
