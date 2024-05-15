package com.locadora.model;

import java.util.List;

import com.locadora.dao.FilmeDAO;
import com.locadora.util.DAOFactory;

public class FilmeRN {

	private FilmeDAO filmeDAO;
	
	public FilmeRN() {

		this.filmeDAO = DAOFactory.criarFilmeDAO();
	}

	public Filme buscarPorId(Long id) {
		
		return this.filmeDAO.buscarPorId(id);
	}
	
	public List<Filme> buscarPorDescricao(String descricao) {
		
		return this.filmeDAO.buscarPorDescricao(descricao);
	}
	
	public void salvar(Filme filme) {
		
		Long id = filme.getId();
		
		if (id == null || id == 0) {
			this.filmeDAO.salvar(filme);
		} else {
			this.filmeDAO.atualizar(filme);
		}
	}
	
	public void excluir(Filme filme) {
		this.filmeDAO.excluir(filme);
	} 
	
	public List<Filme> listar() {
		return this.filmeDAO.listar();
	} 
}
