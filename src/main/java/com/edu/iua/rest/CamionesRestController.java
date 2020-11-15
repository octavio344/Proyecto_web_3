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

import com.edu.iua.business.ICamionBusiness;
import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Camion;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = Constantes.URL_CAMIONES)
@Api(value = "Camiones", description = "Operaciones relacionadas con los camiones", tags = { "Camiones" })
public class CamionesRestController extends BaseRestController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ICamionBusiness camionBusiness;
	
	@ApiOperation(value="Obtener un camión mediante su ID", response = Camion.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra el camión"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@GetMapping(value = { "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<com.edu.iua.model.Camion> load(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<Camion>(camionBusiness.load(id),HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<Camion>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Camion>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value="Obtener un camión mediante su Código Externo", response = Camion.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra el camión"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@GetMapping(value = { "/ce/{codigoExterno}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<com.edu.iua.model.Camion> findByCodigoExterno(@PathVariable("codigoExterno") String codigoExterno) {
		try {
			return new ResponseEntity<Camion>(camionBusiness.findByCodigoExterno(codigoExterno),HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<Camion>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Camion>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value="Obtener todos los camiones almacenados en la base de datos", response = Camion.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@GetMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Camion>> list() {
		try {
			return new ResponseEntity<List<Camion>>(camionBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<Camion>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value="Guardar un nuevo camión en la base de datos", response = Camion.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 400, message = "Algun valor ingresado es incorrecto"),
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
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
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value="Actualizar los datos de un camión", response = Camion.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra el camión"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
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
	
	@ApiOperation(value="Borrar un camión indicando su ID", response = Camion.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra el camión"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@DeleteMapping(value = { "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			camionBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

}
