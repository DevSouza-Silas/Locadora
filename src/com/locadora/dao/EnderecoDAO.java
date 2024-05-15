package com.locadora.dao;

import java.util.List;

import com.locadora.model.Endereco;

public interface EnderecoDAO {

	public void salvar(Endereco endereco);
	public void atualizar(Endereco endereco);
	public void excluir(Endereco endereco);
	public Endereco buscarPorId(Long id);
	public List<Endereco> listar();
}
