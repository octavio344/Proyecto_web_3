package com.edu.iua.tpintegrador.business;

import java.util.List;

import com.edu.iua.tpintegrador.business.exception.BusinessException;
import com.edu.iua.tpintegrador.business.exception.NotFoundException;
import com.edu.iua.tpintegrador.model.Cliente;

public interface IClienteBusiness {
	
	List<Cliente> listAll() throws BusinessException;
	
	Cliente findById(Long id)throws NotFoundException, BusinessException;
	
	void delete(Long id) throws BusinessException, NotFoundException;
	
	Cliente add(Cliente c) throws BusinessException;
	
	Cliente update(Cliente c) throws BusinessException, NotFoundException;

}
