package com.edu.iua.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.iua.authtoken.IAuthTokenBusiness;
import com.edu.iua.business.UserBusiness;
import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Alarma;
import com.edu.iua.model.User;
import com.edu.iua.rest.Constantes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping()
@Api(value = "Servicios de autenticación", description = "Operaciones relacionadas con la autenticación de usuarios y obtención de tokens", tags = { "Autenticación" })
public class CoreRestController extends BaseRestController{
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserBusiness userBusiness;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@ApiOperation(value="Obtener la informacion del token de un usuario pasando su nombre de usaurio y contraseña", response = Alarma.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra el usuario"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@PostMapping(value = "/login-token", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> loginToken(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		try {
			User u = userBusiness.load(username);
			String msg = u.checkAccount(passwordEncoder, password);
			if (msg != null) {
				return new ResponseEntity<String>(msg, HttpStatus.UNAUTHORIZED);
			} else {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(u, null,
						u.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
				return new ResponseEntity<String>(userToJson(getUserLogged()).get("authtoken").toString(),
						HttpStatus.OK);
			}
		} catch (BusinessException e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>("BAD_ACCOUNT_NAME", HttpStatus.UNAUTHORIZED);
		}

	}
	
	
	@ApiOperation(value="Obtener la informacion de un usuario pasando su nombre de usaurio y contraseña", response = Alarma.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 404, message = "No se encuentra el usuario"), 
			@ApiResponse(code = 500, message = "Error interno del servidor") 
	})
	
	@PostMapping(value = "/login-user", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> loginUser(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		try {
			User u = userBusiness.load(username);
			String msg = u.checkAccount(passwordEncoder, password);
			if (msg != null) {
				return new ResponseEntity<String>(msg, HttpStatus.UNAUTHORIZED);
			} else {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(u, null,
						u.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
				return new ResponseEntity<String>(userToJson(getUserLogged()).toString(),
						HttpStatus.OK);
			}
		} catch (BusinessException e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>("BAD_ACCOUNT_NAME", HttpStatus.UNAUTHORIZED);
		}

	}

	@ApiOperation(value="Obtener la información del usuario pasando el token correspondiente con el mismo", response = Alarma.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
	})
	
	@GetMapping(value = Constantes.URL_AUTH_INFO)
	public ResponseEntity<String> authInfo() {
		return new ResponseEntity<String>(userToJson(getUserLogged()).toString(), HttpStatus.OK);
	}

	@Autowired
	private IAuthTokenBusiness authTokenBusiness;

	
	@ApiOperation(value="Eliminar token de inicio de sesión del usuario logeado", response = Alarma.class)

	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Operación exitosa"),
			@ApiResponse(code = 500, message = "Error interno del servidor")
	})
	
	@GetMapping(value = Constantes.URL_LOGOUT)
	public ResponseEntity<String> logout() {
		try {
			User u = getUserLogged();
			if (u != null) {
				authTokenBusiness.delete(u.getSessionToken());
			}
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
