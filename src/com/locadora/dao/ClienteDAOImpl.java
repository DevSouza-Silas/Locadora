package com.locadora.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;

import com.locadora.model.Cliente;

public class ClienteDAOImpl implements ClienteDAO {

	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void salvar(Cliente cliente) {

		this.entityManager.persist(cliente);
	}

	@Override
	public void atualizar(Cliente cliente) {
		
		this.entityManager.merge(cliente);
	}

	@Override
	public void excluir(Cliente cliente) {

		this.entityManager.remove(cliente);
	}

	@Override
	public Cliente buscarPorId(Long id) {

		String jpql = "select c from Cliente c where c.id = :paramId";
		Query consulta = this.entityManager.createQuery(jpql, Cliente.class);
		consulta.setParameter("paramId", id);
		
		return (Cliente) consulta.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> buscarPorNome(String nome) {
		
		String jpql = "select c from Cliente c where c.nome like :paramNome";
		Query consulta = this.entityManager.createQuery(jpql, Cliente.class);
		consulta.setParameter("paramNome", "%"+ nome +"%");
		
		return consulta.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listar() {
		
		String jpql = "select c from Cliente c";
		Query consulta = this.entityManager.createQuery(jpql);
		return consulta.getResultList();
	}
}
