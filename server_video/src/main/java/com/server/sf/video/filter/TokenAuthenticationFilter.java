package com.server.sf.video.filter;


import com.google.gson.Gson;
import com.server.sf.video.common.model.BBUserDTO;
import com.server.sf.video.common.tool.EncryptUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    static Gson gson = new Gson();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
            //解析出头中的token
        String token = httpServletRequest.getHeader("json-token");
        if(token!=null){
            String json = EncryptUtil.decodeUTF8StringBase64(token);

            Map jsonMap = gson.fromJson(json,Map.class);
            //用户身份信息
//            UserDTO userDTO = new UserDTO();
//            String principal = jsonObject.getString("principal");
//            userDTO.setUsername(principal);
            BBUserDTO userDTO = gson.fromJson(jsonMap.get("principal").toString(), BBUserDTO.class);

            //Todo 从jwt获取到版本号与redis中用户的jwt版本号做对比，如果版本号有问题的话则代表jwt无效

            //用户权限
            List<String> authoritiesList = gson.fromJson(jsonMap.get("authorities").toString(),List.class);
            String[] authorities = new String[authoritiesList.size()];
            authoritiesList.toArray(authorities);

            //将用户信息和权限填充 到用户身份token对象中
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(userDTO,null, AuthorityUtils.createAuthorityList(authorities));
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            //将authenticationToken填充到安全上下文
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }
}
