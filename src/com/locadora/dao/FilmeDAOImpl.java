package com.locadora.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;

import com.locadora.model.Filme;
import com.locadora.util.DAOException;

public class FilmeDAOImpl implements FilmeDAO {

	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void salvar(Filme filme) {
		
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(filme);
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel salvar o Filme. ", ex);
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
	public void atualizar(Filme filme) {
		
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.merge(filme);
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel atualizar o Filme. ", ex);
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
	public void excluir(Filme filme) {

		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(this.entityManager.getReference(Filme.class, filme.getId()));
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel excluir o Filme. ", ex);
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
	public Filme buscarPorId(Long id) {

		 Filme filme = null;
		try {
			this.entityManager.getTransaction().begin();

			String jpql = "select c from Filme c where c.id = :paramId";
			Query consulta = this.entityManager.createQuery(jpql, Filme.class);
			consulta.setParameter("paramId", id);
			this.entityManager.getTransaction().commit();
			filme = (Filme)consulta.getSingleResult();

		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel buscar Filme por Id. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de busca por id. ", t);
			}
		}
		
		return filme;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Filme> buscarFilmesPorMidia(Long idMidia) {
		
		List<Filme> filmes = null;
		try {
			this.entityManager.getTransaction().begin();
			String jpql = "select f from Filme f where f.midia.id = :paramId";
			Query consulta = this.entityManager.createQuery(jpql, Filme.class);
			consulta.setParameter("paramId", idMidia);
			this.entityManager.getTransaction().commit();
			filmes = consulta.getResultList();

		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel buscar Filmes Por Midia. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de busca Filmes Por Midia. ", t);
			}
		}
		return filmes;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Filme> buscarPorDescricao(String descricao) {
		
		List<Filme> filmes = null;
		try {
			this.entityManager.getTransaction().begin();
			String jpql = "select f from Filme f where f.descricao like :paramDesc";
			Query consulta = this.entityManager.createQuery(jpql, Filme.class);
			consulta.setParameter("paramDesc", "%"+ descricao +"%");
			this.entityManager.getTransaction().commit();
			filmes = consulta.getResultList();

		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel buscar por Descri��o. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de busca por Descri��o. ", t);
			}
		}
		return filmes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Filme> listar() {
		
		List<Filme> filmes = null;
		try {
			this.entityManager.getTransaction().begin();

			String jpql = "select c from Filme c";
			Query consulta = this.entityManager.createQuery(jpql);
			filmes = consulta.getResultList();

		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel listar Filme. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de listar filme. ", t);
			}
		}
		return filmes;
	}
}
