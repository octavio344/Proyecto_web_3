package com.edu.iua.business;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.Mockito;

import java.util.Optional;

import com.edu.iua.business.ChoferBusiness;
import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Chofer;
import com.edu.iua.model.persistence.ChoferRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChoferBusinessTest {
	
	
	@Autowired
	ChoferBusiness ChoferBusiness;


    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    
    @MockBean
    ChoferRepository ChoferMock;
    
    private static Chofer chofer;
    
    @Before
    public void setup() {
     chofer= new Chofer();
     
     chofer.setDni((long) 39111111);
     chofer.setApellido("Perez");
     chofer.setNombre("Pepe");
     chofer.setCodigoExterno("zdgy2xpHlZ");
   
    }
    
    @Test
    public void testLoadSuccess() throws BusinessException, NotFoundException {
    	 long dni = 39111111;
    	
        Mockito.when(ChoferMock.findById(dni)).thenReturn(Optional.ofNullable(chofer));
        
        Chofer choferReceived = ChoferBusiness.load(dni);

        assertEquals(chofer.getApellido(), choferReceived.getApellido());
    }
    
    @Test
    public void testLoadFailure() throws  BusinessException, NotFoundException  {
     	 long dni = 39111111;

         Mockito.when(ChoferMock.findById(dni)).thenReturn(Optional.ofNullable(chofer));
         
         Chofer choferReceived = ChoferBusiness.load(dni);

        assertNotEquals("Guzman", choferReceived.getApellido());
    } 
    
    @Test(expected = com.edu.iua.business.exception.NotFoundException.class)
    public void testLoadNotFoundException() throws  BusinessException, NotFoundException  {
    	 long dni = 00000002;
    	 ChoferBusiness.load(dni);
        expectedEx.expect(com.edu.iua.business.exception.NotFoundException.class);
        expectedEx.expectMessage("No se encuentra el chofer con el dni ="+dni);
    }
    
    @Test
    public void testfindByCodigoExternoSuccess() throws BusinessException, NotFoundException {
   	 String codigoExterno = "zdgy2xpHlZ";
   	
       Mockito.when(ChoferMock.findByCodigoExterno(codigoExterno)).thenReturn(Optional.ofNullable(chofer));
       
       Chofer choferReceived = ChoferBusiness.findByCodigoExterno(codigoExterno);

       assertEquals(chofer.getDni(), choferReceived.getDni());
   }
    
    @Test
    public void testSaveSuccess() throws BusinessException, NotFoundException {
        Mockito.when(ChoferMock.save(chofer)).thenReturn(chofer);
        
        Chofer choferReceived = ChoferMock.save(chofer);

        assertEquals(chofer.getDni(), choferReceived.getDni());
    }
    
    
}
