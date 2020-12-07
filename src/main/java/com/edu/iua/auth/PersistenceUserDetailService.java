package com.edu.iua.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edu.iua.business.IUserBusiness;
import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.model.User;

@Service
public class PersistenceUserDetailService implements UserDetailsService {

	@Autowired
	private IUserBusiness userBusiness;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u=null;
		
		try {
			u= userBusiness.load(username);
		} catch (NotFoundException e) {
			throw new UsernameNotFoundException("No se encuentra el usuario"+ username);
		} catch (BusinessException e) {
			throw new RuntimeException(e);
		}
		return u;
	}

}
