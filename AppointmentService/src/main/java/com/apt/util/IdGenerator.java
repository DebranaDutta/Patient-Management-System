package com.apt.util;

import java.util.Random;

public class IdGenerator {
	public static long randomIdGenerator() {
		Random random = new Random();
		long numericId = Math.abs(random.nextLong());
		long sixDigitId = 100000 + random.nextInt(900000);	
		return sixDigitId;
	}
}
