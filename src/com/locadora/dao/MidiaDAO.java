package com.locadora.dao;

import java.util.List;

import com.locadora.model.Midia;

public interface MidiaDAO {

	public void salvar(Midia midia);
	public void atualizar(Midia midia);
	public void excluir(Midia midia);
	public Midia buscarPorId(Long id);
	public List<Midia> listar();
}
