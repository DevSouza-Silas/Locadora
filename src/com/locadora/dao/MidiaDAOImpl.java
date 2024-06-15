package com.locadora.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;

import com.locadora.model.Filme;
import com.locadora.model.Midia;
import com.locadora.util.DAOException;

public class MidiaDAOImpl implements MidiaDAO {

	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void salvar(Midia midia) {
		
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(midia);
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel salvar a Midia. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de inser��o. ", t);
			}
		}

	}

	@Override
	public void atualizar(Midia midia) {
		
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.merge(midia);
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel atualizar a Midia. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de atualiza��o. ", t);
			}
		}
	}

	@Override
	public void excluir(Midia midia) {

		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(this.entityManager.getReference(Midia.class, midia.getId()));
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel excluir a Midia. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de exclus�o. ", t);
			}
		}
	}

	@Override
	public Midia buscarPorId(Long id) {

		Midia midia = null;
		try {
			this.entityManager.getTransaction().begin();

			String jpql = "select m from Midia m where m.id = :paramId";
			Query consulta = this.entityManager.createQuery(jpql, Midia.class);
			consulta.setParameter("paramId", id);
			
			this.entityManager.getTransaction().commit();
			midia = (Midia)consulta.getSingleResult();

		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel buscar M�dia por Id. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de busca por id. ", t);
			}
		}
		
		return midia;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Midia> buscarMidiaPorFilme(Long filme) {
		
		List<Midia> midias = null;
		try {
			this.entityManager.getTransaction().begin();
			String jpql = "select m from Midia m where m.filme.id = :paramFilme";
			Query consulta = this.entityManager.createQuery(jpql, Midia.class);
			consulta.setParameter("paramFilme", filme);
			this.entityManager.getTransaction().commit();
			midias = consulta.getResultList();

		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel buscar Midia por Filme. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fechar opera��o de busca de Midia por Filme. ", t);
			}
		}
		return midias;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Midia> listar() {
		
		List<Midia> midias = null;
		try {
			this.entityManager.getTransaction().begin();

			String jpql = "select m from Midia m";
			Query consulta = this.entityManager.createQuery(jpql);
			midias = consulta.getResultList();

		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel listar M�dia. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de listar M�dia. ", t);
			}
		}

		return midias;
	}

}
