package com.edu.iua.RestControllerTest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.edu.iua.business.CamionBusiness;
import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.model.Camion;
import com.edu.iua.model.Cisterna;
import com.edu.iua.model.persistence.CamionRepository;
import com.edu.iua.rest.CamionesRestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CamionesRestController.class)
public class CamionRestControllerTest {

	  @Autowired
	    private MockMvc mvc;

	   @MockBean
	   private CamionBusiness camionBusiness; 
	   
	   @MockBean
	   private CamionesRestController camionMock; 
	   
	    private static Camion camion;
	    private static Cisterna cisterna;
	    
	    @BeforeClass
	    public static void setup() {
	    	
	    	cisterna= new Cisterna();
	    	cisterna.setCodigoExterno("fyHGuO7XBh");
	    	cisterna.setCapacidad( (double) 500);
	    	List<Cisterna> cisternas = new ArrayList<Cisterna>();
	        cisternas.add(cisterna);
	    	camion= new Camion();
	    	camion.setPatente("AAA000");
	    	camion.setCodigoExterno("c3svZYkLJF");
	    	camion.setDescripcion("Camion Ford");
	    	camion.setCisternado(cisternas);
	    }
	    
	    @Test
	    public void testListSuccess() throws BusinessException, Exception {
	    	List<Camion> allCamiones= new ArrayList<Camion>();
	    	allCamiones.add(camion);
	    	
	    	 when(camionBusiness.list()).thenReturn(allCamiones);
	    	 
	    	 mvc.perform(get("/api/v1/camiones")
             .contentType(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$", hasSize(1)))
             .andExpect((ResultMatcher) jsonPath("$[0].id", camion.getIdCamion().intValue()));
	    }
	    

	    @Test
	    public void testLoadByidSuccess() throws BusinessException, Exception {
	    	Camion camionDescContains = camion;
	    	Long id = (long) 1;
	    	
	    	when(camionBusiness.load(id)).thenReturn(camionDescContains);
	    	
	    	mvc.perform(get("/api/v1/camiones/id")
	    			.param("id", "1")
	    			.contentType(MediaType.APPLICATION_JSON))
	    			.andExpect(status().isOk())
	    			.andExpect(jsonPath(("$.id"),  is(camion.getIdCamion()), Long.class));
	    	
	    	
	    }
	    
	    @Test
	    public void testLoadByCodigoExternoSuccess() throws BusinessException, Exception {
	    	Camion camionDescContains = camion;
	    	String codigoExterno = "c3svZYkLJF";
	    	
	    	when(camionBusiness.findByCodigoExterno(codigoExterno)).thenReturn(camionDescContains);
	    	
	    	mvc.perform(get("/api/v1/camiones/ce")
	    			.param("ce", codigoExterno)
	    			.contentType(MediaType.APPLICATION_JSON))
	    			.andExpect(status().isOk())
	    			.andExpect((ResultMatcher) jsonPath(("$.ce"),  is(camion.getCodigoExterno()), Long.class));
	    	
	    	
	    }
	
}
