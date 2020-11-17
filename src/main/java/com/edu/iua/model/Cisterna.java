package com.edu.iua.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "cisternas")
@ApiModel(value = "Cisterna", description = "Cisternas transportadas por los camiones")
public class Cisterna implements Serializable {

	private static final long serialVersionUID = 5555343978773673818L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador de la cisterna, generado automáticamente", required = true)
	private Long idCisterna;
	
	@ApiModelProperty(notes = "Capacidad de la cisterna", required = true)
	private Double capacidad;
	
	@ApiModelProperty(notes = "Codigo externo del camión para identificación desde el sistema externo, ingresado manualmente", required = true)
	@Column(length = 50,unique = true)
	private String codigoExterno;
	
	@ManyToMany(mappedBy = "cisternadoList")
	@JsonBackReference
	private List<Camion> camionesList;

	public Cisterna(Cisterna c) {
		this.codigoExterno= c.getCodigoExterno();
		this.capacidad = c.getCapacidad();
	}
	
	public Cisterna(Double capacidad, String codigoExterno) {
		super();
		this.capacidad = capacidad;
		this.codigoExterno = codigoExterno;
	}
	
	public Cisterna() {
		super();
	}

	public Long getIdCisterna(){
		return idCisterna;
	}

	public void setIdCisterna(Long idCisterna) {
		this.idCisterna = idCisterna;
	}

	public Double getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Double capacidad) {
		this.capacidad = capacidad;
	}

	public String getCodigoExterno() {
		return codigoExterno;
	}

	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}	
	
}
