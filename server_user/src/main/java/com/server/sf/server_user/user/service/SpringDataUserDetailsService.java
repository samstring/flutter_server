package com.server.sf.server_user.user.service;


import com.server.sf.server_user.user.model.BBUser;
import com.server.sf.server_user.user.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    UserServiceInterface<BBUser> userServiceInterface;

    //根据 账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //将来连接数据库根据账号查询用户信息
        BBUser user  = null;
        try {
            user = userServiceInterface.getUserAllInfo(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(user == null){
            return  null;
        }
        //根据用户的id查询用户的权限
        List<String> permissions =new ArrayList<>();
        //将permissions转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        //将userDto转成json
        String principal = "";
        String password = user.getPassword().replace("{bcrypt}","");

        user.setAuthorities(new ArrayList<>());
//        User.withUserDetails()
//        UserDetails userDetails = User.withUsername(principal).password(password).authorities(permissionArray).build();
        User userDetails = new User(username,user.getPassword().replace("{bcrypt}",""),user.getAuthorities());
        return userDetails;
    }
}
