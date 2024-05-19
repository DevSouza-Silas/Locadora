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
		Categoria categoria = null;

		categoria = this.categoriaDAO.buscarPorId(id);
		return categoria;
	}
	
	public List<Categoria> buscarPorDescricao(String descricao) {
		List<Categoria> categorias = null;

		categorias = this.categoriaDAO.buscarPorDescricao(descricao);
		return categorias;
	}
	
	public void salvar(Categoria categoria) throws Exception {
	
		Long id = categoria.getId();
		if (id == null || id == 0) {
				this.categoriaDAO.salvar(categoria);
		} else {
			this.categoriaDAO.atualizar(categoria);
		}
	}
	
	public void excluir(Categoria categoria) throws Exception {

		this.categoriaDAO.excluir(categoria);
	}
	
	public List<Categoria> listar(){

		return this.categoriaDAO.listar();
	}
}
