package com.edu.iua.tpintegrador.model;

import java.util.ArrayList;

public class Camion {
	
	private String patente;
	
	private String descripcion;
	
	private ArrayList<Double> cisternado;

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

	public Camion(String patente, String descripcion, ArrayList<Double> cisternado) {
		super();
		this.patente = patente;
		this.descripcion = descripcion;
		this.cisternado = cisternado;
	}

	public Camion() {
		super();
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
