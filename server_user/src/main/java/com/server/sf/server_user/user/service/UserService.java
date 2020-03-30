package com.server.sf.server_user.user.service;

import com.server.sf.server_user.user.model.BBUser;
import com.server.sf.server_user.base.service.ServiceImpl;
import com.server.sf.server_user.tool.CharacterUtils;
import com.server.sf.server_user.tool.JWTUntil;
import com.server.sf.server_user.tool.MD5Util;
import com.server.sf.server_user.tool.NetAdressTool;
import com.server.sf.server_user.user.model.BBToken;
import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService<T extends BBUser> extends ServiceImpl<T> implements UserServiceInterface<T> {

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
	public T login(T user,int loginMode) throws Exception {
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
	public T getUserWithToken(T user) throws Exception {
		// TODO Auto-generated method stub
		T returnUser = null;
		try {
//			System.out.println("1----------");
			System.out.println(user.getB_Id()+user.getBbToken().getB_tokenString());
			this.getDao().clearCaChe();
		Query query = this.getDao().createQuery("from "+user.getClass().getName()+" u where u.b_Id = :b_Id and u.bbToken.b_tokenString = :validateString");
		query.setParameter("b_Id", user.getB_Id());
		query.setParameter("validateString",user.getBbToken().getB_tokenString());
		List<T> list = query.list();
		System.out.println("2----------");
		if ( list.size() > 0) {
			T mUser =  (T)list.get(0);
			returnUser = (T) user.getClass().newInstance();
			returnUser.setAddress(mUser.getAddress());
			returnUser.setAvatarImage(mUser.getAvatarImage());
			returnUser.setUserBackgroundImage(mUser.getUserBackgroundImage());
			returnUser.setCountryCode(mUser.getCountryCode());
			returnUser.setEmail(mUser.getEmail());
			returnUser.setName(mUser.getName());
			returnUser.setPhoneNumber(mUser.getPhoneNumber());
			returnUser.setUserDesc(mUser.getUserDesc());
			returnUser.setUserDetailDesc(mUser.getUserDetailDesc());
			returnUser.setBbToken(mUser.getBbToken());
			returnUser.setB_Id(mUser.getB_Id());
//			returnUser.setBangId(mUser.getBangId());
//
//
//			returnUser.setFansCount(mUser.getFansCount());
//			returnUser.setVideoCount(mUser.getVideoCount());
//			returnUser.setFollowCount(mUser.getFollowCount());
//			returnUser.setLikeCount(mUser.getLikeCount());


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


//	@Override
	public T getUserInfo(T user) throws Exception{
		T returnUser = null;
		try {
//			System.out.println("1----------");
//			System.out.println(user.getB_Id()+user.getValidateString());
		Query query = this.getDao().createQuery("from "+user.getClass().getName()+" u where u.b_Id = :b_Id").setCacheMode(CacheMode.IGNORE);
		query.setParameter("b_Id", user.getB_Id());
		List<T> list = query.list();
		System.out.println("2----------");
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
//			returnUser.setBangId(mUser.getBangId());
//			returnUser.setBbToken(mUser.getBbToken());
//			returnUser.setId(mUser.getId());
//			returnUser.setFansCount(mUser.getFansCount());
//			returnUser.setFollowCount(mUser.getFollowCount());
//			returnUser.setLikeCount(mUser.getLikeCount());
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


//				returnUser.setFansCount(mUser.getFansCount());
//				returnUser.setFollowCount(mUser.getFollowCount());
//				returnUser.setLikeCount(mUser.getLikeCount());

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


//				returnUser.setFansCount(mUser.getFansCount());
//				returnUser.setFollowCount(mUser.getFollowCount());
//				returnUser.setLikeCount(mUser.getLikeCount());

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
	public T getAllUserInfo(T user) throws Exception{
		T returnUser = null;
		try {
//
//			Query query = this.getDao().createQuery("from "+user.getClass().getName()+" u where u.b_Id = :b_Id").setCacheMode(CacheMode.IGNORE);
//			query.setParameter("b_Id", user.getB_Id());
//			List<T> list = query.list();
//			System.out.println("2----------");
//			if ( list.size() > 0) {

			System.out.println("------"+user.getB_Id()+"----"+user.getBbToken().getB_tokenString());
				T mUser =  this.getUserWithToken(user);
				returnUser = mUser;
//				returnUser.setFansCount(mUser.getFans().size());
//				returnUser.setVideoCount(mUser.getVideos().size());
//				returnUser.setFollowCount(mUser.getFollows().size());
//				returnUser.setLikeCount(mUser.getLikeCount());


//			}else{
//				throw new Exception("102");
//			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return returnUser;
	}

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
		// TODO Auto-generated method stub
		T returnUser = null;
		//设置密码有两种一种是用手机号重新设置密码，一种是用用token设置密码
		//用手机号重置密码

			if (user.getB_Id() == null || user.getB_Id().equals("")){
			try {
				if (propertyKey.equals("password")){
					if (this.checkUserIdExitWithPhoneNumber(user.getPhoneNumber())) {
						//修改密码
						this.getDao().createQuery("update " + user.getClass().getName() + " u set u." + propertyKey + " = :propertyValue where u.phoneNumber = :phoneNumber").setParameter("propertyValue", MD5Util.calc(propertyValue)).setParameter("phoneNumber", user.getPhoneNumber()).executeUpdate();
						//获取用户，修改Token
						Query query = this.getDao().createQuery("from BBVideoUser as u where u.phoneNumber = :phoneNumber");
						query.setParameter("phoneNumber", user.getPhoneNumber());
						List list = query.list();
						if (list.size() > 0) {
							returnUser = (T) list.get(0);
						} else {
							throw new Exception("102");
						}
						String tokenString = new JWTUntil().generateToken(user.getB_Id() + MD5Util.calc(propertyValue));
						int reslut = this.getDao().createQuery("update BBToken t set t.b_tokenString = :b_tokenString where t.b_Id = :b_Id").setParameter("b_Id", returnUser.getBbToken().getB_Id()).setParameter("b_tokenString", tokenString).executeUpdate();
						if (reslut == 0) {
							throw new Exception("400");
						}
						returnUser.getBbToken().setB_tokenString(tokenString);
						return  true;

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

				if ( propertyKey.equals("password")) {
					this.getDao().createQuery("update "+user.getClass().getName()+" u set u."+propertyKey+" = :propertyValue where u.b_Id = :b_Id").setParameter("propertyValue", MD5Util.calc(propertyValue)).setParameter("b_Id", user.getB_Id()).executeUpdate();

					String tokenString = new JWTUntil().generateToken(user.getB_Id()+MD5Util.calc(propertyValue));
					this.getDao().createQuery("update BBToken t set t.b_tokenString = :b_tokenString where t.b_Id = :b_Id").setParameter("b_Id",returnUser.getBbToken().getB_Id()).setParameter("b_tokenString", tokenString).executeUpdate();
					returnUser.getBbToken().setB_tokenString(tokenString);
				}else{
					this.getDao().createQuery("update "+user.getClass().getName()+" u set u."+propertyKey+" = :propertyValue where u.b_Id = :b_Id").setParameter("propertyValue", propertyValue).setParameter("b_Id", user.getB_Id()).executeUpdate();

				}
//			//更换名字或头像的时候应该重新获取融云的token
//			if ( propertyKey.equals("name") || propertyKey.equals("avatarImage")) {
//				RongCloud rongCloud = RongCloud.getInstance(RongYunTool.RONGYUNKEY,
//						RongYunTool.RONGYUNPASSWORD);
//				// 获取 Token  ,把 token 存到用户信息中，实现单点登录
//				TokenReslut userGetTokenResult = rongCloud.user.getToken(
//						returnUser.getB_Id(), returnUser.getName(), returnUser.getAvatarImage());
//				Map map = JsonTool.parseJsonData(userGetTokenResult.toString());
//				String chatValidateString = (String) map.get("token");
//				this.getDao().createQuery("update BBToken t set t.chat_tokenString = :chat_tokenString where t.b_Id = :b_Id").setParameter("b_Id",returnUser.getBbToken().getB_Id()).setParameter("chat_tokenString", chatValidateString).executeUpdate();
//			}

               return  true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw e;
			}
		}

		return false;
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
