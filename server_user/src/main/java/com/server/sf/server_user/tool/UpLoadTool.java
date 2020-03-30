package com.server.sf.server_user.tool;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

/***
 * 通过base64字串上传图片的工具类
 * @author Slience
 *
 */

@RestController
public class UpLoadTool {
    /**
     * 上传图片
     * @param base64 图片编码后的字符串
     * @return 上传成功后的文件名
     */
    public static String upload(String base64,String filePath) throws IOException {
//        HttpServletRequest request = .getRequest();
        //upload是图片上传路径
        Resource resource = new ClassPathResource("");
        String dir = resource.getFile().getAbsolutePath();
        File fileLocation = new File(dir);
        //判断上传路径是否存在，如果不存在就创建
        if(!fileLocation.exists()) {
            boolean isCreated  = fileLocation.mkdir();
            if(!isCreated) {
                //目标上传目录创建失败,可做其他处理,例如抛出自定义异常等,一般应该不会出现这种情况。
                return null;
            }
        }


        if(base64.indexOf("jpeg") != -1) {
            //base64字串中有jpeg字串，这是一个4个字的，而我这里是把base64字串的指定位置的字串来作为上传
            //文件类型的判断依据，所以在大部分都是三个字的文件类型下就得把jpeg改成jpg了
            base64 = base64.replaceFirst("jpeg", "jpg");
        }


//        base64ToImage(base64,filePath);
        //生成一个唯一的文件名
        String upName = UUID.randomUUID().toString()+System.currentTimeMillis()+"."+base64.substring(11, 14);
        FileOutputStream out;
        String iconBase64 = base64.substring(22);
        try {
            byte[] buffer = new BASE64Decoder().decodeBuffer(iconBase64);
            out = new FileOutputStream(dir+"/"+upName);
            out.write(buffer);
            out.close();
            return upName;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Descriptionmap 对字节数组字符串进行Base64解码并生成图片
     * @author temdy
     * @Date 2015-01-26
     * @param base64 图片Base64数据
     * @param path 图片路径
     * @return
     */
    public static boolean base64ToImage(String base64, String path,String fileName) {// 对字节数组字符串进行Base64解码并生成图片
        if (base64 == null){ // 图像数据为空
            return false;
        }

        BASE64Decoder decoder = new BASE64Decoder();
        try {

            File fileLocation = new File(path);
            if ( !fileLocation.exists() ){
                boolean isCreated  = fileLocation.mkdirs();
                if(!isCreated) {
                    //目标上传目录创建失败,可做其他处理,例如抛出自定义异常等,一般应该不会出现这种情况。
                    throw new Exception("创建文件夹失败");
//
                }
            }
            //判断上传路径是否存在，如果不存在就创建
           File imageFile = new File(fileLocation,fileName);
            if ( !imageFile.exists()){
                boolean isCreated  = imageFile.createNewFile();
                if(!isCreated) {
                    //目标上传目录创建失败,可做其他处理,例如抛出自定义异常等,一般应该不会出现这种情况。
                    throw new Exception("创建文件失败");
//
                }
            }
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(base64);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imageFile);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}