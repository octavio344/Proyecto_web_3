package com.edu.iua.tpintegrador.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edu.iua.tpintegrador.business.exception.BusinessException;
import com.edu.iua.tpintegrador.business.exception.NotFoundException;
import com.edu.iua.tpintegrador.model.Producto;
import com.edu.iua.tpintegrador.model.persistence.ProductoRepository;

@Service
public class ProductoBusiness implements IProductoBusiness {

	
	@Autowired
	private ProductoRepository productoDAO;
	
	
	@Override
	public List<Producto> listAll() throws BusinessException {
		try {
			return productoDAO.findAll();
		}catch(Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Producto findById(Long id) throws NotFoundException, BusinessException {
		Optional<Producto> op;
		
		try {
			op = productoDAO.findById(id);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
		
		if(!op.isPresent()) {
			throw new NotFoundException("No se encontro ningun producto con el siguiente identificador: "+ id);
		}
		
		return op.get();
	}

	@Override
	public void delete(Long id) throws BusinessException, NotFoundException {
		try {		
			productoDAO.deleteById(id);
			}catch (EmptyResultDataAccessException e1) {
				throw new NotFoundException("No se encuentra el producto con el identificador:" + id);
				}
			catch(Exception e) {
				throw new BusinessException(e);
			}
	}

	@Override
	public Producto add(Producto p) throws BusinessException {
		try {
			return productoDAO.save(p);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Producto update(Producto p) throws BusinessException, NotFoundException {
		Producto pro=findById(p.getId());
		
		if(pro==null) {
			throw new NotFoundException("No se encuentra el cliente con el identificador:" + p.getId());
		}
		
		if(p.getDescripcion()!= null || p.getDescripcion().length()> 0) {
			pro.setDescripcion(p.getDescripcion());
		}
		if(p.getNombre()!= null || p.getNombre().length()> 0) {
			pro.setNombre(p.getNombre());
		}
		if(p.getPrecio()!= null || p.getPrecio()> 0) {
			pro.setPrecio(p.getPrecio());
		}
		
		try {
		return productoDAO.save(pro);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
