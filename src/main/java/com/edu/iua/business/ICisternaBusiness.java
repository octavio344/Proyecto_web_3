package com.edu.iua.business;

import java.util.List;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Cisterna;

public interface ICisternaBusiness {

	 Cisterna load(Long id) throws BusinessException, NotFoundException;

	 List<Cisterna> list() throws BusinessException;

	 Cisterna save(Cisterna Cisterna) throws BusinessException, IllegalArgumentException;

	 void delete(Long id) throws BusinessException, NotFoundException;
	
	 Cisterna findByCodigoExterno(String c)throws NotFoundException, BusinessException;
	 
}
