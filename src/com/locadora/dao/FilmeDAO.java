package com.locadora.dao;

import java.util.List;

import com.locadora.model.Filme;

public interface FilmeDAO {

	public void salvar(Filme filme);
	public void atualizar(Filme filme);
	public void excluir(Filme filme);
	public Filme buscarPorId(Long id);
	public List<Filme> buscarPorDescricao(String descricao);
	public List<Filme> listar();
}
