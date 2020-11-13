package com.edu.iua.model;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ordenes")

public class Orden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nroOrden;
	
	@Column(length = 100)
	private String codigoExterno;
	
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
	
	@OneToMany(targetEntity=DetalleOrden.class, mappedBy="ordenAsociada", fetch = FetchType.LAZY)
	private List<DetalleOrden> detalleOrdenList;

	
	private Date fechaRecepcion;
	
	private Date fechaRecepcionPesajeI;
	
	private Date fechaIProcesoCarga;
	
	private Date fechaFProcesoCarga;
	
	private Date fechaRecepcionPesajeF;
	
	private Date fechaUltimoAlmacenamiento;
	
	private Double preset;
	
	private Double pesajeInicial;
	
	private Double pesajeFinal;
	
	private Double masaAcumulada = 0.0;
	
	private Double densidad = 0.0;
	
	private Double temperatura = 0.0;
	
	private Double caudal = 0.0;  

	private int estado = 1;
	
	private String psswd;

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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getPsswd() {
		return psswd;
	}

	public void setPsswd(String psswd) {
		this.psswd = psswd;
	}
	
	
	
	public Date getFechaUltimoAlmacenamiento() {
		return fechaUltimoAlmacenamiento;
	}

	public void setFechaUltimoAlmacenamiento(Date fechaUltimoAlmacenamiento) {
		this.fechaUltimoAlmacenamiento = fechaUltimoAlmacenamiento;
	}

	
	
	public List<DetalleOrden> getDetalleOrdenList() {
		return detalleOrdenList;
	}

	public void setDetalleOrdenList(List<DetalleOrden> detalleOrdenList) {
		this.detalleOrdenList = detalleOrdenList;
	}

	public Double getPesajeInicial() {
		return pesajeInicial;
	}

	public void setPesajeInicial(Double pesajeInicial) {
		this.pesajeInicial = pesajeInicial;
	}

	public Double getPesajeFinal() {
		return pesajeFinal;
	}

	public void setPesajeFinal(Double pesajeFinal) {
		this.pesajeFinal = pesajeFinal;
	}

	public Double getMasaAcumulada() {
		return masaAcumulada;
	}

	public void setMasaAcumulada(Double masaAcumulada) {
		this.masaAcumulada = masaAcumulada;
	}

	public Double getDensidad() {
		return densidad;
	}

	public void setDensidad(Double densidad) {
		this.densidad = densidad;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public Double getCaudal() {
		return caudal;
	}

	public void setCaudal(Double caudal) {
		this.caudal = caudal;
	}

	public Orden(Long nroOrden, Camion camion, Chofer chofer, Cliente cliente, Producto producto, Date fechaRecepcion,
			Date fechaRecepcionPesajeI, Date fechaIProcesoCarga, Date fechaFProcesoCarga, Date fechaRecepcionPesajeF,
			Double preset, DetalleOrden carga, int estado, String psswd) {
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
		this.estado = estado;
		this.psswd = psswd;
	}

	public Orden() {
		super();
	}
	
	public String generatePsswd() {
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000);
		String formatted = String.format("%05d", num);
		setPsswd(formatted);
		return formatted;
	}
}
