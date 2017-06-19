package com.sonego.interviewQuestions.dao;

import java.util.List;

import com.sonego.interviewQuestions.model.Usuario;

public interface UsuarioDao {
	
	void salvar(Usuario usuario);
	
	void atualizar(Usuario usuario);
	
	List<Usuario> recuperarUser();
	
	Usuario recuperarUser(int id);
	
	Usuario recuperarUser(String nome);
	
}
