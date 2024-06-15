package com.locadora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.locadora.model.Locacao;
import com.locadora.util.DAOException;

public class LocacaoDAOImpl implements LocacaoDAO {

	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void salvar(Locacao locacao) {

		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(locacao);
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("Não foi possível salvar a Locação. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha operação de inserção. ", t);
			}
		}

	}

	@Override
	public void atualizar(Locacao locacao) {
		
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.merge(locacao);
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("Não foi possível atualizar a Locação. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha operação de atualização. ", t);
			}
		}

	}

	@Override
	public void excluir(Locacao locacao) {

		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(this.entityManager.getReference(Locacao.class, locacao.getId()));
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("Não foi possível excluir a Locação. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha operação de exclusão. ", t);
			}
		}

	}

	@Override
	public Locacao buscarPorId(Long id) {

		Locacao locacao = null;
		try {
			this.entityManager.getTransaction().begin();

			String jpql = "select l from Locacao l where l.id = :paramId";
			Query consulta = this.entityManager.createQuery(jpql, Locacao.class);
			consulta.setParameter("paramId", id);
			
			this.entityManager.getTransaction().commit();
			locacao = (Locacao) consulta.getSingleResult();

		} catch (Throwable ex) {
			new DAOException("Não foi possível buscar Locação por Id. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha operação de busca por id. ", t);
			}
		}
		
		return locacao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Locacao> buscarPorMidia(Long id) {

		 List<Locacao> locacoes = null;
		try {
			this.entityManager.getTransaction().begin();

			String jpql = " from Locacao l where l.midia.id = :paramId and l.dataDevolucao >= CURRENT_DATE and l.horaEmprestimo < CURRENT_TIME";
			Query consulta = this.entityManager.createQuery(jpql, Locacao.class);
			consulta.setParameter("paramId", id);
			
			this.entityManager.getTransaction().commit();
			locacoes = consulta.getResultList();

		} catch (Throwable ex) {
			new DAOException("Não foi possível buscar Locação por Mídia. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha operação de busca por Mídia. ", t);
			}
		}
		
		return locacoes;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Locacao> listar() {
		
		List<Locacao> locacoes = null;
		try {
			this.entityManager.getTransaction().begin();

			String jpql = "select l from Locacao l";
			Query consulta = this.entityManager.createQuery(jpql);
			locacoes = consulta.getResultList();

		} catch (Throwable ex) {
			new DAOException("Não foi possível listar Locação. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha operação de listar Locação. ", t);
			}
		}

		return locacoes;
	}
}
