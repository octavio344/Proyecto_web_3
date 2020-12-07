package com.edu.iua.eventos;

import org.springframework.context.ApplicationEvent;

public class OrdenEvent extends ApplicationEvent {

	private static final long serialVersionUID = 2022808737079531828L;

	public enum Tipo {
		TEMPERATURA_MAXIMA,
		CAPACIDAD_NOVENTA_PORCIENTO,
		PRESET_EXCEDIDO
	}
	
	private Tipo tipo;
	
	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public OrdenEvent(Object source, Tipo tipo) {
		super(source);
		this.tipo = tipo;
	}

}
