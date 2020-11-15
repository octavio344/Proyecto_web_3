package com.edu.iua.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Cisterna;
import com.edu.iua.model.persistence.CisternaRepository;

@Service
public class CisternaBusiness implements ICisternaBusiness{

	@Autowired
	private CisternaRepository CisternaDAO;
	
	@Override
	public Cisterna load(Long id) throws BusinessException, NotFoundException {
		Optional<Cisterna> op;
		try {
			op = CisternaDAO.findById(id);

		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent())
			throw new NotFoundException("No se encuentra la Cisterna con Id =" + id);
		return op.get();
	}

	@Override
	public List<Cisterna> list() throws BusinessException {
		try {
			return CisternaDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Cisterna save(Cisterna Cisterna) throws BusinessException {
		try {
			return CisternaDAO.save(Cisterna);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(Long id) throws BusinessException, NotFoundException {
		try {
			CisternaDAO.deleteById(id);
		} catch (EmptyResultDataAccessException e1) {
			throw new NotFoundException("No se encuentra la Cisterna con el Id =" + id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	
	@Override
	public Cisterna findByCodigoExterno(String c) throws NotFoundException, BusinessException {
		
		Optional<Cisterna> op=CisternaDAO.findByCodigoExterno(c);
		
		if(!op.isPresent()) {
			throw new NotFoundException("No se encuentra la Cisterna con el codigo externo =" + c);
		}
		
		return op.get();
	}
	

}
