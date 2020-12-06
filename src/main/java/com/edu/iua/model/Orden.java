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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "ordenes")
@ApiModel(value = "Orden", description = "Modelo de la orden realizada")
public class Orden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador de la orden, generado automáticamente", required = true)
	private Long nroOrden;
	
	@Column(length = 100)
	@ApiModelProperty(notes = "Codigo externo de la orden para identificación desde el sistema externo, ingresado manualmente", required = true)
	private String codigoExterno;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "camion_id")
	@ApiModelProperty(notes = "Camión utilizado para la orden, se ingresa manualmente su código externo", required = true)
	private Camion camion;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "chofer_id")
	@ApiModelProperty(notes = "Chofer contratado para llevar la orden, se ingresa manualmente su código externo", required = true)
	private Chofer chofer;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "cliente_id")
	@ApiModelProperty(notes = "Cliente que realizó la orden, se ingresa manualmente su código externo", required = true)
	private Cliente cliente;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "producto_id")
	@ApiModelProperty(notes = "Producto adquirido en la orden, se ingresa manualmente su código externo", required = true)
	private Producto producto;
	
	@OneToMany(targetEntity=DetalleOrden.class, mappedBy="ordenAsociada", fetch = FetchType.LAZY)
	@ApiModelProperty(notes = "Lista de detalles asociados al proceso de carga del camión.", required = true)
	private List<DetalleOrden> detalleOrdenList;

	@ApiModelProperty(notes = "Fecha en la que se recibe la orden", required = true)
	private Date fechaRecepcion;
	
	@ApiModelProperty(notes = "Fecha en la que el camión arribará a la planta para su pesaje inicial", required = true)
	private Date fechaRecepcionPesajeI;
	
	@ApiModelProperty(notes = "Fecha en la que inicia el proceso de carga del camión", required = true)
	private Date fechaIProcesoCarga;
	
	@ApiModelProperty(notes = "Fecha en la que finaliza el proceso de carga del camión", required = true)
	private Date fechaFProcesoCarga;
	
	@ApiModelProperty(notes = "Fecha en la que se realiza el pesaje final antes de retirarse de la planta", required = true)
	private Date fechaRecepcionPesajeF;
	
	@ApiModelProperty(notes = "Fecha del último almacenamiento de un detalle de la carga", required = true)
	private Date fechaUltimoAlmacenamiento;
	
	@ApiModelProperty(notes = "Cantidad del producto a cargar durante la orden", required = true, allowableValues = "Mayor a cero")
	private Double preset;
	
	@ApiModelProperty(notes = "Valor del pesaje inicial del camión al arribar a la planta", required = true, allowableValues = "Mayor a cero")
	private Double pesajeInicial;
	
	@ApiModelProperty(notes = "Valor del pesaje final del camión al retirarse de la planta", required = true, allowableValues = "Mayor al pesaje inicial")
	private Double pesajeFinal;
	
	@ApiModelProperty(notes = "Masa acumulada del producto durante la carga ", required = true, allowableValues = "Mayor a cero")
	private Double masaAcumulada = 0.0;
	
	@ApiModelProperty(notes = "Densidad del producto durante la carga", required = true, allowableValues="range[0, 1]")
	private Double densidad = 0.0;
	
	@ApiModelProperty(notes = "Temperatura del producto durante la carga", required = true, allowableValues= "Mayor a Cero")
	private Double temperatura = 0.0;
	
	@ApiModelProperty(notes = "Caudal de carga del producto", required = true, allowableValues="Mayor a Cero")
	private Double caudal = 0.0;  

	@ApiModelProperty(notes = "Estado de la orden, comienza en uno y llega hasta cuatro", required = true, allowableValues="range[1, 4]")
	private int estado = 1;
	
	@ApiModelProperty(notes = "Clave de identificación del sistema externo", required = true)
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


	public String getCodigoExterno() {
		return codigoExterno;
	}

	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
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
