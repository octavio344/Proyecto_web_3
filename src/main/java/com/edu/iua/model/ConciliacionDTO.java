package com.edu.iua.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "DTO de la conciliación", description = "DTO para enviar los datos requeridos por la conciliación")
public class ConciliacionDTO implements Serializable {
	
	private static final long serialVersionUID = 1877055914099271387L;

	@ApiModelProperty(notes = "Valor del pesaje inicial del camión al arribar a la planta", required = true, allowableValues = "Mayor a cero")
	private Double pesajeInicial;
	
	@ApiModelProperty(notes = "Valor del pesaje final del camión al retirarse de la planta", required = true, allowableValues = "Mayor a cero")
	private Double pesajeFinal;
	
	@ApiModelProperty(notes = "Masa acumulada del producto al finalizar la carga ", required = true, allowableValues = "Mayor a cero")
	private Double productoAcumulado;
	
	@ApiModelProperty(notes = "Valor neto por balanza del producto calculado restando el pesaje inicial del pesaje final", required = true, allowableValues = "Mayor a cero")
	private Double netoPorBalanza;
	
	@ApiModelProperty(notes = "Diferencia entre la balanza y el caudalímetro, calculada restando la masa acumulada del neto por balanza.", required = true, allowableValues = "Mayor a cero")
	private Double difBalyCaud;
	
	@ApiModelProperty(notes = "Temperatura promedio del producto durante la carga ", required = true, allowableValues = "Mayor a cero")
	private Double promedioTemp;
	
	@ApiModelProperty(notes = "Caudal promedio del producto durante la carga ", required = true, allowableValues = "Mayor a cero")
	private Double promedioCaudal;
	
	@ApiModelProperty(notes = "Densidad promedio del producto durante la carga ", required = true, allowableValues = "Mayor a cero")
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
