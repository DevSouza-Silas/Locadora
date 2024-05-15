package com.locadora.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;

import com.locadora.model.Filme;

public class FilmeDAOImpl implements FilmeDAO {

	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void salvar(Filme filme) {

		this.entityManager.persist(filme);
	}

	@Override
	public void atualizar(Filme filme) {
		
		this.entityManager.merge(filme);
	}

	@Override
	public void excluir(Filme filme) {

		this.entityManager.remove(filme);
	}

	@Override
	public Filme buscarPorId(Long id) {

		String jpql = "select c from Filme c where c.id = :paramId";
		Query consulta = this.entityManager.createQuery(jpql, Filme.class);
		consulta.setParameter("paramId", id);
		
		return (Filme) consulta.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Filme> buscarPorDescricao(String nome) {
		
		String jpql = "select c from Filme c where c.nome like :paramNome";
		Query consulta = this.entityManager.createQuery(jpql, Filme.class);
		consulta.setParameter("paramNome", "%"+ nome +"%");
		
		return consulta.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Filme> listar() {
		
		String jpql = "select c from Filme c";
		Query consulta = this.entityManager.createQuery(jpql);
		return consulta.getResultList();
	}
}
