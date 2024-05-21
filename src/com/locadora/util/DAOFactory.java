package com.locadora.util;

import com.locadora.dao.CategoriaDAO;
import com.locadora.dao.CategoriaDAOImpl;
import com.locadora.dao.ClienteDAO;
import com.locadora.dao.ClienteDAOImpl;
import com.locadora.dao.EnderecoDAO;
import com.locadora.dao.EnderecoDAOImpl;
import com.locadora.dao.FilmeDAO;
import com.locadora.dao.FilmeDAOImpl;
import com.locadora.dao.LocacaoDAO;
import com.locadora.dao.LocacaoDAOImpl;
import com.locadora.dao.MidiaDAO;
import com.locadora.dao.MidiaDAOImpl;

public class DAOFactory {

	public static CategoriaDAO criarCategoriaDAO() {
		
		CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl();
		categoriaDAO.setEntityManager(JPAUtil.getEntityManager());
		
		return categoriaDAO;
	}
	
	public static ClienteDAO criarClienteDAO() {
		
		ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
		clienteDAO.setEntityManager(JPAUtil.getEntityManager());
		
		return clienteDAO;
	}
	
	public static EnderecoDAO criarEnderecoDAO() {
		
		EnderecoDAOImpl enderecoDAO = new EnderecoDAOImpl();
		enderecoDAO.setEntityManager(JPAUtil.getEntityManager());
		
		return enderecoDAO;
	}
	
	public static FilmeDAO criarFilmeDAO() {
		
		FilmeDAOImpl filmeDAO = new FilmeDAOImpl();
		filmeDAO.setEntityManager(JPAUtil.getEntityManager());
		
		return filmeDAO;
	}
	
	public static LocacaoDAO criarLocacaoDAO() {
		
		LocacaoDAOImpl locacaoDAO = new LocacaoDAOImpl();
		locacaoDAO.setEntityManager(JPAUtil.getEntityManager());
		
		return locacaoDAO;
	}
	
	public static MidiaDAO criarMidiaDAO() {
		
		MidiaDAOImpl midiaDAO = new MidiaDAOImpl();
		midiaDAO.setEntityManager(JPAUtil.getEntityManager());
		
		return midiaDAO;
	}
}
