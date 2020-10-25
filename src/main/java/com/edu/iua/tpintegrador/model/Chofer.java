package com.edu.iua.tpintegrador.model;

public class Chofer {

	private Long dni;
	
	private String nombre;
	
	private String apellido;

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

	public Chofer(Long dni, String nombre, String apellido) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Chofer() {
		super();
	}
	
	
}
