package com.zyy.utils;

import java.util.UUID;

public class PicUtils {
	public static String getPicName(String name) {
		//截取图片的后缀名
		String string = UUID.randomUUID().toString();
		String startName = string.replaceAll("-", "");
		String endName = name.substring(name.lastIndexOf("."));
		
		System.out.println("aaa");
		return startName+endName;
		
	}
}
