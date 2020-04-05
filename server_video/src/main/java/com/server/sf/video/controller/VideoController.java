package com.server.sf.video.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class VideoController {

    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('authority_user')")
    public Map getUserController(){
        BBUserDTO userDTO = (BBUserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HashMap map = new HashMap();
        map.put("done",true);
        map.put("user",userDTO.getUserId()+"----"+userDTO.getUserName());
        return map;
    }
}
