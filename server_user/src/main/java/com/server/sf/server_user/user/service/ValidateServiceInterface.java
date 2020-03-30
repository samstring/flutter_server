package com.server.sf.server_user.user.service;

public interface ValidateServiceInterface <T>{
	
	public String generateValidateToken(T user) throws Exception;
	public T getInfoWithTokenString(T user)throws Exception;
	public boolean canDoAction();
	
}
