package com.locadora.dao;

import java.util.List;

import com.locadora.model.Locacao;

public interface LocacaoDAO {

	public void salvar(Locacao locacao);
	public void atualizar(Locacao locacao);
	public void excluir(Locacao locacao);
	public Locacao buscarPorId(Long id);
	public List<Locacao> listar();
}
