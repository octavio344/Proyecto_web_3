package com.edu.iua.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.iua.business.IDetalleOrdenBusiness;
import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.DetalleOrden;

import io.swagger.annotations.Api;


@RestController
@RequestMapping(value = "/api/v1/detalles-ordenes")
@Api(value = "Detalles de las ordenes", description = "Operaciones relacionadas con los detalles de las ordenes", tags = { "DetalleOrden" })
public class DetalleOrdenRestController {

	@Autowired
	private IDetalleOrdenBusiness detalleBusiness;
	
	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DetalleOrden> load(@PathVariable("id") Long id) {
		
		try {
			return new ResponseEntity<DetalleOrden>(detalleBusiness.findById(id),HttpStatus.OK); 
		}catch (NotFoundException e) {
			return new ResponseEntity<DetalleOrden>(HttpStatus.NOT_FOUND);
		}catch (BusinessException e) {
			return new ResponseEntity<DetalleOrden>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DetalleOrden>> list(){
		try {
			return new ResponseEntity<List<DetalleOrden>>(detalleBusiness.listAll(),HttpStatus.OK);
		}catch (BusinessException e) {
			return new ResponseEntity<List<DetalleOrden>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
	
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody DetalleOrden p){
		try {
			detalleBusiness.add(p);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_ORDENES + "/" +p.getIdDetalle());
			return new ResponseEntity<String>(responseHeaders,HttpStatus.OK);
		}catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			detalleBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
}
