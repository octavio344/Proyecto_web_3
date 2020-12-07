package com.edu.iua.authtoken;


import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;

public interface IAuthTokenBusiness {
	
	public AuthToken save(AuthToken at) throws BusinessException;

	public AuthToken load(String series) throws BusinessException, NotFoundException;

	public void delete(AuthToken at) throws BusinessException;

	public void purgeTokens() throws BusinessException;
	
	public void delete(String token) throws BusinessException ;

}
