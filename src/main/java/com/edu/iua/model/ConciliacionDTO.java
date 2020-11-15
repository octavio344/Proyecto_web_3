package com.edu.iua.model;

import java.io.Serializable;

public class ConciliacionDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1877055914099271387L;

	private Double pesajeInicial;
	
	private Double pesajeFinal;
	
	private Double productoAcumulado;
	
	private Double netoPorBalanza;
	
	private Double difBalyCaud;
	
	private Double promedioTemp;
	
	private Double promedioCaudal;
	
	private Double promedioDensidad;

	public ConciliacionDTO(Double pesajeInicial, Double pesajeFinal, Double productoAcumulado, Double promedioTemp,
			Double promedioCaudal, Double promedioDensidad) {
		super();
		this.pesajeInicial = pesajeInicial;
		this.pesajeFinal = pesajeFinal;
		this.productoAcumulado = productoAcumulado;
		this.promedioTemp = promedioTemp;
		this.promedioCaudal = promedioCaudal;
		this.promedioDensidad = promedioDensidad;
		this.netoPorBalanza = pesajeFinal - pesajeInicial;
		this.difBalyCaud =  netoPorBalanza - productoAcumulado;
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

	public Double getProductoAcumulado() {
		return productoAcumulado;
	}

	public void setProductoAcumulado(Double productoAcumulado) {
		this.productoAcumulado = productoAcumulado;
	}

	public Double getNetoPorBalanza() {
		return netoPorBalanza;
	}

	public void setNetoPorBalanza(Double netoPorBalanza) {
		this.netoPorBalanza = netoPorBalanza;
	}

	public Double getDifBalyCaud() {
		return difBalyCaud;
	}

	public void setDifBalyCaud(Double difBalyCaud) {
		this.difBalyCaud = difBalyCaud;
	}

	public Double getPromedioTemp() {
		return promedioTemp;
	}

	public void setPromedioTemp(Double promedioTemp) {
		this.promedioTemp = promedioTemp;
	}

	public Double getPromedioCaudal() {
		return promedioCaudal;
	}

	public void setPromedioCaudal(Double promedioCaudal) {
		this.promedioCaudal = promedioCaudal;
	}

	public Double getPromedioDensidad() {
		return promedioDensidad;
	}

	public void setPromedioDensidad(Double promedioDensidad) {
		this.promedioDensidad = promedioDensidad;
	}

}
