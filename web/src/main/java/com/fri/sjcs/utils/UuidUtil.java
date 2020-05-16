package com.fri.sjcs.utils;

import java.util.UUID;

public class UuidUtil {

	/**
	 * 获取32位的uuid
	 * @return
	 */
	public static String get32UUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	/**
	 * 获取完整的uuid
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().trim();
		return uuid;
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println(get32UUID());
		System.out.println(get32UUID());
		System.out.println(get32UUID());
	}
}

