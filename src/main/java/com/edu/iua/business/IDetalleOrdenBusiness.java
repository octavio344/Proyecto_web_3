package com.edu.iua.business;

import java.util.List;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.DetalleOrden;

public interface IDetalleOrdenBusiness {

	List<DetalleOrden> listAll() throws BusinessException;
	
	DetalleOrden findById(Long id)throws NotFoundException, BusinessException;
	
	void delete(Long id) throws BusinessException, NotFoundException;
	
	DetalleOrden add(DetalleOrden d) throws BusinessException;
	
	//DetalleOrden update(DetalleOrden d) throws BusinessException, NotFoundException;
	
}
