package com.edu.iua.business;

import java.util.List;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Producto;


public interface IProductoBusiness {
	
	List<Producto> listAll() throws BusinessException;
	
	Producto findById(Long id)throws NotFoundException, BusinessException;
	
	void delete(Long id) throws BusinessException, NotFoundException;
	
	Producto add(Producto p) throws BusinessException,IllegalArgumentException;
	
	Producto update(Producto p) throws BusinessException, NotFoundException;

	Producto findByCodigoExterno(String p)throws NotFoundException, BusinessException;

}
