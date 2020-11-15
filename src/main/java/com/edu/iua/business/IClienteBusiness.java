package com.edu.iua.business;

import java.util.List;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Cliente;

public interface IClienteBusiness {
	
	List<Cliente> listAll() throws BusinessException;
	
	Cliente findById(Long id)throws NotFoundException, BusinessException;
	
	void delete(Long id) throws BusinessException, NotFoundException;
	
	Cliente add(Cliente c) throws BusinessException, IllegalArgumentException;
	
	Cliente update(Cliente c) throws BusinessException, NotFoundException;
	
	Cliente findByCodigoExterno(String c)throws NotFoundException, BusinessException;

}
