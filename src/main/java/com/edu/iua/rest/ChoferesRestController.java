package com.edu.iua.rest;

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

import com.edu.iua.business.IChoferBusiness;
import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Camion;
import com.edu.iua.model.Chofer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = Constantes.URL_CHOFERES)
@Api(value = "Choferes", description = "Operaciones relacionadas con los choferes", tags = { "Choferes" })
public class ChoferesRestController extends BaseRestController {
	
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IChoferBusiness choferBusiness;
	
	@ApiOperation(value="Obtener un chofer mediante su ID", response = Chofer.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra el chofer"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<com.edu.iua.model.Chofer> load(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<Chofer>(choferBusiness.load(id),HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<Chofer>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Chofer>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value="Obtener un chofer mediante su Código Externo", response = Chofer.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra el chofer"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@GetMapping(path = "/ce/{codigoExterno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<com.edu.iua.model.Chofer> findByCodigoExterno(@PathVariable("codigoExterno") String codigoExterno) {
		try {
			return new ResponseEntity<Chofer>(choferBusiness.findByCodigoExterno(codigoExterno),HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<Chofer>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Chofer>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value="Obtener todos los choferes almacenados en la base de datos", response = Chofer.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Chofer>> list() {
		try {
			return new ResponseEntity<List<Chofer>>(choferBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<Chofer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value="Guardar un nuevo chofer en la base de datos", response = Chofer.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 400, message = "Algun valor ingresado es incorrecto"),
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Chofer chofer) {
		try {
			choferBusiness.save(chofer);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_CAMIONES + "/" + chofer.getDni());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value="Actualizar los datos de un chofer", response = Chofer.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra el chofer"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody Chofer chofer) {
		try {
			choferBusiness.save(chofer);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value="Borrar un chofer indicando su ID", response = Chofer.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra el chofer"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@DeleteMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			choferBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

}
