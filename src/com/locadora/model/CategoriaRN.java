package com.locadora.model;

import java.util.List;

import com.locadora.dao.CategoriaDAO;
import com.locadora.util.DAOException;
import com.locadora.util.DAOFactory;

public class CategoriaRN {

	private CategoriaDAO categoriaDAO;

	public CategoriaRN() {
		
		this.categoriaDAO = DAOFactory.criarCategoriaDAO();
	}
	
	public Categoria buscarPorId(Long id) {
		Categoria categoria = null;

		categoria = this.categoriaDAO.buscarPorId(id);
		return categoria;
	}
	
	public List<Categoria> buscarPorDescricao(String descricao) {
		List<Categoria> categorias = null;

		categorias = this.categoriaDAO.buscarPorDescricao(descricao);
		return categorias;
	}
	
	public void salvar(Categoria categoria) throws DAOException {
	
		this.categoriaDAO.salvar(categoria);
	}
	
	public void atualizar(Categoria categoria) throws DAOException {
		
		this.categoriaDAO.atualizar(categoria);
	}
	
	public void excluir(Categoria categoria) throws DAOException {

		this.categoriaDAO.excluir(categoria);
	}
	
	public List<Categoria> listar(){

		return this.categoriaDAO.listar();
	}
}
