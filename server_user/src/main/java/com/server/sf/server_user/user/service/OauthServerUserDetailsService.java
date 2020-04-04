package com.server.sf.server_user.user.service;


import com.google.gson.Gson;
import com.server.sf.server_user.common.model.BBUserDTO;
import com.server.sf.server_user.user.model.BBAuthority;
import com.server.sf.server_user.user.model.BBRole;
import com.server.sf.server_user.user.model.BBUser;
import com.server.sf.server_user.common.tool.OauthConstantTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class OauthServerUserDetailsService implements UserDetailsService {

    static Gson gson = new Gson();

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
        //----假数据---
        BBAuthority authority1 = new BBAuthority();
        authority1.setName(OauthConstantTool.Authority_user);

        BBRole role1 = new BBRole();
        role1.setName(OauthConstantTool.Role_User);
        role1.setAuthorises(new HashSet<>());
        role1.getAuthorises().add(authority1);

        user.setRoles(new HashSet<>());
        user.getRoles().add(role1);

        //----假数据---

        //传递额外的信息
        BBUserDTO userDTO = new BBUserDTO();
        userDTO.setUserId(user.getB_Id());
        userDTO.setUserName(user.getName());
        userDTO.setUserPhone(user.getPhoneNumber());

        //遍历角色获取权限
        List<BBAuthority> authoritys = new ArrayList<>();
        for (BBRole role: user.getRoles()  ) {
            for (BBAuthority authory:role.getAuthorises()) {
                authoritys.add(authory);
            }
        }

        User userDetails = new User(gson.toJson(userDTO),user.getPassword().replace("{bcrypt}",""),authoritys);

        return userDetails;
    }
}
