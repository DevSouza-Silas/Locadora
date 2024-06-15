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
			new DAOException("N�o foi poss�vel salvar a Loca��o. ", ex);
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
	public void atualizar(Locacao locacao) {
		
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.merge(locacao);
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel atualizar a Loca��o. ", ex);
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
	public void excluir(Locacao locacao) {

		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(this.entityManager.getReference(Locacao.class, locacao.getId()));
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel excluir a Loca��o. ", ex);
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
			new DAOException("N�o foi poss�vel buscar Loca��o por Id. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de busca por id. ", t);
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
			new DAOException("N�o foi poss�vel buscar Loca��o por M�dia. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de busca por M�dia. ", t);
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
			new DAOException("N�o foi poss�vel listar Loca��o. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de listar Loca��o. ", t);
			}
		}

		return locacoes;
	}
}
