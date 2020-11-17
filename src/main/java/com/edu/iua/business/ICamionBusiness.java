package com.edu.iua.business;

import java.util.List;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Camion;


public interface ICamionBusiness {

	 Camion load(Long id) throws BusinessException, NotFoundException;

	 List<Camion> list() throws BusinessException;

	 Camion save(Camion camion) throws BusinessException, IllegalArgumentException;

	 void delete(Long id) throws BusinessException, NotFoundException;
	
	 Camion findByCodigoExterno(String c)throws NotFoundException, BusinessException;
	 
}
