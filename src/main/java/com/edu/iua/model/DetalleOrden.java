package com.edu.iua.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_ordenes")
public class DetalleOrden {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDetalle;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "orden_id")
	private Orden ordenAsociada;

	private Double masaAcumulada;
	
	private Double densidadProducto;
	
	private Double temperaturaProducto;
	
	private Double caudalProducto;

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



 
	
	

	public DetalleOrden(Orden o,Double masaAcumulada, Double densidadProducto,
			Double temperaturaProducto, Double caudalProducto) {
		super();
		this.ordenAsociada = o;
		this.masaAcumulada = masaAcumulada;
		this.densidadProducto = densidadProducto;
		this.temperaturaProducto = temperaturaProducto;
		this.caudalProducto = caudalProducto;
	}

	public DetalleOrden() {
		super();
	}	
	
}
