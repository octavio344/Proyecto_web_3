package com.edu.iua.tpintegrador.business;

import java.util.List;

import com.edu.iua.tpintegrador.business.exception.BusinessException;
import com.edu.iua.tpintegrador.business.exception.NotFoundException;
import com.edu.iua.tpintegrador.model.Camion;


public interface ICamionBusiness {

	public Camion load(String patente) throws BusinessException, NotFoundException;

	public List<Camion> list() throws BusinessException;

	public Camion save(Camion camion) throws BusinessException;

	public void delete(String patente) throws BusinessException, NotFoundException;
	
}
