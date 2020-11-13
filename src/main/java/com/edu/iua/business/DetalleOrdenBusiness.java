package com.edu.iua.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.DetalleOrden;
import com.edu.iua.model.persistence.DetalleOrdenRepository;

@Service
public class DetalleOrdenBusiness implements IDetalleOrdenBusiness {

	
	@Autowired
	private DetalleOrdenRepository detalleDAO;
	
	@Override
	public List<DetalleOrden> listAll() throws BusinessException {
		try {
			return detalleDAO.findAll();
		}catch(Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public DetalleOrden findById(Long id) throws NotFoundException, BusinessException {
		Optional<DetalleOrden> op;
		
		try {
			op = detalleDAO.findById(id);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
		
		if(!op.isPresent()) {
			throw new NotFoundException("No se encontro ningun cliente con el siguiente identificador: "+ id);
		}
		
		return op.get();
	}

	@Override
	public void delete(Long id) throws BusinessException, NotFoundException {
		try {		
			detalleDAO.deleteById(id);
			}catch (EmptyResultDataAccessException e1) {
				throw new NotFoundException("No se encuentra el cliente con el identificador:" + id);
				}
			catch(Exception e) {
				throw new BusinessException(e);
			}
	}

	@Override
	public DetalleOrden add(DetalleOrden d) throws BusinessException {
		try {
			return detalleDAO.save(d);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	

	

}
