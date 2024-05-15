package com.locadora.model;

import java.util.List;

import com.locadora.dao.CategoriaDAO;
import com.locadora.util.DAOFactory;

public class CategoriaRN {

	private CategoriaDAO categoriaDAO;

	public CategoriaRN() {
		
		this.categoriaDAO = DAOFactory.criarCategoriaDAO();
	}
	
	public Categoria buscarPorId(Long id) {
		
		return this.categoriaDAO.buscarPorId(id);
	}
	
	public List<Categoria> buscarPorDescricao(String descricao){
		
		return this.categoriaDAO.buscarPorDescricao(descricao);
	}
	
	public void salvar(Categoria categoria) {
		
		Long id = categoria.getId();
		if (id == null || id == 0) {
			this.categoriaDAO.salvar(categoria);
		} else {
			this.categoriaDAO.atualizar(categoria);
		}
	}
	
	public void excluir(Categoria categoria) {
		this.categoriaDAO.excluir(categoria);
	}
	
	public List<Categoria> listar(){
		
		return this.categoriaDAO.listar();
	}
}
