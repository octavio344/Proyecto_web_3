package com.edu.iua.business;

import java.util.List;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Chofer;



public interface IChoferBusiness {

	 Chofer load(Long id) throws BusinessException, NotFoundException;

	 List<Chofer> list() throws BusinessException;

	 Chofer save(Chofer chofer) throws BusinessException, IllegalArgumentException;

	 void delete(Long id) throws BusinessException, NotFoundException;
	
	 Chofer findByCodigoExterno(String c)throws NotFoundException, BusinessException;
}
