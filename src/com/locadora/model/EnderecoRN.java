package com.locadora.model;

import java.util.List;

import com.locadora.dao.EnderecoDAO;
import com.locadora.util.DAOFactory;

public class EnderecoRN {

	private EnderecoDAO enderecoDAO; 
	
	public EnderecoRN() {

		enderecoDAO = DAOFactory.criarEnderecoDAO();
	}

	public List<Endereco>  buscarPorRua(String rua) {
		
		return this.enderecoDAO.buscarPorRua(rua);
	}
	
	public Endereco buscarPorId(Long id) {
		
		return this.enderecoDAO.buscarPorId(id);
	}
	
	public void salvar(Endereco endereco) {
		
		this.enderecoDAO.salvar(endereco);
	} 
	
	public void atualizar(Endereco endereco) {
		
		this.enderecoDAO.atualizar(endereco);
	}
	
	public void excluir(Endereco endereco) {
		this.enderecoDAO.excluir(endereco);
	}
	
	public List<Endereco> listar(){
		return this.enderecoDAO.listar();
	}
}
