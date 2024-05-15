package com.locadora.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;

import com.locadora.model.Locacao;

public class LocacaoDAOImpl implements LocacaoDAO {

	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void salvar(Locacao locacao) {

		this.entityManager.persist(locacao);
	}

	@Override
	public void atualizar(Locacao locacao) {
		
		this.entityManager.merge(locacao);
	}

	@Override
	public void excluir(Locacao locacao) {

		this.entityManager.remove(locacao);
	}

	@Override
	public Locacao buscarPorId(Long id) {

		String jpql = "select c from Locacao c where c.id = :paramId";
		Query consulta = this.entityManager.createQuery(jpql, Locacao.class);
		consulta.setParameter("paramId", id);
		
		return (Locacao) consulta.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Locacao> listar() {
		
		String jpql = "select c from Locacao c";
		Query consulta = this.entityManager.createQuery(jpql);
		return consulta.getResultList();
	}
}
