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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.iua.business.IProductoBusiness;
import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Camion;
import com.edu.iua.model.Producto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/productos")
@Api(value = "Productos", description = "Operaciones relacionadas con los productos", tags = { "Productos" })
public class ProductoRestController {

	//private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private IProductoBusiness productoBusiness;
	
	@ApiOperation(value="Obtener un producto mediante su ID", response = Producto.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra el producto"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> load(@PathVariable("id") Long id) {
		
		try {
			return new ResponseEntity<Producto>(productoBusiness.findById(id),HttpStatus.OK); 
		}catch (NotFoundException e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}catch (BusinessException e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value="Obtener un producto mediante su Código Externo", response = Producto.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra el producto"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@GetMapping(value = "/ce/{codigoExterno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> findByCodigoExterno(@PathVariable("codigoExterno") String codigoExterno) {
		
		try {
			return new ResponseEntity<Producto>(productoBusiness.findByCodigoExterno(codigoExterno),HttpStatus.OK); 
		}catch (NotFoundException e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}catch (BusinessException e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value="Obtener todos los productos almacenados en la base de datos", response = Producto.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> list(){
	
		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.listAll(),HttpStatus.OK);
		}catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
	@ApiOperation(value="Guardar un nuevo producto en la base de datos", response = Producto.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 400, message = "Algun valor ingresado es incorrecto"),
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})	
	
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Producto p){
		try {
			productoBusiness.add(p);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_PRODUCTOS + "/" +p.getId());
			return new ResponseEntity<String>(responseHeaders,HttpStatus.OK);
		}catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value="Actualizar los datos de un producto", response = Producto.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra el producto"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody Producto producto) {
		try {
			productoBusiness.update(producto);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value="Borrar un producto indicando su ID", response = Producto.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra el producto"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			productoBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
}
