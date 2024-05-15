package com.locadora.dao;

import java.util.List;

import com.locadora.model.Categoria;

public interface CategoriaDAO {

	public void salvar(Categoria categoria);
	public void atualizar(Categoria categoria);
	public void excluir(Categoria categoria);
	public Categoria buscarPorId(Long id);
	public List<Categoria> buscarPorDescricao(String descricao);
	public List<Categoria> listar();
}
