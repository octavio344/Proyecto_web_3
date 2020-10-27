package com.edu.iua.tpintegrador.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "choferes")

public class Chofer implements Serializable {

	private static final long serialVersionUID = 8777321664336516570L;

	@Id
	private Long dni;
	
	@Column(length = 100)
	private String nombre;
	
	@Column(length = 100)
	private String apellido;
	
	//Constructores
	
	public Chofer(Long dni, String nombre, String apellido) {
		super();
		this.dni = dni;
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

	public void setDni(Long dni) {
		this.dni = dni;
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
	
}
