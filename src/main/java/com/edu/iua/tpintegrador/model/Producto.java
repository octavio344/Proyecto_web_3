package com.edu.iua.tpintegrador.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2724423191943622176L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 60)
	private String nombre;
	
	@Column(length = 140)
	private String descripcion;
	
	@OneToMany(targetEntity=Orden.class, mappedBy="productos", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Orden> ordenList;
	
	public List<Orden> getOrdenList() {
		return ordenList;
	}

	public void setOrdenList(List<Orden> ordenList) {
		this.ordenList = ordenList;
	}

	private Long precio;

	public Long getPrecio() {
		return precio;
	}

	public void setPrecio(Long precio) {
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

	public Producto(Long id, String nombre, String descripcion, Long precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Producto() {
		super();
	}
	
	
	
}