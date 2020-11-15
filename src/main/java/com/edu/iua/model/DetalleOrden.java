package com.edu.iua.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "detalle_ordenes")
@ApiModel(value = "Detalle de la carga", description = "Almacenamiento del detalle de la carga en un momento determinado")
public class DetalleOrden {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDetalle;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "orden_id")
	@ApiModelProperty(notes = "Orden a la que pertenece el detalle", required = true)
	private Orden ordenAsociada;

	@ApiModelProperty(notes = "Masa acumulada al momento de almacenar el detalle", required = true, allowableValues = "Mayor a cero")
	private Double masaAcumulada;
	
	@ApiModelProperty(notes = "Densidad del producto al momento de almacenar el detalle", required = true, allowableValues = "range[0,1]")
	private Double densidadProducto;
	
	@ApiModelProperty(notes = "Temperatura del producto al momento de almacenar el detalle", required = true, allowableValues = "Mayor a cero")
	private Double temperaturaProducto;
	
	@ApiModelProperty(notes = "Caudal de ingreso del producto al momento de almacenar el detalle", required = true, allowableValues = "Mayor a cero")
	private Double caudalProducto;
	
	@ApiModelProperty(notes = "Momento en el que se almacenó el detalle", required = true)
	private Date fechaDetalle;

	public Double getMasaAcumulada() {
		return masaAcumulada;
	}

	public void setMasaAcumulada(Double masaAcumulada) {
		this.masaAcumulada = masaAcumulada;
	}

	public Double getDensidadProducto() {
		return densidadProducto;
	}

	public void setDensidadProducto(Double densidadProducto) {
		this.densidadProducto = densidadProducto;
	}

	public Double getTemperaturaProducto() {
		return temperaturaProducto;
	}

	public void setTemperaturaProducto(Double temperaturaProducto) {
		this.temperaturaProducto = temperaturaProducto;
	}

	public Double getCaudalProducto() {
		return caudalProducto;
	}

	public void setCaudalProducto(Double caudalProducto) {
		this.caudalProducto = caudalProducto;
	}

	public Long getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(Long idDetalle) {
		this.idDetalle = idDetalle;
	}
	
	public Date getFechaDetalle() {
		return fechaDetalle;
	}

	public void setFechaDetalle(Date fechaDetalle) {
		this.fechaDetalle = fechaDetalle;
	}

	public DetalleOrden(Orden o,Double masaAcumulada, Double densidadProducto,
			Double temperaturaProducto, Double caudalProducto, Date fechaDetalle) {
		super();
		this.ordenAsociada = o;
		this.masaAcumulada = masaAcumulada;
		this.densidadProducto = densidadProducto;
		this.temperaturaProducto = temperaturaProducto;
		this.caudalProducto = caudalProducto;
		this.fechaDetalle = fechaDetalle;
	}

	public DetalleOrden() {
		super();
	}	
	
}
