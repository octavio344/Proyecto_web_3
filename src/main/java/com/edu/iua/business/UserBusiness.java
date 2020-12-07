package com.edu.iua.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.Rol;
import com.edu.iua.model.User;
import com.edu.iua.model.persistence.RolRepository;
import com.edu.iua.model.persistence.UserRepository;

@Service
public class UserBusiness implements IUserBusiness {

	@Autowired
	private UserRepository userDAO;
	
	@Autowired
	private RolRepository rolDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User load(Long id) throws NotFoundException, BusinessException {
		Optional<User> op;
		try {
			op = userDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("El user con id " + id + " no se encuentra en la BD");
		}
		return op.get();
	}

	@Override
	public List<User> list() throws BusinessException {

		try {
			return userDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public User add(User user) throws BusinessException {
		try {
			
			Optional<Rol> op=rolDAO.findById(user.getRolPrincipal().getId());
			
			if(!op.isPresent()) {
				throw new NotFoundException("No se encuentra el rol con el id: "+user.getRolPrincipal().getId());
			}

			user.setRolPrincipal(op.get());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return userDAO.save(user);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public User update(User user) throws NotFoundException, BusinessException {
		load(user.getId());
		return add(user);
	}

	@Override
	public User load(String nameOrEmail) throws NotFoundException, BusinessException {
		User user = null;
		try {
			user = userDAO.findByUsernameOrEmail(nameOrEmail, nameOrEmail);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (user == null) {
			throw new NotFoundException("No se encuentra el user con nombre o email =" + nameOrEmail);
		}
		return user;
	}

}