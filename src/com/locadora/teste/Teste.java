package com.locadora.teste;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.locadora.dao.CategoriaDAO;
import com.locadora.model.Categoria;
import com.locadora.util.DAOFactory;

@ManagedBean
@RequestScoped
public class Teste {

	private Categoria categoria;
	
	public Teste() {
		categoria = new Categoria();
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public void salvar() {

		
		CategoriaDAO categoriaDAO = DAOFactory.criarCategoriaDAO();
		categoriaDAO.salvar(categoria);		
		
	}


	/*public static void main(String[] args) {

		Categoria categoria = new Categoria();
		categoria.setDescricao("teste 3");
		
		CategoriaDAO categoriaDAO = DAOFactory.criarCategoriaDAO();
		categoriaDAO.salvar(categoria);		
		
	}*/
}
