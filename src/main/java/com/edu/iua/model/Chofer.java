package com.edu.iua.model;


import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "choferes")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="idChofer")
@ApiModel(value = "Chofer", description = "Modelo del chofer contratado para las ordenes")
public class Chofer implements Serializable {

	private static final long serialVersionUID = 8777321664336516570L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador del chofer, generado automáticamente", required = true)
	private Long idChofer;
	
	
	@Column(nullable = false,unique = true)
	@ApiModelProperty(notes = "DNI del chofer, ingresado manualmente", required = true)
	private Long dni;
	
	
	@Column(length = 100)
	@ApiModelProperty(notes = "Codigo externo del camión para identificación desde el sistema externo, ingresado manualmente", required = true)
	private String codigoExterno;
	
	@Column(length = 100,nullable = false)
	@ApiModelProperty(notes = "Nombre del chofer, ingresado manualmente", required = true)
	private String nombre;
	
	@Column(length = 100,nullable = false)
	@ApiModelProperty(notes = "Apellido del chofer, ingresado manualmente", required = true)
	private String apellido;
	
	@OneToMany(targetEntity=Orden.class, mappedBy="chofer", fetch = FetchType.LAZY)
	@JsonBackReference
	@ApiModelProperty(notes = "Lista de todas las órdenes en las que se contrató al chofer", required = false)
	private List<Orden> ordenList;
	
	//Constructores
	
	public List<Orden> getOrdenList() {
		return ordenList;
	}

	public void setOrdenList(List<Orden> ordenList) {
		this.ordenList = ordenList;
	}

	public Chofer(Long dni, String nombre, String apellido) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	
	public Chofer(Long dni, String codigoExterno, String nombre, String apellido) {
		super();
		this.dni = dni;
		this.codigoExterno = codigoExterno;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Chofer() {
		super();
	}
	
	//Getters y Setters

	
	
	public Long getDni() {
		return dni;
	}

	public Long getIdChofer() {
		return idChofer;
	}

	public void setIdChofer(Long idChofer) {
		this.idChofer = idChofer;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}
	
	public String getCodigoExterno() {
		return codigoExterno;
	}



	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Chofer [idChofer=" + idChofer + ", dni=" + dni + ", codigoExterno=" + codigoExterno + ", nombre="
				+ nombre + ", apellido=" + apellido + ", ordenList=" + ordenList + "]";
	}
	
	
	
}
