package com.edu.iua.model;

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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "productos")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="idProducto")
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2724423191943622176L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true,nullable = false)
	private Long idProducto;
	
	
	@Column(length = 100)
	private String codigoExterno;
	
	@Column(length = 60,nullable = false)
	private String nombre;
	
	@Column(length = 140,nullable = false)
	private String descripcion;
	
	@OneToMany(targetEntity=Orden.class, mappedBy="producto", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Orden> ordenList;
	
	
	
	public Producto(Long id, String codigoExterno, String nombre, String descripcion,
			Long precio) {
		super();
		this.idProducto = id;
		this.codigoExterno = codigoExterno;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}

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
		return idProducto;
	}

	public void setId(Long id) {
		this.idProducto = id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCodigoExterno() {
		return codigoExterno;
	}



	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
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
		this.idProducto = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Producto() {
		super();
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", codigoExterno=" + codigoExterno + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", ordenList=" + ordenList + ", precio=" + precio + "]";
	}
	
	
	
}
