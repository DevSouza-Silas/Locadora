package com.locadora.model;

import java.util.List;

import com.locadora.dao.LocacaoDAO;
import com.locadora.util.DAOFactory;

public class LocacaoRN {

	private LocacaoDAO locacaoDAO;
	
	public LocacaoRN() {
		
		this.locacaoDAO = DAOFactory.criarLocacaoDAO();
	}
	
	public Locacao buscarPorID(Long id) {
		
		return this.locacaoDAO.buscarPorId(id);
	}
	
	public void salvar(Locacao locacao) {
		
		Long id = locacao.getId();
		
		if (id == null || id == 0) {
			this.locacaoDAO.salvar(locacao);
		} else {
			this.locacaoDAO.atualizar(locacao);
		}
	}
	
	public void excluir(Locacao locacao) {
		this.locacaoDAO.excluir(locacao);
	}
	
	public List<Locacao> listar(){
		return this.locacaoDAO.listar();
	} 
}
