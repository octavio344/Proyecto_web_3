package com.edu.iua.tpintegrador.model;




public class DatosCarga {

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

	public DatosCarga(Double masaAcumulada, Double densidadProducto, Double temperaturaProducto,
			Double caudalProducto) {
		super();
		this.masaAcumulada = masaAcumulada;
		this.densidadProducto = densidadProducto;
		this.temperaturaProducto = temperaturaProducto;
		this.caudalProducto = caudalProducto;
	}

	public DatosCarga() {
		super();
	}	
	
}
