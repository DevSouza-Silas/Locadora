package com.locadora.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;

import com.locadora.model.Endereco;

public class EnderecoDAOImpl implements EnderecoDAO {

	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void salvar(Endereco endereco) {

		this.entityManager.persist(endereco);
	}

	@Override
	public void atualizar(Endereco endereco) {
		
		this.entityManager.merge(endereco);
	}

	@Override
	public void excluir(Endereco endereco) {

		this.entityManager.remove(endereco);
	}

	@Override
	public Endereco buscarPorId(Long id) {

		String jpql = "select c from Endereco c where c.id = :paramId";
		Query consulta = this.entityManager.createQuery(jpql, Endereco.class);
		consulta.setParameter("paramId", id);
		
		return (Endereco) consulta.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Endereco> listar() {
		
		String jpql = "select c from Endereco c";
		Query consulta = this.entityManager.createQuery(jpql);
		return consulta.getResultList();
	}
}
