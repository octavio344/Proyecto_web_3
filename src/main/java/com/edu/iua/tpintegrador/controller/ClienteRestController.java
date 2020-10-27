package com.edu.iua.tpintegrador.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.iua.tpintegrador.business.IClienteBusiness;
import com.edu.iua.tpintegrador.business.exception.BusinessException;
import com.edu.iua.tpintegrador.business.exception.NotFoundException;
import com.edu.iua.tpintegrador.model.Cliente;


@RestController
@RequestMapping(value = Constantes.URL_CLIENTES)
public class ClienteRestController {

	
	@Autowired
	private IClienteBusiness clienteBusiness;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> load(@PathVariable("id") Long id) {
		
		try {
			return new ResponseEntity<Cliente>(clienteBusiness.findById(id),HttpStatus.OK); 
		}catch (NotFoundException e) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}catch (BusinessException e) {
			return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> list(){
		
		try {
			return new ResponseEntity<List<Cliente>>(clienteBusiness.listAll(),HttpStatus.OK);
		}catch (BusinessException e) {
			return new ResponseEntity<List<Cliente>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
	
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Cliente c){
		try {
			clienteBusiness.add(c);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_PRODUCTOS + "/" +c.getIdCliente());
			return new ResponseEntity<String>(responseHeaders,HttpStatus.OK);
		}catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody Cliente cliente) {
		try {
			clienteBusiness.update(cliente);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			clienteBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
}
