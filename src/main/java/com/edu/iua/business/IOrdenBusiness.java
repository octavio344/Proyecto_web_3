package com.edu.iua.business;

import java.util.List;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.business.exception.WrongStateException;
import com.edu.iua.model.ConciliacionDTO;
import com.edu.iua.model.Orden;

public interface IOrdenBusiness {

	List<Orden> listAll() throws BusinessException;
	
	Orden findById(Long id)throws NotFoundException, BusinessException;
	
	void delete(Long id) throws BusinessException, NotFoundException;
	
	Orden add(Orden o) throws BusinessException, IllegalArgumentException;
	
	Orden setearPesajeInicial(Orden o) throws BusinessException, NotFoundException,WrongStateException;
	
	Orden updateDetalle(Orden o) throws BusinessException, NotFoundException,WrongStateException;
	
	Orden cerrarOrden(Orden o) throws BusinessException, NotFoundException,WrongStateException;
	
	ConciliacionDTO finalizar(Orden o) throws BusinessException, NotFoundException,WrongStateException;
	
	Orden findByCodigoExterno(String p)throws NotFoundException, BusinessException;
	
	ConciliacionDTO getConciliacion(Orden o) throws BusinessException, NotFoundException, WrongStateException;
	
}
