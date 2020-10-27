package com.edu.iua.tpintegrador.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8624349707200492059L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	
	@Column(length = 60)
	private String razonSocial;
	
	@Column(length = 100)
	private String contacto;
	
	@OneToMany(targetEntity=Orden.class, mappedBy="clientes", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Orden> ordenList;
	
	public List<Orden> getOrdenList() {
		return ordenList;
	}

	public void setOrdenList(List<Orden> ordenList) {
		this.ordenList = ordenList;
	}

	public void setNumTelefono(Long numTelefono) {
		this.numTelefono = numTelefono;
	}

	private Long numTelefono;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public Long getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(long numTelefono) {
		this.numTelefono = numTelefono;
	}

	public Cliente(Long idCliente, String razonSocial, String contacto, long numTelefono) {
		super();
		this.idCliente = idCliente;
		this.razonSocial = razonSocial;
		this.contacto = contacto;
		this.numTelefono = numTelefono;
	}

	public Cliente() {
		super();
	}
	
	
}