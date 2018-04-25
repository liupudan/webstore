package com.chinasofti.util;

import java.util.Random;

public class RandomUtil {
	public static String getRandomPassword() {
		String string = "1234567890";
		Random random = new Random();
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			int index = random.nextInt(string.length());
			char c = string.charAt(index);
			sBuffer.append(c);
		}
		return sBuffer.toString();
	}
}
