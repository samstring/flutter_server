package com.server.sf.server_user.user.service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserServiceInterface <T>{
	

	@Transactional
	public  T getUserAllInfo(String phone) throws Exception;
	//用户注册
//	@Transactional
	@Transactional
	public T register(T user) throws Exception;
	
	//用户登陆
//	@Transactional(readOnly = true)
	public T login(T user, String loginMode) throws Exception;
	
////	@Transactional(propagation = Propagation.NOT_SUPPORTED)
//	public T getUserWithToken(T user) throws Exception;

	//更新用户信息
	public boolean updateUserInfo(T user) throws Exception;
	@Transactional
	public boolean updateUserSingleInfo(T user, String propertyKey, String propertyValue) throws Exception;
//	@Transactional
//	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean checkUserIdExitWithPhoneNumber(String phoneNumber) throws Exception;

	public boolean checkUserIdExitWithInviteString(String inviteString)
			throws Exception;
////	@Transactional(propagation = Propagation.NOT_SUPPORTED)
//	public T getUserInfo(T user) throws Exception;

	public T getBasicUserInfoById(T user) throws Exception;

    public T getBasicUserInfoByphoneNumber(T user) throws Exception;

	//根据条件查询，返回用户列表
	public List<T> queryUserList(String propertyKey, String propertyValuek, int index, int pageCount) throws  Exception;
}
