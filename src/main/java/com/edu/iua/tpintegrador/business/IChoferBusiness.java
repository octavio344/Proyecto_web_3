package com.edu.iua.tpintegrador.business;

import java.util.List;

import com.edu.iua.tpintegrador.business.exception.BusinessException;
import com.edu.iua.tpintegrador.business.exception.NotFoundException;
import com.edu.iua.tpintegrador.model.Chofer;


public interface IChoferBusiness {

	public Chofer load(Long dni) throws BusinessException, NotFoundException;

	public List<Chofer> list() throws BusinessException;

	public Chofer save(Chofer chofer) throws BusinessException;

	public void delete(Long dni) throws BusinessException, NotFoundException;
	
}
