package com.server.sf.server_user.user.controller;

import com.server.sf.server_user.common.model.ResultMapConstant;
import com.server.sf.server_user.user.model.BBToken;
import com.server.sf.server_user.user.model.BBUser;
import com.server.sf.server_user.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    public UserService<BBUser> userService;

//    @RequestMapping("/user_login.action")
//    public Map login(BBVideoUser user,int loginMode) throws Exception {
//        Map map = new HashMap();
////        BBVideoUser user = new BBVideoUser();
////        user.setPhoneNumber(phoneNumber);
////        user.setPassword(password);
//        BBVideoUser mUser = null;
//        boolean isExit = false;
//        try {
//            isExit = userService.checkUserIdExitWithPhoneNumber(user.getPhoneNumber());
//            if (isExit){
//                mUser = userService.login(user,loginMode);
//                if (mUser != null){
//                    map.put("resultCode",  "200");
//                    map.put("error", null);
//                    map.put("result", mUser);
//                }else {
//                    map.put("resultCode",  "-1");
//                    map.put("error", "密码与账号不符合");
//                    map.put("result", null);
//                }
//
//            }else{
////                    BBVideoUser mUser ;//= new BBVideoUser();
//                mUser = userService.register(user);
////				isRegisterDone = true;
//                map.put("resultCode",  "200");
//                map.put("result",mUser);
//                map.put("error", null);
//            }
//        }catch (Exception e){
//            System.out.println(e.toString());
//            throw  e;
//        }
//        finally {
//
//        }
//
//        return  map;
//    }
//

    @RequestMapping("/a/d")
    public  Map test(){
        return new HashMap();
    }

    @RequestMapping("/user/login")
    public Map login(String phoneNumber,String password,String loginMode) throws Exception {

        Map map = new HashMap();

        if(loginMode == null || loginMode.equals("")){
            map.put(ResultMapConstant.ResultCode,  ResultMapConstant.ResultCodeError);
            map.put(ResultMapConstant.Message, "loginMode不能为空");
            map.put(ResultMapConstant.Result, "");
            return map;
        }
        if(phoneNumber == null || phoneNumber.equals("")){
            map.put(ResultMapConstant.ResultCode,  ResultMapConstant.ResultCodeError);
            map.put(ResultMapConstant.Message,  "phoneNumber不能为空");
            map.put(ResultMapConstant.Result, "");
            return map;
        }
        if(password == null || password.equals("")){
            map.put(ResultMapConstant.ResultCode,  ResultMapConstant.ResultCodeError);
            map.put(ResultMapConstant.Message,  "password不能为空");
            map.put(ResultMapConstant.Result, "");
            return map;
        }
        BBUser user = new BBUser();
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        BBUser mUser = null;
        boolean isExit = false;
        try {
            isExit = userService.checkUserIdExitWithPhoneNumber(user.getPhoneNumber());
            if (isExit){
                mUser = userService.login(user,loginMode);
                if (mUser != null){
                    map.put("resultCode",  "200");
                    map.put("error", null);
                    map.put("result", mUser);
                }else {
                    map.put("resultCode",  "-1");
                    map.put("error", "密码与账号不符合");
                    map.put("result", null);
                }

            }else{
//                    BBVideoUser mUser ;//= new BBVideoUser();
                 mUser = userService.register(user);
//				isRegisterDone = true;
                map.put("resultCode",  "200");
                map.put("result",mUser);
                map.put("error", null);
            }
        }catch (Exception e){
            System.out.println(e.toString());
            throw  e;
        }
        finally {

        }

        return  map;
    }

    /*获取用户信息*/
    @RequestMapping("/getUserInfo")
    public Map getUserInfo(String userId) throws Exception{
        Map map = new HashMap();
        if(userId == null || userId.equals("")){
            map.put(ResultMapConstant.ResultCode,  ResultMapConstant.ResultCodeError);
            map.put(ResultMapConstant.Message, "用户ID不能为空");
            map.put(ResultMapConstant.Result, "");
        }

        BBUser user = new BBUser();
        user.setB_Id(userId);
        try {
            BBUser result = userService.getBasicUserInfoById(user);
            map.put(ResultMapConstant.ResultCode,  ResultMapConstant.ResultCodeDone);
            map.put(ResultMapConstant.Message, "");
            map.put(ResultMapConstant.Result, result);
        }catch (Exception e){
            map.put(ResultMapConstant.ResultCode,  ResultMapConstant.ResultCodeError);
            map.put(ResultMapConstant.Message, e.toString());
            map.put(ResultMapConstant.Result, "");
        }

        return  map;

    }


    //根据字段更新用户信息
    @RequestMapping("'/updateUserInfo'")
    public Map updateUserInfo(String userId,String propertyKey,String propertyValue)throws Exception{
        Map returnMap = new HashMap();
        BBUser user = new BBUser();
        user.setB_Id(userId);
        try {

            boolean isSuc =  userService.updateUserSingleInfo(user,propertyKey,propertyValue);
            if(isSuc){
                returnMap.put(ResultMapConstant.ResultCode,ResultMapConstant.ResultCodeDone);
                returnMap.put(ResultMapConstant.Result,"");
                returnMap.put(ResultMapConstant.Message,"修改成功");
            }else{
                returnMap.put(ResultMapConstant.ResultCode,ResultMapConstant.ResultCodeError);
                returnMap.put(ResultMapConstant.Result,"");
                returnMap.put(ResultMapConstant.Message,"修改失败");
            }

        } catch (Exception e) {
            returnMap.put(ResultMapConstant.ResultCode,ResultMapConstant.ResultCodeError);
            returnMap.put(ResultMapConstant.Result,"");
            returnMap.put(ResultMapConstant.Message,e.toString());
            // TODO: handle exception
            throw e;
        }
        return returnMap;
    }


    @RequestMapping(value = "/multiImport", method = RequestMethod.POST)
    public Map<String, Object> uploadFiles(String userId,String userToken,@RequestParam("uploadFile") MultipartFile[] uploadFile) throws IOException {

        Map returnMap = new HashMap();

        try{
            //获取文件夹
            String fileUrl = "/image/user_upload_file/";
            Resource resource = new ClassPathResource("");
            String dir = resource.getFile().getAbsolutePath();
            File fileLocation = new File(dir+fileUrl);
            //判断上传路径是否存在，如果不存在就创建
            if(!fileLocation.exists()) {
                boolean isCreated  = fileLocation.mkdir();
                if(!isCreated) {
                    //目标上传目录创建失败,可做其他处理,例如抛出自定义异常等,一般应该不会出现这种情况。
                    return null;
                }
            }
            //获取用户信息
            BBUser user = new BBUser();
            user.setB_Id(userId);
            user.setBbToken(new BBToken());
            user.getBbToken().setB_tokenString(userToken);
//            BBUser userInfo = userService.getUserWithToken(user);



            BufferedOutputStream stream = null;

            //获取文件路径
            for (MultipartFile multipartFile:uploadFile) {
                //文件名
                DateFormat df=new SimpleDateFormat("yyyy-MM-dd-HH-mm-");
                String dateString=df.format(new Date());
                String fileName = user.getB_Id()+"_"+dateString+(int)(Math.random()*10000)+"——";

                if(!multipartFile.isEmpty()){
                    byte[] bytes = multipartFile.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(fileLocation,fileName+multipartFile.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                }
            }

        }catch (Exception e){

        }

        System.out.println(uploadFile.length);

        return returnMap;
    }


}
