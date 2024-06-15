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
	
	public List<Locacao> buscarPorMidia(Long id) {
		
		return this.locacaoDAO.buscarPorMidia(id);
	}
	
	public void salvar(Locacao locacao) {

		this.locacaoDAO.salvar(locacao);
	}
	
	public void atualizar(Locacao locacao) {
		this.locacaoDAO.atualizar(locacao);
	}
	
	public void excluir(Locacao locacao) {
		this.locacaoDAO.excluir(locacao);
	}
	
	public List<Locacao> listar(){
		return this.locacaoDAO.listar();
	} 
}
