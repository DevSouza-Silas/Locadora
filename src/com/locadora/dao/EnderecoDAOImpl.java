package com.locadora.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;

import com.locadora.model.Endereco;
import com.locadora.util.DAOException;

public class EnderecoDAOImpl implements EnderecoDAO {

	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void salvar(Endereco endereco) {

		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(endereco);
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("Não foi possível salvar o Endereço. ", ex);
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
	public void atualizar(Endereco endereco) {
		
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.merge(endereco);
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("Não foi possível atualizar o Endereço. ", ex);
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
	public void excluir(Endereco endereco) {

		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(this.entityManager.getReference(Endereco.class, endereco.getId()));
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("Não foi possível excluir o Endereço. ", ex);
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
	public Endereco buscarPorId(Long id) {

		Endereco endereco = null;
		try {
			this.entityManager.getTransaction().begin();
			
			String jpql = "select c from Endereco c where c.id = :paramId";
			Query consulta = this.entityManager.createQuery(jpql, Endereco.class);
			consulta.setParameter("paramId", id);
			
			this.entityManager.getTransaction().commit();
			endereco = (Endereco) consulta.getSingleResult();
			
		} catch (Throwable ex) {
			new DAOException("Não foi possível buscar Endereço por Id. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha operação de busca por Id. ", t);
			}
		}
		
		return endereco;
	}

	@SuppressWarnings("unchecked")
	public List<Endereco> buscarPorRua(String rua) {

		List<Endereco> enderecos = null;
		try {
			this.entityManager.getTransaction().begin();
			
			String jpql = "select c from Endereco c where c.rua = :paramRua";
			Query consulta = this.entityManager.createQuery(jpql, Endereco.class);
			consulta.setParameter("paramRua", rua);
			
			this.entityManager.getTransaction().commit();
			enderecos = consulta.getResultList();
			
		} catch (Throwable ex) {
			new DAOException("Não foi possível buscar Endereço por Rua. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha operação de busca. ", t);
			}
		}
		
		return enderecos;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Endereco> listar() {
		
		List<Endereco> enderecos = null;
		try {
			this.entityManager.getTransaction().begin();
			String jpql = "select c from Endereco c";
			Query consulta = this.entityManager.createQuery(jpql);
			this.entityManager.getTransaction().commit();

			enderecos = consulta.getResultList();
			
		} catch (Throwable ex) {
			new DAOException("Não foi possível buscar Endereço por Rua. ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha operação de busca. ", t);
			}
		}
		
		return enderecos;
	}
}
