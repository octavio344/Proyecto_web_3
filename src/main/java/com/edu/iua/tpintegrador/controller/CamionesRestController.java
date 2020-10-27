package com.edu.iua.tpintegrador.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.iua.tpintegrador.business.ICamionBusiness;
import com.edu.iua.tpintegrador.business.exception.BusinessException;
import com.edu.iua.tpintegrador.business.exception.NotFoundException;
import com.edu.iua.tpintegrador.model.Camion;

@RestController
@RequestMapping(value = Constantes.URL_CAMIONES)
public class CamionesRestController extends BaseRestController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ICamionBusiness camionBusiness;
	
	@GetMapping(value = { "/{patente}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<com.edu.iua.tpintegrador.model.Camion> load(@PathVariable("patente") String patente) {
		try {
			return new ResponseEntity<Camion>(camionBusiness.load(patente),HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<Camion>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Camion>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Camion>> list() {
		try {
			return new ResponseEntity<List<Camion>>(camionBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<Camion>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Camion camion) {
		try {
			camionBusiness.save(camion);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_CAMIONES + "/" + camion.getPatente());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody Camion camion) {
		try {
			camionBusiness.save(camion);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = { "/{patente}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("patente") String patente) {
		try {
			camionBusiness.delete(patente);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

}
