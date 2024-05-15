package com.locadora.dao;

import java.util.List;

import com.locadora.model.Cliente;

public interface ClienteDAO {

	public void salvar(Cliente cliente);
	public void atualizar(Cliente cliente);
	public void excluir(Cliente cliente);
	public Cliente buscarPorId(Long id);
	public List<Cliente> buscarPorNome(String nome);
	public List<Cliente> listar();
}
