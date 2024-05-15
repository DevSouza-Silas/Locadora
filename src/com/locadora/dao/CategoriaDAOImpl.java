package com.locadora.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;

import com.locadora.model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {

	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public void salvar(Categoria categoria) {

		this.entityManager.persist(categoria);
	}

	@Override
	public void atualizar(Categoria categoria) {
		
		this.entityManager.merge(categoria);
	}

	@Override
	public void excluir(Categoria categoria) {

		this.entityManager.remove(categoria);
	}

	@Override
	public Categoria buscarPorId(Long id) {
		
		String jpql = "select c from Categoria c where c.id = :paramId";
		Query consulta = this.entityManager.createQuery(jpql, Categoria.class);
		consulta.setParameter("paramId", id);
		return (Categoria) consulta.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> buscarPorDescricao(String descricao) {
		
		String jpql = "select c from Categoria c where c.descricao like :paramDescricao";
		Query consulta = this.entityManager.createQuery(jpql, Categoria.class);
		consulta.setParameter("paramDescricao","%"+ descricao +"%");
		return consulta.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> listar() {
		
		String jpql = "select c from Categoria c";
		Query consulta = this.entityManager.createQuery(jpql);
		return consulta.getResultList();
	}

}
