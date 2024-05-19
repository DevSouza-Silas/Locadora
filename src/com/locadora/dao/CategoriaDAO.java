package com.locadora.dao;

import java.util.List;

import com.locadora.model.Categoria;
import com.locadora.util.DAOException;

public interface CategoriaDAO {

	public void salvar(Categoria categoria) throws DAOException;
	public void atualizar(Categoria categoria) throws DAOException;
	public void excluir(Categoria categoria) throws DAOException;
	public Categoria buscarPorId(Long id);
	public List<Categoria> buscarPorDescricao(String descricao);
	public List<Categoria> listar();
}
