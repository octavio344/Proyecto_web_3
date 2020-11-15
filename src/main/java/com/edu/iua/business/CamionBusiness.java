package com.edu.iua.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Camion;
import com.edu.iua.model.persistence.CamionRepository;

@Service
public class CamionBusiness implements ICamionBusiness{

	@Autowired
	private CamionRepository camionDAO;
	
	@Override
	public Camion load(Long id) throws BusinessException, NotFoundException {
		Optional<Camion> op;
		try {
			op = camionDAO.findById(id);

		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent())
			throw new NotFoundException("No se encuentra el camion con el id =" + id);
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
	public void delete(Long id) throws BusinessException, NotFoundException {
		try {
			camionDAO.deleteById(id);
		} catch (EmptyResultDataAccessException e1) {
			throw new NotFoundException("No se encuentra el camion con el id =" + id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Camion findByCodigoExterno(String c) throws NotFoundException, BusinessException {
		
		Optional<Camion> op=camionDAO.findByCodigoExterno(c);
		
		if(!op.isPresent()) {
			throw new NotFoundException("No se encuentra el camion con el codigo externo =" + c);
		}
		
		return op.get();
	}
	
	

}
