package com.locadora.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;

import com.locadora.model.Cliente;
import com.locadora.util.DAOException;

public class ClienteDAOImpl implements ClienteDAO {

	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void salvar(Cliente cliente) {

		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(cliente);
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("Não foi possível inserir o cliente. ", ex);
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
	public void atualizar(Cliente cliente) {
		
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.merge(cliente);
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("Não foi possível atualizar o cliente. ", ex);
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
	public void excluir(Cliente cliente) {

		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(this.entityManager.getReference(Cliente.class, cliente.getId()));
			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("Não foi possível excluir o cliente. ", ex);
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
	public Cliente buscarPorId(Long id) {

		Query consulta = null;
		try {
			this.entityManager.getTransaction().begin();
			String jpql = "select c from Cliente c where c.id = :paramId";
			consulta = this.entityManager.createQuery(jpql, Cliente.class);
			consulta.setParameter("paramId", id);

			this.entityManager.getTransaction().commit();
			
		} catch (Throwable ex) {
			new DAOException("Não foi possível buscar cliente por Id ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha operação de busca por Id. ", t);
			}
		}
		
		return (Cliente) consulta.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> buscarPorNome(String nome) {
		
		List<Cliente> clientes = null;
		try {
			this.entityManager.getTransaction().begin();
			String jpql = "select c from Cliente c where c.nome like :paramNome";
			Query consulta = this.entityManager.createQuery(jpql, Cliente.class);
			consulta.setParameter("paramNome", "%"+ nome +"%");
			this.entityManager.getTransaction().commit();
			clientes = consulta.getResultList();
			
		} catch (Throwable ex) {
			new DAOException("Não foi possível buscar por Nome", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha operação de busca por Nome. ", t);
			}
		}
		return clientes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listar() {
		
		List<Cliente> clientes = null;
		
		try {
			this.entityManager.getTransaction().begin();
			String jpql = "select c from Cliente c";
			Query consulta = this.entityManager.createQuery(jpql);
			this.entityManager.getTransaction().commit();
			clientes = consulta.getResultList();
			
		} catch (Throwable ex) {
			new DAOException("Não foi possível listar cliente ", ex);
		} finally {
			try {
				
				if (this.entityManager.isOpen()) {
					this.entityManager.close();
				}
			} catch (Throwable t) {
				new DAOException("Erro ao Fecha operação de listar cliente. ", t);
			}
		}
		return clientes;
	}
}
