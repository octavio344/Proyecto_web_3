package com.edu.iua.tpintegrador.business;

import java.util.List;

import com.edu.iua.tpintegrador.business.exception.BusinessException;
import com.edu.iua.tpintegrador.business.exception.NotFoundException;
import com.edu.iua.tpintegrador.model.Producto;


public interface IProductoBusiness {
	
	List<Producto> listAll() throws BusinessException;
	
	Producto findById(Long id)throws NotFoundException, BusinessException;
	
	void delete(Long id) throws BusinessException, NotFoundException;
	
	Producto add(Producto p) throws BusinessException;
	
	Producto update(Producto p) throws BusinessException, NotFoundException;


}
