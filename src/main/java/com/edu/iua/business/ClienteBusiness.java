package com.edu.iua.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Cliente;
import com.edu.iua.model.persistence.ClienteRepository;

@Service
public class ClienteBusiness implements IClienteBusiness {

	
	@Autowired
	private ClienteRepository clienteDAO;
	
	@Override
	public List<Cliente> listAll() throws BusinessException {
		try {
			return clienteDAO.findAll();
		}catch(Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Cliente findById(Long long1) throws NotFoundException, BusinessException {
		Optional<Cliente> op;
		
		try {
			op = clienteDAO.findById(long1);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
		
		if(!op.isPresent()) {
			throw new NotFoundException("No se encontro ningun cliente con el siguiente identificador: "+ long1);
		}
		
		return op.get();
	}

	@Override
	public void delete(Long id) throws BusinessException, NotFoundException {
		try {		
		clienteDAO.deleteById(id);
		}catch (EmptyResultDataAccessException e1) {
			throw new NotFoundException("No se encuentra el cliente con el identificador:" + id);
			}
		catch(Exception e) {
			throw new BusinessException(e);
		}

	
	}

	@Override
	public Cliente add(Cliente c) throws BusinessException {
		try {
			System.out.println(c.toString());
			return clienteDAO.save(c);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Cliente update(Cliente c) throws BusinessException, NotFoundException {
		Cliente cli = findById(c.getIdCliente());
		
		if(cli==null) {
			throw new NotFoundException("No se encuentra el cliente con el identificador:" + c.getIdCliente());
		}
		
		if(c.getContacto()!= null || c.getContacto().length()> 0) {
			cli.setContacto(c.getContacto());
		}
		if(c.getNumTelefono()!= null || c.getNumTelefono()> 0) {
			cli.setNumTelefono(c.getNumTelefono());
		}
		if(c.getRazonSocial()!= null || c.getRazonSocial().length()> 0) {
			cli.setRazonSocial(c.getRazonSocial());
		}
		
		try {
		return clienteDAO.save(cli);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
	
		
		
	}
	
	@Override
	public Cliente findByCodigoExterno(String c) throws NotFoundException, BusinessException {
		
		Optional<Cliente> op=clienteDAO.findByCodigoExterno(c);
		
		if(!op.isPresent()) {
			throw new NotFoundException("No se encuentra el camion con el codigo externo =" + c);
		}
		
		return op.get();
	}

}
