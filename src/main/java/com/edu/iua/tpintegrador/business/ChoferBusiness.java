package com.edu.iua.tpintegrador.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edu.iua.tpintegrador.business.exception.BusinessException;
import com.edu.iua.tpintegrador.business.exception.NotFoundException;
import com.edu.iua.tpintegrador.model.Chofer;
import com.edu.iua.tpintegrador.persistance.ChoferRepository;

@Service
public class ChoferBusiness implements IChoferBusiness{

	@Autowired
	private ChoferRepository choferDAO;
	
	@Override
	public Chofer load(Long DNI) throws BusinessException, NotFoundException {
		Optional<Chofer> op;
		try {
			op = choferDAO.findById(DNI);

		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent())
			throw new NotFoundException("No se encuentra el chofer con DNI =" + DNI);
		return op.get();
	}

	@Override
	public List<Chofer> list() throws BusinessException {
		try {
			return choferDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Chofer save(Chofer chofer) throws BusinessException {
		try {
			return choferDAO.save(chofer);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(Long DNI) throws BusinessException, NotFoundException {
		try {
			choferDAO.deleteById(DNI);
		} catch (EmptyResultDataAccessException e1) {
			throw new NotFoundException("No se encuentra el chofer con DNI =" + DNI);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	
	

}
