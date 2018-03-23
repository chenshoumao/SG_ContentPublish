package com.solar.tech.util;

 

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomNum {

	/**
	 * @Description: TODO
	 * @param @param args   
	 * @return void  
	 * @throws
	 * @author houhuayu
	 * @date 2016-6-17
	 */
	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		System.out.println(RandomNum.getRandomString(4));

	}
	public static String getRandomString(int length) { //length表示生成字符串的长度
		String randomNum="";
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		randomNum = df.format(new Date())+sb.toString();
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	    return randomNum;   
	 }  
	

}
