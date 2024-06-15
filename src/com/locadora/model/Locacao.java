package com.locadora.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "locacao")
public class Locacao implements Serializable {

	private static final long serialVersionUID = -1151369304396743283L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_locacao")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cod_cliente", nullable = false)
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_midia", nullable = false)
	private Midia midia;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emprestimo", updatable = false)
	private Date dataEmprestimo;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "hora_emprestimo", updatable = false)
	private Date horaEmprestimo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_devolucao")
	private Date dataDevolucao;
	
	@Column(name = "observacao")
	private String observacao;
	
	public Locacao() {
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Midia getMidia() {
		return midia;
	}


	public void setMidia(Midia midia) {
		this.midia = midia;
	}


	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}


	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public Date getHoraEmprestimo() {
		return horaEmprestimo;
	}

	public void setHoraEmprestimo(Date horaEmprestimo) {
		this.horaEmprestimo = horaEmprestimo;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
