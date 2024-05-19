package com.locadora.dao;

import java.util.List;

import com.locadora.model.Categoria;

public interface CategoriaDAO {

	public void salvar(Categoria categoria) throws Exception;
	public void atualizar(Categoria categoria) throws Exception;
	public void excluir(Categoria categoria) throws Exception;
	public Categoria buscarPorId(Long id);
	public List<Categoria> buscarPorDescricao(String descricao);
	public List<Categoria> listar();
}
