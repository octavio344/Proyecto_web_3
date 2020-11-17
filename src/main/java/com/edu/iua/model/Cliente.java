package com.edu.iua.model;

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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "clientes")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="idCliente")
@ApiModel(value = "Cliente", description = "Modelo del cliente que realizó las ordenes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 8624349707200492059L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador del cliente, generado automáticamente", required = true)
	private Long idCliente;
	
	
	@Column(length = 100,unique = true)
	@ApiModelProperty(notes = "Codigo externo del cliente para identificación desde el sistema externo, ingresado manualmente", required = true)
	private String codigoExterno;
	
	@Column(length = 60,nullable = false)
	@ApiModelProperty(notes = "Razon social del cliente, ingresada manualmente", required = true)
	private String razonSocial;
	
	@Column(length = 100,nullable = false)
	@ApiModelProperty(notes = "Contacto del cliente, ingresado manualmente", required = true)
	private String contacto;
	
	@OneToMany(targetEntity=Orden.class, mappedBy="cliente", fetch = FetchType.LAZY)
	@JsonBackReference
	@ApiModelProperty(notes = "Lista de todas las órdenes realizadas por el cliente", required = false)
	private List<Orden> ordenList;
	
	@Column(nullable = true)
	@ApiModelProperty(notes = "Número de teléfono del cliente, ingresado manualmente", required = false)
	private Long numTelefono;
	
	public List<Orden> getOrdenList() {
		return ordenList;
	}

	
	public Cliente(Cliente c) {
		this.codigoExterno = c.getCodigoExterno();
		this.contacto = c.getContacto();
		this.razonSocial = c.getRazonSocial();
	}
	
	
	
	public Cliente(Long idCliente, String codigoExterno, String razonSocial, String contacto, Long numTelefono) {
		super();
		this.idCliente = idCliente;
		this.codigoExterno = codigoExterno;
		this.razonSocial = razonSocial;
		this.contacto = contacto;
		this.numTelefono = numTelefono;
	}


	public void setOrdenList(List<Orden> ordenList) {
		this.ordenList = ordenList;
	}

	public void setNumTelefono(Long numTelefono) {
		this.numTelefono = numTelefono;
	}


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

	public String getCodigoExterno() {
		return codigoExterno;
	}



	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
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






	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", codigoExterno=" + codigoExterno + ", razonSocial=" + razonSocial
				+ ", contacto=" + contacto + ", ordenList=" + ordenList + ", numTelefono=" + numTelefono + "]";
	}
	
	
	
}
