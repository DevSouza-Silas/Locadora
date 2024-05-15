package com.locadora.model;

import java.util.List;

import com.locadora.dao.ClienteDAO;
import com.locadora.util.DAOFactory;

public class ClienteRN {

	private ClienteDAO clienteDAO;
	
	public ClienteRN() {

		this.clienteDAO = DAOFactory.criarClienteDAO();
	}
	
	public Cliente buscarPorID(Long id) {
		
		return this.clienteDAO.buscarPorId(id);
	}
	
	public List<Cliente> buscarPorNome(String nome){
		
		return this.clienteDAO.buscarPorNome(nome);
	}
	
	public void salvar(Cliente cliente) {
		
		Long id = cliente.getId();
		
		if (id == null || id == 0) {
			this.clienteDAO.salvar(cliente);
		} else {
			this.clienteDAO.atualizar(cliente);
		}
	}
	
	public void excluir(Cliente cliente) {
		this.clienteDAO.excluir(cliente);
	}
	
	public List<Cliente> listar(){
		return this.clienteDAO.listar();
	}
}
