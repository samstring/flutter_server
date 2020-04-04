package com.server.sf.zuul_server.filter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class JsonTool {
	   /**
    *
    * 函数名称: parseData
    * 函数描述: 将json字符串转换为map
    * @param data
    * @return
    */
   public static Map<String, String> parseJsonData(String data){
       GsonBuilder gb = new GsonBuilder();
       Gson g = gb.create();
       Map<String, String> map = g.fromJson(data, new TypeToken<Map<String, String>>() {}.getType());
       return map;
   }

   public  static List<String>  toJsonList(List objects){
       List<String> jsonList = new ArrayList<>();
       Gson gson = new Gson();
       for (int i = 0; i < objects.size(); i++) {
           jsonList.add(gson.toJson(objects.get(i)));
       }
       return jsonList;
   }

   public  static  List<Object> praseJsonList(List<String> jsonList,Class objectClass){
       List<Object> objectArrayList = new ArrayList<>();
       Gson gson = new Gson();
       for (int i = 0; i < jsonList.size(); i++) {
           objectArrayList.add((Object)(gson.fromJson(jsonList.get(i).toString(), objectClass)));
       }
       return objectArrayList;
   }

}
