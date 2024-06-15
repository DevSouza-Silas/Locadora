package com.locadora.teste;

import java.util.ArrayList;
import java.util.List;

import com.locadora.dao.LocacaoDAO;
import com.locadora.model.Categoria;
import com.locadora.model.CategoriaRN;
import com.locadora.model.Locacao;
import com.locadora.model.LocacaoRN;
import com.locadora.model.Midia;
import com.locadora.util.DAOException;
import com.locadora.util.DAOFactory;

public class Teste {

	static CategoriaRN categoriaRN = null;
	
	public static void main(String[] args) throws DAOException {
	
		//categoriaRN = new CategoriaRN();
		//Teste teste = new Teste();
		
		Locacao locacao = new Locacao();
		locacao.setMidia(new Midia());
		
		//locacao = DAOFactory.criarLocacaoDAO().buscarPorMidia(4l);		

		System.out.println("====>> " + locacao.getMidia().getId());
		//teste.buscarPorId();
		//teste.buscarPorDesc();
		//teste.listar();
		//teste.atualizar();
		//teste.excluir();
		//teste.cadastrar();
	}	
	
	public void buscarPorId() {
		Categoria categoria = new Categoria();
		categoria.setId(128l);
		categoria = categoriaRN.buscarPorId(categoria.getId());
		System.out.println("====>> "+ categoria.getId());
		System.out.println("====>> "+ categoria.getDescricao());
		
	}
	
	
	public void buscarPorDesc() {
		List<Categoria> categorias = new ArrayList<>();
		Categoria categoria = new Categoria();
		categoria.setDescricao("Aç");
		categorias = categoriaRN.buscarPorDescricao(categoria.getDescricao());
		
		for (Categoria categoria2 : categorias) {
			System.out.println("====>> "+ categoria2.getId());
			System.out.println("====>> "+ categoria2.getDescricao());
		}
		
	}
	
	public void listar() {
		
		for (Categoria categoria : categoriaRN.listar()) {
			System.out.println("===>> "+ categoria.getId());
			System.out.println("===>> "+ categoria.getDescricao());
			System.out.println("================================ ");
		}
	}

	public void atualizar() throws DAOException {
		
		Long id[] = {127l, 128l}; 
		String desc[] = {"teste01", "teste02"}; 
		Categoria categoria = null;
		
		for(int i = 0; i < id.length; i++) {
			
			categoriaRN = new CategoriaRN();
			categoria = new Categoria();

			categoria.setId(id[i]);
			categoria.setDescricao(desc[i]);
			categoriaRN.atualizar(categoria);
			
		}
		
	}
	
	public void excluir() throws DAOException {
		
		Long id[] = {123l, 124l,125l}; 
		Categoria categoria = null;
		
		for(int i =0; i < id.length; i++) {
			
			CategoriaRN categoriaRN = new CategoriaRN();
			categoria = new Categoria();
			categoria.setId(id[i]);
			
			categoriaRN.excluir(categoria);
		}
	}
	
	public void cadastrar() throws DAOException {
	
		String categorias[] = {"teste1", "teste2","teste3"};
		Categoria categoria = null;
		
		for (int i = 0; i < categorias.length; i++) {
			CategoriaRN categoriaRN = new CategoriaRN();
			categoria = new Categoria();
			categoria.setDescricao(categorias[i]);
			
			categoriaRN.salvar(categoria);
		}
		
	}
	
}
