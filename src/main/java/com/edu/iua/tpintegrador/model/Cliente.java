package com.edu.iua.tpintegrador.model;

public class Cliente {

	private Long idCliente;
	
	private String razonSocial;
	
	private String contacto;
	
	private long numTelefono;

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

	public long getNumTelefono() {
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
