package com.edu.iua.business;


import java.util.List;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.User;

public interface IUserBusiness {

	public User load(Long id) throws NotFoundException, BusinessException;

	public List<User> list() throws BusinessException;
	
	public User add(User user) throws BusinessException;
	
	public User update(User user) throws NotFoundException, BusinessException;

	public User load(String nameOrEmail) throws NotFoundException, BusinessException;

}
