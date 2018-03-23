 package com.solar.tech.util;
 
 public class Error
 {
	 public static final String USR_PASS_NULL = "用户名或密码不能为空";
 
   public static final String USR_NOT_EXIST(String loginAccount)
   {
	   return "该用户不存在";
   }
 
   public static final String PASSWORD(String username) {
	   return "用户：" + username + " 密码错误";
   }
   public static final String USR_FRZ(String username) {
	   return "用户：" + username + " 已被冻结";
   }
   public static final String FIELD_NOT_FOUND(Object object, Class<?> annoClass) {
	   return "没有在对象" + object + "中找到被" + annoClass + "标记的字段";
   }
 }

