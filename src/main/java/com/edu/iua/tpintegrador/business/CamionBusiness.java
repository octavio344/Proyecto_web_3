package com.edu.iua.tpintegrador.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edu.iua.tpintegrador.business.exception.BusinessException;
import com.edu.iua.tpintegrador.business.exception.NotFoundException;
import com.edu.iua.tpintegrador.model.Camion;
import com.edu.iua.tpintegrador.persistance.CamionRepository;

@Service
public class CamionBusiness implements ICamionBusiness{

	@Autowired
	private CamionRepository camionDAO;
	
	@Override
	public Camion load(String patente) throws BusinessException, NotFoundException {
		Optional<Camion> op;
		try {
			op = camionDAO.findById(patente);

		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent())
			throw new NotFoundException("No se encuentra el camion con patente =" + patente);
		return op.get();
	}

	@Override
	public List<Camion> list() throws BusinessException {
		try {
			return camionDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Camion save(Camion camion) throws BusinessException {
		try {
			return camionDAO.save(camion);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(String patente) throws BusinessException, NotFoundException {
		try {
			camionDAO.deleteById(patente);
		} catch (EmptyResultDataAccessException e1) {
			throw new NotFoundException("No se encuentra el camion con patente =" + patente);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	
	

}
