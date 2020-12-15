package com.edu.iua.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Alarma;
import com.edu.iua.model.Orden;
import com.edu.iua.model.User;
import com.edu.iua.model.persistence.AlarmaRepository;
import com.edu.iua.model.persistence.OrdenRepository;
import com.edu.iua.model.persistence.UserRepository;

@Service
public class AlarmaBusiness implements IAlarmaBusiness{

	@Autowired
	private AlarmaRepository alarmaDAO;
	
	@Autowired
	private UserRepository userDAO;
	
	@Autowired
	private OrdenRepository ordenDAO;
	
	@Override
	public Alarma load(Long id) throws BusinessException, NotFoundException {
		Optional<Alarma> op;
		try {
			op = alarmaDAO.findById(id);

		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent())
			throw new NotFoundException("No se encuentra la alarma con Id =" + id);
		return op.get();
	}

	@Override
	public List<Alarma> list() throws BusinessException {
		try {
			return alarmaDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Alarma save(Alarma alarma) throws BusinessException {
		try {
			Optional<User> u = userDAO.findById(alarma.getUsuarioQueAcepto().getId());
			Optional<Orden> o = ordenDAO.findById(alarma.getOrden().getNroOrden());
			if(u.isPresent() && o.isPresent()) {
				Orden or = o.get();
				or.setTieneAlarmaEncendida(false);
				ordenDAO.save(or);
				alarma.setUsuarioQueAcepto(u.get());
				alarma.setOrden(or);
				alarma.setFechaAceptacion(new Date());
				return alarmaDAO.save(alarma);
			}
			else throw new NotFoundException();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(Long id) throws BusinessException, NotFoundException {
		try {
			alarmaDAO.deleteById(id);
		} catch (EmptyResultDataAccessException e1) {
			throw new NotFoundException("No se encuentra la alarma con el Id =" + id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	
}
