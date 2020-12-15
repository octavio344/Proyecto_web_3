package com.edu.iua.business;

import java.util.List;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.CanceledOrderException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.business.exception.WrongStateException;
import com.edu.iua.model.ConciliacionDTO;
import com.edu.iua.model.Orden;

public interface IOrdenBusiness {

	List<Orden> listAll() throws BusinessException;
	
	Orden findById(Long id)throws NotFoundException, BusinessException;
	
	Orden add(Orden o) throws BusinessException, IllegalArgumentException;
	
	Orden setearPesajeInicial(Orden o) throws BusinessException, NotFoundException,WrongStateException, CanceledOrderException;
	
	Orden updateDetalle(Orden o) throws BusinessException, NotFoundException,WrongStateException, CanceledOrderException;
	
	Orden cerrarOrden(Orden o) throws BusinessException, NotFoundException,WrongStateException, CanceledOrderException;
	
	ConciliacionDTO finalizar(Orden o) throws BusinessException, NotFoundException,WrongStateException, CanceledOrderException;
	
	Orden findByCodigoExterno(String p)throws NotFoundException, BusinessException;
	
	ConciliacionDTO getConciliacion(Long id) throws BusinessException, NotFoundException, WrongStateException;
	
	ConciliacionDTO getConciliacion(String codigoExterno) throws BusinessException, NotFoundException, WrongStateException;
	
	Orden anularOrden(Long id) throws BusinessException,NotFoundException;
	
	public void pushOrderData();
	
	public void cambiarUmbralTemperatura(Float temp);
	
}
