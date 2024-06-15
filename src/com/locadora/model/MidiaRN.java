package com.locadora.model;

import java.util.List;

import com.locadora.dao.MidiaDAO;
import com.locadora.util.DAOFactory;

public class MidiaRN {

	private MidiaDAO midiaDAO;
	
	public MidiaRN() {
		this.midiaDAO = DAOFactory.criarMidiaDAO();
	}
	
	public List<Midia> buscarMidiaPorFilme(Long id) {
		return this.midiaDAO.buscarMidiaPorFilme(id);
	}
	
	public Midia buscarPorID(Long id) {
		return this.midiaDAO.buscarPorId(id);
	}
	
	public void salvar(Midia midia) {
		this.midiaDAO.salvar(midia);
	}
	
	public void atualizar(Midia midia) {
		this.midiaDAO.atualizar(midia);
	}
	
	public void excluir(Midia midia) {
		this.midiaDAO.excluir(midia);
	}
	
	public List<Midia> listar(){
		return this.midiaDAO.listar();
	} 
}
