package com.edu.iua.RestControllerTest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.edu.iua.SpringFoxConfig;
import com.edu.iua.authtoken.AuthToken;
import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Camion;
import com.edu.iua.model.Cisterna;
import com.edu.iua.model.Rol;
import com.edu.iua.model.User;
import com.edu.iua.rest.CamionesRestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@WebMvcTest(CamionesRestController.class)
@ContextConfiguration(classes = { SpringFoxConfig.class })
public class CamionRestControllerTest {
		
	   
	   	@Autowired
		private MockMvc mvc;
	   
		@MockBean
		private CamionesRestController camionMock; 
	   
	    private static Camion camion;
	    private static Cisterna cisterna;
	    
	    @SuppressWarnings("deprecation")
		@BeforeClass
	    public static void setup() throws NotFoundException, BusinessException {
	    	
	    	cisterna= new Cisterna();
	    	cisterna.setCodigoExterno("fyHGuO7XBh");
	    	cisterna.setCapacidad( (double) 500);
	    	List<Cisterna> cisternas = new ArrayList<Cisterna>();
	        cisternas.add(cisterna);
	    	camion= new Camion();
	    	camion.setIdCamion(new Long(1));
	    	camion.setPatente("AAA000");
	    	camion.setCodigoExterno("c3svZYkLJF");
	    	camion.setDescripcion("Camion Ford");
	    	camion.setCisternado(cisternas);
	    	
	    }
	  
	    @Test
	    public void testListSuccess() throws BusinessException, Exception {
	    	
	    	String token = getToken();
	    	
	    	List<Camion> allCamiones= new ArrayList<Camion>();
	    	allCamiones.add(camion);
	    	
	    	 when(camionMock.list()).thenReturn(ResponseEntity.ok(allCamiones));
	    	 
	    	 mvc.perform(get("/api/v1/camiones")
			 .param("xauthtoken", token)
             .contentType(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$[0].idCamion", is(Integer.parseInt(camion.getIdCamion().toString()))));
	    }
	    

	    @Test
	    public void testLoadByidSuccess() throws BusinessException, Exception {
	    	String token = getToken();
	    	
	    	Camion camionDescContains = camion;
	    	Long id = (long) 1;
	    	
	    	when(camionMock.load(id)).thenReturn(ResponseEntity.ok(camionDescContains));
	    	
	    	mvc.perform(get("/api/v1/camiones/"+id)
	    			.param("xauthtoken", token)
	    			.contentType(MediaType.APPLICATION_JSON))
	    			.andDo(print())
	    			.andExpect(status().isOk())
	    			.andExpect(jsonPath(("$.idCamion"),  is(camion.getIdCamion()), Long.class));
	    	
	    }
	    
	    @Test
	    public void testLoadByCodigoExternoSuccess() throws BusinessException, Exception {
	    	
	    	String token = getToken();
	    	
	    	Camion camionDescContains = camion;
	    	String codigoExterno = "c3svZYkLJF";
	    	
	    	when(camionMock.findByCodigoExterno(codigoExterno)).thenReturn(ResponseEntity.ok(camionDescContains));
	    	
	    	mvc.perform(get("/api/v1/camiones/ce/"+codigoExterno)
	    			.param("xauthtoken", token)
	    			.contentType(MediaType.APPLICATION_JSON))
	    			.andExpect(status().isOk());
	    	
	    	
	    }
	    
	    @SuppressWarnings("deprecation")
		private String getToken() {
	    	Rol admin = new Rol(1, "Admin", "Administrador del sistema");
	    	User u = new User(new Long(1), "Alan", "Alberino", "alanalberino@gmail.com", "123", "alanalberino", admin, true);
	    	u.setRoles(new HashSet<Rol>());
	    	u.getRoles().add(admin);
	    	
	    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(u, null,
					u.getAuthorities());
	    	
	    	
	    	SecurityContextHolder.getContext().setAuthentication(auth);
	    	
	    	Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
			u = (User) auth1.getPrincipal();
	    
			AuthToken newToken = new AuthToken(u.getSessionTimeout(), u.getUsername());
			
			String token = newToken.encodeCookieValue();
	    	token = token.replace("[", "").replace("]", "");
	    	
	    	
	    	
	    	return token;
	    }

}
