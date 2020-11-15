package com.edu.iua.model;

import java.io.Serializable;
import java.util.List;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@Entity
@Table(name = "camiones")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="idCamion")
@ApiModel(value = "Camion", description = "Modelo del camión utilizado para las ordenes")
public class Camion implements Serializable {
	
	private static final long serialVersionUID = -5085577829256392612L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador del camión, generado automáticamente", required = true)
	private Long idCamion;
	
	@Column(columnDefinition="varchar(10)" ,nullable = false,unique = true)
	@ApiModelProperty(notes = "Patente del camión, ingresada manualmente", required = true)
	private String patente;

	
	@Column(length = 100)
	@ApiModelProperty(notes = "Codigo externo del camión para identificación desde el sistema externo, ingresado manualmente", required = true)
	private String codigoExterno;
	
	@Column(length = 100)
	@ApiModelProperty(notes = "Descripción del camión, ingresada manualmente", required = false)
	private String descripcion;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "camiones_cisternas",
	        joinColumns = @JoinColumn(name = "id_camion", referencedColumnName="idCamion"),
	        inverseJoinColumns = @JoinColumn(name = "id_cisterna", referencedColumnName="idCisterna"))
	private List<Cisterna> cisternadoList;
	
	@OneToMany(targetEntity=Orden.class, mappedBy="camion", fetch = FetchType.LAZY)
	@JsonBackReference
	@ApiModelProperty(notes = "Lista de todas las órdenes en las que se utilizó el camión", required = false)
	private List<Orden> ordenList;
	
	//Metodo para calcular el total del cisternado
	
	
	//Constructores
	
	public Camion(String patente, String descripcion, List<Cisterna> cisternado) {
		super();
		this.patente = patente;
		this.descripcion = descripcion;
		this.cisternadoList = cisternado;
	}

	
	
	
	public Camion(String patente, String codigoExterno, String descripcion, List<Cisterna> cisternado) {
		super();
		this.patente = patente;
		this.codigoExterno = codigoExterno;
		this.descripcion = descripcion;
		this.cisternadoList = cisternado;
	}




	public String getCodigoExterno() {
		return codigoExterno;
	}



	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}



	public List<Orden> getOrdenList() {
		return ordenList;
	}

	public void setOrdenList(List<Orden> ordenList) {
		this.ordenList = ordenList;
	}

	public Camion() {
		super();
	}
	
	//Getters y Setters

	
	
	public String getPatente() {
		return patente;
	}

	public Long getIdCamion() {
		return idCamion;
	}




	public void setIdCamion(Long idCamion) {
		this.idCamion = idCamion;
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

	public List<Cisterna> getCisternado() {
		return cisternadoList;
	}

	public void setCisternado(List<Cisterna> cisternado) {
		this.cisternadoList = cisternado;
	}
	
	public Double getTotalCisternado(){
		
		@SuppressWarnings("deprecation")
		Double total = new Double(0);
		
		for(int i=0;i < cisternadoList.size();i++){
			total += cisternadoList.get(i).getCapacidad(); 
		}
		
		return total;
		
	}




	@Override
	public String toString() {
		return "Camion [idCamion=" + idCamion + ", patente=" + patente + ", codigoExterno=" + codigoExterno
				+ ", descripcion=" + descripcion + ", cisternado=" + cisternadoList + ", ordenList=" + ordenList + "]";
	}

	
}
