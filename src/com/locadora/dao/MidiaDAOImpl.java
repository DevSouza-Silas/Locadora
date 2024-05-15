package com.locadora.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;

import com.locadora.model.Midia;

public class MidiaDAOImpl implements MidiaDAO {

	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void salvar(Midia midia) {

		this.entityManager.persist(midia);
	}

	@Override
	public void atualizar(Midia midia) {
		
		this.entityManager.merge(midia);
	}

	@Override
	public void excluir(Midia midia) {

		this.entityManager.remove(midia);
	}

	@Override
	public Midia buscarPorId(Long id) {

		String jpql = "select c from Midia c where c.id = :paramId";
		Query consulta = this.entityManager.createQuery(jpql, Midia.class);
		consulta.setParameter("paramId", id);
		
		return (Midia) consulta.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Midia> listar() {
		
		String jpql = "select c from Midia c";
		Query consulta = this.entityManager.createQuery(jpql);
		return consulta.getResultList();
	}
}
