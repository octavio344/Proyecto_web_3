package com.edu.iua.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.iua.business.IOrdenBusiness;
import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.business.exception.WrongStateException;
import com.edu.iua.model.ConciliacionDTO;
import com.edu.iua.model.Orden;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/ordenes")
@Api(value = "Ordenes", description = "Operaciones relacionadas con las ordenes", tags = { "Ordenes" })
public class OrdenRestController {

	
	@Autowired
	private IOrdenBusiness ordenBusiness;
	
	@ApiOperation(value="Obtener una orden mediante su ID", response = Orden.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra la orden"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orden> load(@PathVariable("id") Long id) {
		
		try {
			return new ResponseEntity<Orden>(ordenBusiness.findById(id),HttpStatus.OK); 
		}catch (NotFoundException e) {
			return new ResponseEntity<Orden>(HttpStatus.NOT_FOUND);
		}catch (BusinessException e) {
			return new ResponseEntity<Orden>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value="Obtener una orden mediante su Código Externo", response = Orden.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra la orden"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@GetMapping(value = "/ce/{codigoExterno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orden> findByCodigoExterno(@PathVariable("codigoExterno") String codigoExterno) {
		
		try {
			return new ResponseEntity<Orden>(ordenBusiness.findByCodigoExterno(codigoExterno),HttpStatus.OK); 
		}catch (NotFoundException e) {
			return new ResponseEntity<Orden>(HttpStatus.NOT_FOUND);
		}catch (BusinessException e) {
			return new ResponseEntity<Orden>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value="Obtener todas las ordenes almacenadas en la base de datos", response = Orden.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Orden>> list(){
		try {
			return new ResponseEntity<List<Orden>>(ordenBusiness.listAll(),HttpStatus.OK);
		}catch (BusinessException e) {
			return new ResponseEntity<List<Orden>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
	
	//Endpoint Nro 1 - Funciona OK
	
	@ApiOperation(value="Endpoint 1: Generar una nueva orden en la base de datos.", response = Orden.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Orden creada exitosamente"),
			@ApiResponse(code = 400, message = "Algun valor ingresado es incorrecto"),
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@PostMapping(value = "/crear", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> abrirOrden(@RequestBody Orden p){
		try {
			ordenBusiness.add(p);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_ORDENES + "/" +p.getNroOrden());
			return new ResponseEntity<String>(responseHeaders,HttpStatus.CREATED);
		}catch (BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//Endpoint Nro 2 - Funciona OK
	
	@ApiOperation(value="Endpoint 2: Establecer el pesaje inicial del camión.", response = Orden.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 400, message = "Algun valor ingresado es incorrecto"),
			@ApiResponse(code = 404, message = "No se encuentra la orden"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})

	@PutMapping(value = "/pesajeInicial", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> pesajeInicial(@RequestBody Orden orden) {
		try {
			ordenBusiness.setearPesajeInicial(orden);	
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}	catch (WrongStateException e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	//Endpoint Nro 3 - Funciona OK
	
	@ApiOperation(value="Endpoint 3: Cargar los detalles de la carga durante la misma.", response = Orden.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 400, message = "Algun valor ingresado es incorrecto"),
			@ApiResponse(code = 404, message = "No se encuentra la orden"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@PutMapping(value = "/actualizarDetalle", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateDetalle(@RequestBody Orden orden) {
		try {
			ordenBusiness.updateDetalle(orden);	
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} catch (WrongStateException e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	//Endpoint Nro 4 - Funciona OK
	
	@ApiOperation(value="Endpoint 4: Cierra la orden una vez finalizada la carga.", response = Orden.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 400, message = "Algun valor ingresado es incorrecto"),
			@ApiResponse(code = 404, message = "No se encuentra la orden"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@PutMapping(value = "/cerrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> cerrarOrden(@RequestBody Orden orden) {
		try {
			ordenBusiness.cerrarOrden(orden);	
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}catch (WrongStateException e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	//Endpoint Nro 5

	@ApiOperation(value="Endpoint 5: Se debe ingresar el pesaje final.", response = Orden.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 400, message = "Algun valor ingresado es incorrecto"),
			@ApiResponse(code = 404, message = "No se encuentra la orden"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@PutMapping(value = "/pesajeFinal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> finalizar(@RequestBody Orden orden) {
		try {
			ordenBusiness.finalizar(orden);	
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}	catch (WrongStateException e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@ApiOperation(value="Borrar una orden indicando su ID", response = Orden.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra la orden"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			ordenBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value="Obtener la conciliación de una orden", response = Orden.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 400, message = "Algun valor ingresado es incorrecto"),
			@ApiResponse(code = 404, message = "No se encuentra la orden"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@GetMapping(value = "/conciliacion/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConciliacionDTO> getConciliacion(@PathVariable("id") Long id) {
		
		try {
			return new ResponseEntity<ConciliacionDTO>(ordenBusiness.getConciliacion(id),HttpStatus.OK); 
		}catch (NotFoundException e) {
			return new ResponseEntity<ConciliacionDTO>(HttpStatus.NOT_FOUND);
		}catch (BusinessException e) {
			return new ResponseEntity<ConciliacionDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (WrongStateException e) {
			return new ResponseEntity<ConciliacionDTO>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(value = "/conciliacion/ce/{ce}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConciliacionDTO> getConciliacion(@PathVariable("ce") String ce) {
		
		try {
			return new ResponseEntity<ConciliacionDTO>(ordenBusiness.getConciliacion(ce),HttpStatus.OK); 
		}catch (NotFoundException e) {
			return new ResponseEntity<ConciliacionDTO>(HttpStatus.NOT_FOUND);
		}catch (BusinessException e) {
			return new ResponseEntity<ConciliacionDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (WrongStateException e) {
			return new ResponseEntity<ConciliacionDTO>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}
