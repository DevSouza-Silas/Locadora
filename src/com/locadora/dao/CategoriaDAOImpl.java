package com.locadora.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.locadora.model.Categoria;
import com.locadora.util.DAOException;

public class CategoriaDAOImpl implements CategoriaDAO {

	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void salvar(Categoria categoria) {
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(categoria);
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel inserir a categoria. ", ex);
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
	public void atualizar(Categoria categoria) {

		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.merge(categoria);
			this.entityManager.getTransaction().commit();
		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel atualizar a categoria. ", ex);
			if (this.entityManager.getTransaction().isActive()) {
				this.entityManager.getTransaction().rollback();
			}
			
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
	public void excluir(Categoria categoria) {

		try {

			this.entityManager.getTransaction();
			this.entityManager.getTransaction().begin();
			
			this.entityManager.remove(this.entityManager
					.getReference(Categoria.class, categoria.getId()));
			
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("N�o foi poss�vel remover a categoria. ", ex);
			
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de remo��o. ", t);
			}
		}
	}

	@Override
	public Categoria buscarPorId(Long id) {
		Categoria categoria	= null;	
		
		try {
			this.entityManager.getTransaction().begin();

			String jpql = "select c from Categoria c where c.id = :paramId";
			Query consulta = this.entityManager.createQuery(jpql, Categoria.class);
			consulta.setParameter("paramId", id);
			categoria = (Categoria) consulta.getSingleResult();

			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("Erro ao tentar realizar buscar por id.", ex);

			if (this.entityManager.getTransaction().isActive()) {
				this.entityManager.getTransaction().rollback();
			}
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de busca. ", t);
			}
		}
		return categoria;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> buscarPorDescricao(String descricao) {
		
		List<Categoria> categorias	= null;	
		
		try {
			this.entityManager.getTransaction().begin();

			String jpql = "select c from Categoria c where c.descricao like :paramDescricao";
			Query consulta = this.entityManager.createQuery(jpql, Categoria.class);
			consulta.setParameter("paramDescricao","%"+ descricao +"%");
			categorias = consulta.getResultList();

			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("Erro ao tentar realizar buscar por descri��o.", ex);
			
			if (this.entityManager.getTransaction().isActive()) {
				this.entityManager.getTransaction().rollback();
			}
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de busca. ", t);
			}
		}
		return categorias;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> listar() {
		
		List<Categoria> categorias	= null;	
		
		try {
			this.entityManager.getTransaction().begin();

			String jpql = "select c from Categoria c";
			Query consulta = this.entityManager.createQuery(jpql);
			categorias = consulta.getResultList();

			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("Erro ao tentar listar categoria.", ex);

			if (this.entityManager.getTransaction().isActive()) {
				this.entityManager.getTransaction().rollback();
			}
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha opera��o de busca. ", t);
			}
		}
		return categorias;
	}

}
