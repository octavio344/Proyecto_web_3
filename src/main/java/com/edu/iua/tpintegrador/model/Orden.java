package com.edu.iua.tpintegrador.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Orden {

	
	private Long nroOrden;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "camion_id")
	private Camion camion;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "chofer_id")
	private Chofer chofer;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	private Date fechaRecepcion;
	
	private Date fechaRecepcionPesajeI;
	
	private Date fechaIProcesoCarga;
	
	private Date fechaFProcesoCarga;
	
	private Date fechaRecepcionPesajeF;
	
	private Double preset;
	
	private DatosCarga carga;
	
	private int estado;
	
	private int psswd;

	public Long getNroOrden() {
		return nroOrden;
	}

	public void setNroOrden(Long nroOrden) {
		this.nroOrden = nroOrden;
	}

	public Camion getCamion() {
		return camion;
	}

	public void setCamion(Camion camion) {
		this.camion = camion;
	}

	public Chofer getChofer() {
		return chofer;
	}

	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Date getFechaRecepcionPesajeI() {
		return fechaRecepcionPesajeI;
	}

	public void setFechaRecepcionPesajeI(Date fechaRecepcionPesajeI) {
		this.fechaRecepcionPesajeI = fechaRecepcionPesajeI;
	}

	public Date getFechaIProcesoCarga() {
		return fechaIProcesoCarga;
	}

	public void setFechaIProcesoCarga(Date fechaIProcesoCarga) {
		this.fechaIProcesoCarga = fechaIProcesoCarga;
	}

	public Date getFechaFProcesoCarga() {
		return fechaFProcesoCarga;
	}

	public void setFechaFProcesoCarga(Date fechaFProcesoCarga) {
		this.fechaFProcesoCarga = fechaFProcesoCarga;
	}

	public Date getFechaRecepcionPesajeF() {
		return fechaRecepcionPesajeF;
	}

	public void setFechaRecepcionPesajeF(Date fechaRecepcionPesajeF) {
		this.fechaRecepcionPesajeF = fechaRecepcionPesajeF;
	}

	public Double getPreset() {
		return preset;
	}

	public void setPreset(Double preset) {
		this.preset = preset;
	}

	public DatosCarga getCarga() {
		return carga;
	}

	public void setCarga(DatosCarga carga) {
		this.carga = carga;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getPsswd() {
		return psswd;
	}

	public void setPsswd(int psswd) {
		this.psswd = psswd;
	}

	public Orden(Long nroOrden, Camion camion, Chofer chofer, Cliente cliente, Producto producto, Date fechaRecepcion,
			Date fechaRecepcionPesajeI, Date fechaIProcesoCarga, Date fechaFProcesoCarga, Date fechaRecepcionPesajeF,
			Double preset, DatosCarga carga, int estado, int psswd) {
		super();
		this.nroOrden = nroOrden;
		this.camion = camion;
		this.chofer = chofer;
		this.cliente = cliente;
		this.producto = producto;
		this.fechaRecepcion = fechaRecepcion;
		this.fechaRecepcionPesajeI = fechaRecepcionPesajeI;
		this.fechaIProcesoCarga = fechaIProcesoCarga;
		this.fechaFProcesoCarga = fechaFProcesoCarga;
		this.fechaRecepcionPesajeF = fechaRecepcionPesajeF;
		this.preset = preset;
		this.carga = carga;
		this.estado = estado;
		this.psswd = psswd;
	}

	public Orden() {
		super();
	}
	
	
}
