package com.edu.iua.tpintegrador.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table(name = "camiones")

public class Camion implements Serializable {
	
	private static final long serialVersionUID = -5085577829256392612L;
	
	@Id
	private String patente;
	
	@Column(length = 100)
	private String descripcion;
	
	private ArrayList<Double> cisternado;
	
	//Metodo para calcular el total del cisternado
	
	public Double getTotalCisternado(){
		
		Double total = 0.0;
		
		for(int i=0;i < cisternado.size();i++){
			total += cisternado.get(i); 
		}
		
		return total;
		
	}
	
	//Constructores
	
	public Camion(String patente, String descripcion, ArrayList<Double> cisternado) {
		super();
		this.patente = patente;
		this.descripcion = descripcion;
		this.cisternado = cisternado;
	}

	public Camion() {
		super();
	}
	
	//Getters y Setters

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ArrayList<Double> getCisternado() {
		return cisternado;
	}

	public void setCisternado(ArrayList<Double> cisternado) {
		this.cisternado = cisternado;
	}
	
	public Double getTotalCisternado(){
		
		@SuppressWarnings("deprecation")
		Double total = new Double(0);
		
		for(int i=0;i < cisternado.size();i++){
			total += cisternado.get(i); 
		}
		
		return total;
		
	}

	
}
