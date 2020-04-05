package com.server.sf.server_user.user.service;

import com.server.sf.server_user.common.tool.OauthConstantTool;
import com.server.sf.server_user.tool.*;
import com.server.sf.server_user.user.model.BBAuthority;
import com.server.sf.server_user.user.model.BBRole;
import com.server.sf.server_user.user.model.BBUser;
import com.server.sf.server_user.common.service.ServiceImpl;
import com.server.sf.server_user.user.model.BBToken;
import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
public class UserService<T extends BBUser> extends ServiceImpl<T> implements UserServiceInterface<T> {

	@Autowired
	RedisService redisService;


	@Override
	public T getUserAllInfo(String phone) throws Exception{
		BBUser user = new BBUser();
		user.setPhoneNumber(phone);
		// TODO Auto-generated method stub
		T returnUser = null;
		try {
			Query query = this.getDao().createQuery("from "+user.getClass().getName()+" u where u.phoneNumber = :phoneNumber");
			query.setParameter("phoneNumber", phone);

			List<T> list = query.list();
			if ( list.size() > 0) {
				T mUser =  (T)list.get(0);
				returnUser = (T) user.getClass().newInstance();
				returnUser.setAddress(mUser.getAddress());
				returnUser.setAvatarImage(mUser.getAvatarImage());
				returnUser.setUserBackgroundImage(mUser.getUserBackgroundImage());
				returnUser.setCountryCode(mUser.getCountryCode());
				returnUser.setEmail(mUser.getEmail());
				returnUser.setName(mUser.getName());
				returnUser.setB_Id(mUser.getB_Id());
				returnUser.setPhoneNumber(mUser.getPhoneNumber());
				returnUser.setUserDesc(mUser.getUserDesc());
				returnUser.setUserDetailDesc(mUser.getUserDetailDesc());
				returnUser.setPassword(mUser.getPassword());
//			returnUser.setBangId(mUser.getBangId());
//			returnUser.setBbToken(mUser.getBbToken());
//			returnUser.setFansCount(mUser.getFansCount());
//			returnUser.setFollowCount(mUser.getFollowCount());
//			returnUser.setLikeCount(mUser.getLikeCount());

			}else{


			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			throw e;
		}
		return returnUser;

	}

	@Override
	public T register(T user) throws Exception {
		// TODO Auto-generated method stub
		T  t = null;
		try {
			if ( checkUserIdExitWithPhoneNumber(((T)user).getPhoneNumber())) {
				throw new Exception("101");
			}
			String userInitName =  CharacterUtils.getRandomString(8);
			String userInitAvatarImage = NetAdressTool.getServiceRootAdress()+"/image/avatar_holder.png";
			if ( user.getName() == null || user.getName().equals("")) {
				user.setName(userInitName);
			}
			if( user.getAvatarImage() == null || user.getAvatarImage().isEmpty()){
				user.setAvatarImage(userInitAvatarImage);
			}

			//注册用户默认有用户权限
			BBRole userRole = null;
			Query query = this.getDao().createQuery("from BBRole as a where a.name = :name");
			query.setParameter("name", OauthConstantTool.Role_User);
			List roleList = query.list();
			if(roleList.size() > 0){
				userRole = (BBRole) roleList.get(0);
			}
			user.setRoles(new HashSet<>());
			if(userRole != null){
				user.getRoles().add(userRole);
			}


			t = this.getDao().save((T)user);
//			RongCloud rongCloud = RongCloud.getInstance(RongYunTool.RONGYUNKEY,
//					RongYunTool.RONGYUNPASSWORD);

//			// 获取 Token  ,把 token 存到用户信息中，实现单点登录
//			TokenReslut userGetTokenResult = rongCloud.user.getToken(t.getB_Id(),t.getName(),t.getAvatarImage());
////            TokenReslut userGetTokenResult = rongCloud.user.getToken(t.getPhoneNumber(),t.getName(),t.getAvatarImage());
//			Map map = JsonTool.parseJsonData(userGetTokenResult.toString());
////			Sys
//			String chatValidateString = (String) map.get("token");
//			System.out.println(t.getB_Id()+"---"+chatValidateString);
			BBToken token = new BBToken();
            t.setBbToken(token);

            t.getBbToken().setB_tokenString(t.getB_Id()+ MD5Util.calc(t.getPassword()));
//            t.getBbToken().setChat_tokenString(chatValidateString);

//			BBVideoUploadSignature signature = new BBVideoUploadSignature();
//			signature.setCurrentTime(new Date().getTime());
//			String random = "";
//			for (int i = 0;i < 4;i++){
//				random = random+(int)(Math.random()*9);
//			}
//			signature.setRandom(Integer.parseInt(random));
//			signature.setSignValidDuration(7776000);
//            t.getBbToken().setB_uploadString(signature.getUploadSignature());



			System.out.println("b_token---"+token.getB_tokenString());
			//生成一个可以用来搜索的ID
//            t.setBangId(CharacterUtils.getRandomString(12) +Math.random()*1000);

			t = this.getDao().save(t);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("");
			throw e;
		}
		return t;
	}
	
	@Override
	public boolean checkUserIdExitWithPhoneNumber(String phoneNumber)
			throws Exception {
		boolean isExit = false;
		try {
			
			
			Query query = this.getDao().createQuery("from BBVideoUser as u where u.phoneNumber = :phoneNumber");
			query.setParameter("phoneNumber", phoneNumber);
			
			
			List list = query.list();
			if ( list.size() >0) {
				isExit = true;
			}
		} catch (Exception e) {

			System.out.println(e);
			// TODO: handle exception
		}
		return isExit;
	}

	@Override
	public boolean checkUserIdExitWithInviteString(String inviteString)
			throws Exception {
		boolean isExit = false;
		try {


			Query query = this.getDao().createQuery("from BBVideoUser as u where u.inviteString = :inviteString");
			query.setParameter("inviteString", inviteString);


			List list = query.list();
			if ( list.size() >0) {
				isExit = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isExit;
	}

	@Override
	public T login(T user,String loginMode) throws Exception {
		// TODO Auto-generated method stub
		T returnUser = null;
		try {
		Query query = this.getDao().createQuery("from "+user.getClass().getName()+" u where u.phoneNumber = :phoneNumber and u.password = :password");
		query.setParameter("phoneNumber", user.getPhoneNumber());
		query.setParameter("password",user.getPassword());
		List<T> list = query.list();
		if ( list.size() > 0) {
			T mUser =  (T)list.get(0);
			returnUser = (T) user.getClass().newInstance();
			returnUser.setAddress(mUser.getAddress());
			returnUser.setAvatarImage(mUser.getAvatarImage());
			returnUser.setUserBackgroundImage(mUser.getUserBackgroundImage());
			returnUser.setCountryCode(mUser.getCountryCode());
			returnUser.setEmail(mUser.getEmail());
			returnUser.setName(mUser.getName());
			returnUser.setB_Id(mUser.getB_Id());
			returnUser.setPhoneNumber(mUser.getPhoneNumber());
			returnUser.setUserDesc(mUser.getUserDesc());
			returnUser.setUserDetailDesc(mUser.getUserDetailDesc());
			returnUser.setRoles(mUser.getRoles());
//			returnUser.setBangId(mUser.getBangId());
//			returnUser.setBbToken(mUser.getBbToken());
//			returnUser.setFansCount(mUser.getFansCount());
//			returnUser.setFollowCount(mUser.getFollowCount());
//			returnUser.setLikeCount(mUser.getLikeCount());

		}else{
			

		}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			throw e;
		}
		return returnUser;
	}

//	@Override
	public T getUserInfo(T user) throws Exception{
		T returnUser = null;
		try {
		Query query = this.getDao().createQuery("from "+user.getClass().getName()+" u where u.b_Id = :b_Id").setCacheMode(CacheMode.IGNORE);
		query.setParameter("b_Id", user.getB_Id());
		List<T> list = query.list();
		if ( list.size() > 0) {
			T mUser =  (T)list.get(0);
			returnUser = (T) user.getClass().newInstance();
			returnUser.setB_Id(mUser.getB_Id());
			returnUser.setAddress(mUser.getAddress());
			returnUser.setAvatarImage(mUser.getAvatarImage());
			returnUser.setCountryCode(mUser.getCountryCode());
			returnUser.setEmail(mUser.getEmail());
			returnUser.setName(mUser.getName());
			returnUser.setPhoneNumber(mUser.getPhoneNumber());
			returnUser.setUserDesc(mUser.getUserDesc());
			returnUser.setUserDetailDesc(mUser.getUserDetailDesc());
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
	public T getBasicUserInfoById(T user) throws Exception{
		System.out.println("1----------");
		T returnUser = null;
		try {
			System.out.println("2----------");
//
			Query query = this.getDao().createQuery("from "+user.getClass().getName()+" u where u.b_Id = :b_Id").setCacheMode(CacheMode.IGNORE);
			query.setParameter("b_Id", user.getB_Id());
			List<T> list = query.list();
			System.out.println("3----------");
			if ( list.size() > 0) {
				T mUser =  (T)list.get(0);
				returnUser = (T) user.getClass().newInstance();
				returnUser.setB_Id(mUser.getB_Id());

				returnUser.setAvatarImage(mUser.getAvatarImage());
				returnUser.setCountryCode(mUser.getCountryCode());
				returnUser.setUserDetailDesc(mUser.getUserDetailDesc());
				returnUser.setName(mUser.getName());
				returnUser.setPhoneNumber(mUser.getPhoneNumber());
				returnUser.setUserDesc(mUser.getUserDesc());

				System.out.println("4----------"+user.getB_Id());
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
    public T getBasicUserInfoByphoneNumber(T user) throws  Exception {
        System.out.println("1----------");
        T returnUser = null;
        try {
            System.out.println("2----------");
//
            Query query = this.getDao().createQuery("from "+user.getClass().getName()+" u where u.phoneNumber = :phoneNumber").setCacheMode(CacheMode.IGNORE);
            query.setParameter("phoneNumber", user.getPhoneNumber());
            List<T> list = query.list();
            System.out.println("3----------");
            if ( list.size() > 0) {
                T mUser =  (T)list.get(0);
                returnUser = (T) user.getClass().newInstance();
                returnUser.setB_Id(mUser.getB_Id());

                returnUser.setAvatarImage(mUser.getAvatarImage());
                returnUser.setCountryCode(mUser.getCountryCode());
                returnUser.setUserDetailDesc(mUser.getUserDetailDesc());
                returnUser.setName(mUser.getName());
                returnUser.setPhoneNumber(mUser.getPhoneNumber());
                returnUser.setUserDesc(mUser.getUserDesc());
                returnUser.setUserBackgroundImage(mUser.getUserBackgroundImage());

                System.out.println("4----------"+user.getB_Id());
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

//

	@Override
	public boolean updateUserInfo(T user) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.getDao().update(user);
			return  true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public boolean updateUserSingleInfo(T user, String propertyKey,
			String propertyValue) throws Exception {
		boolean isDone = false;
		T returnUser = null;
		//设置密码有两种一种是用手机号重新设置密码，一种是用用token设置密码
		//用手机号重置密码

			if (user.getB_Id() == null || user.getB_Id().equals("")){
			try {
				if (propertyKey.equals("password")){
					if (this.checkUserIdExitWithPhoneNumber(user.getPhoneNumber())) {
						//修改密码
						int count = this.getDao().createQuery("update " + user.getClass().getName() + " u set u." + propertyKey + " = :propertyValue and jwtVersion = jwtVersion + 1 where u.phoneNumber = :phoneNumber").setParameter("propertyValue", MD5Util.calc(propertyValue)).setParameter("phoneNumber", user.getPhoneNumber()).executeUpdate();
						if(count > 0){
							isDone = true;
						}else{
							isDone = false;
						}

					} else {
						throw new Exception("102");
					}
				}
			}catch (Exception e){
				e.printStackTrace();
				throw e;
			}
		}
		//用token修改信息
		else {
			try {
				int count = 0;
				if ( propertyKey.equals("password")) {
					count = this.getDao().createQuery("update "+user.getClass().getName()+" u set u."+propertyKey+" = :propertyValue and jwtVersion = jwtVersion + 1 where u.b_Id = :b_Id").setParameter("propertyValue", MD5Util.calc(propertyValue)).setParameter("b_Id", user.getB_Id()).executeUpdate();
				}else{
					count = this.getDao().createQuery("update "+user.getClass().getName()+" u set u."+propertyKey+" = :propertyValue where u.b_Id = :b_Id").setParameter("propertyValue", propertyValue).setParameter("b_Id", user.getB_Id()).executeUpdate();

				}
				if(count > 0){
					isDone = true;
				}else{
					isDone = false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw e;
			}
		}

		//如果修改成功且是修改密码的情况下更新jwt的版本号
		if(isDone){
			if(propertyKey.equals("password")){
				BBUser resultUser = null;
				Query query;
				if(user.getB_Id().isEmpty() || user.getB_Id() == null){
					query =  this.getDao().createQuery("from BBVideoUser u where u.phoneNumber = :phoneNumber");
					query.setParameter("phoneNumber",user.getPhoneNumber());
				}
				else{
					query = this.getDao().createQuery("from BBVideoUser u where u.b_Id = :b_Id");
					query.setParameter("b_Id",user.getB_Id());
				}
				List userList = query.list();
				if (userList.size() > 0){
					resultUser = (BBUser) userList.get(0);
					//更新版本
					redisService.set(resultUser.getB_Id(),resultUser.getJwtVersion());
				}

			}


		}

		return isDone;
	}

	@Override
	public List<T> queryUserList(String propertyKey,String propertyValue,int index,int pageCount) throws  Exception{
		List<T> returnUserList	 = new ArrayList<T>();
		try {
			T returnUser;
			Query query = null;
			if (propertyValue == null ){
				return returnUserList;
			}
			if ( propertyKey == null || propertyKey.equals("")){
				query =  this.getDao().createQuery("from BBVideoUser u where u.phoneNumber = :phoneNumber or u.name like :name or u.CountryCode like :CountryCode or u.city like :city or u.b_Id like :b_Id").setCacheMode(CacheMode.IGNORE);
				query.setParameter("phoneNumber", propertyValue);
				query.setParameter("name", "%"+propertyValue+"%");
				query.setParameter("CountryCode", "%"+propertyValue+"%");
				query.setParameter("city", "%"+propertyValue+"%");
				query.setParameter("b_Id",propertyValue);
			}else{
				if (propertyKey.equals("phoneNumber")){
					query =  this.getDao().createQuery("from BBVideoUser u where u."+propertyKey+" = :propertyValue");
					query.setParameter("propertyValue", propertyValue);
				}else{
					query =  this.getDao().createQuery("from BBVideoUser u where u."+propertyKey+" like :propertyValue");
					query.setParameter("propertyValue", "%"+propertyValue+"%");
				}

			}

			query.setFirstResult(index);
			query.setMaxResults(pageCount);

			List<T> list = query.list();
			for (int i = 0;i<list.size();i++) {
				T mUser = (T) list.get(i);
				returnUser = (T) mUser.getClass().newInstance();
				returnUser.setB_Id(mUser.getB_Id());
				returnUser.setAvatarImage(mUser.getAvatarImage());;
				returnUser.setCountryCode(mUser.getCountryCode());
				returnUser.setName(mUser.getName());
				returnUser.setUserDesc(mUser.getUserDesc());
				returnUser.setUserDetailDesc(mUser.getUserDetailDesc());
				returnUserList.add(returnUser);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return returnUserList;
	}



}
