package com.edu.iua.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Camion;
import com.edu.iua.model.Cisterna;
import com.edu.iua.model.persistence.CamionRepository;
import com.edu.iua.model.persistence.CisternaRepository;

@Service
public class CamionBusiness implements ICamionBusiness{

	@Autowired
	private CamionRepository camionDAO;
	
	@Autowired 
	private CisternaRepository cisternaDAO;
	
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
			List<Cisterna> cisternasRecibidas = camion.getCisternado();
			List<Cisterna> cisternas = new ArrayList<Cisterna>();
			for(Cisterna c: cisternasRecibidas) {
				Optional<Cisterna> opCisterna = cisternaDAO.findByCodigoExterno(c.getCodigoExterno());
				if(!opCisterna.isPresent())
					if(c.getCapacidad()==null) {
						throw new NotFoundException("No se encuentra la cisterna con el código externo"+ c.getCodigoExterno());
					}else {
						cisternas.add(cisternaDAO.save(new Cisterna(c.getCapacidad(),c.getCodigoExterno())));
					}
				else cisternas.add(opCisterna.get());	
			}
			camion.setCisternado(cisternas);
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
