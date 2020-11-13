package com.edu.iua.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Chofer;
import com.edu.iua.model.persistence.ChoferRepository;

@Service
public class ChoferBusiness implements IChoferBusiness{

	@Autowired
	private ChoferRepository choferDAO;
	
	@Override
	public Chofer load(Long id) throws BusinessException, NotFoundException {
		Optional<Chofer> op;
		try {
			op = choferDAO.findById(id);

		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent())
			throw new NotFoundException("No se encuentra el chofer con Id =" + id);
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
	public void delete(Long id) throws BusinessException, NotFoundException {
		try {
			choferDAO.deleteById(id);
		} catch (EmptyResultDataAccessException e1) {
			throw new NotFoundException("No se encuentra el chofer con el Id =" + id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	
	@Override
	public Chofer findByCodigoExterno(String c) throws NotFoundException, BusinessException {
		
		Optional<Chofer> op=choferDAO.findByCodigoExterno(c);
		
		if(!op.isPresent()) {
			throw new NotFoundException("No se encuentra el chofer con el codigo externo =" + c);
		}
		
		return op.get();
	}
	

}
