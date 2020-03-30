package com.server.sf.server_user.user.service;

import com.server.sf.server_user.base.service.ServiceImpl;
import com.server.sf.server_user.user.model.BBUser;
import org.hibernate.Query;

import java.util.List;

public class ValidateService<T extends BBUser> extends ServiceImpl<T> implements ValidateServiceInterface<T>{

	@Override
	public String generateValidateToken(T user) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getInfoWithTokenString(T user)throws Exception {
		// TODO Auto-generated method stub
		T returnUser = null;
		try {
			System.out.println("1----------");
//			System.out.println(user.getB_Id()+user.getValidateString());
		Query query = this.getDao().createQuery("from "+user.getClass().getName()+" u where u.b_Id = :b_Id and u.validateString = :validateString");
		query.setParameter("b_Id", user.getB_Id());
//		query.setParameter("validateString",user.getValidateString());
		List<T> list = query.list();
		System.out.println("2----------");
		if ( list.size() > 0) {
			T mUser =  (T)list.get(0);
			returnUser = (T) user.getClass().newInstance();
			returnUser.setAddress(mUser.getAddress());
			returnUser.setAvatarImage(mUser.getAvatarImage());
			returnUser.setCountryCode(mUser.getCountryCode());
			returnUser.setEmail(mUser.getEmail());
			returnUser.setName(mUser.getName());
			returnUser.setPhoneNumber(mUser.getPhoneNumber());
			returnUser.setUserDesc(mUser.getUserDesc());
//			returnUser.setValidateString(mUser.getValidateString());
			System.out.println("3----------");
		}else{
			throw new Exception("102");
		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return returnUser;
	}

	@Override
	public boolean canDoAction() {
		// TODO Auto-generated method stub
		return false;
	}

}
