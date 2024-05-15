package com.locadora.model;

import java.util.List;

import com.locadora.dao.EnderecoDAO;
import com.locadora.util.DAOFactory;

public class EnderecoRN {

	private EnderecoDAO enderecoDAO; 
	
	public EnderecoRN() {

		enderecoDAO = DAOFactory.criarEnderecoDAO();
	}

	public Endereco buscarPorId(Long id) {
		
		return this.enderecoDAO.buscarPorId(id);
	}
	
	public void salvar(Endereco endereco) {
		
		Long id = endereco.getId();
		
		if (id == null || id == 0) {
			this.enderecoDAO.salvar(endereco);
		} else {
			this.enderecoDAO.atualizar(endereco);
		}
	}
	
	public void excluir(Endereco endereco) {
		this.enderecoDAO.excluir(endereco);
	}
	
	public List<Endereco> listar(){
		return this.enderecoDAO.listar();
	}
}
