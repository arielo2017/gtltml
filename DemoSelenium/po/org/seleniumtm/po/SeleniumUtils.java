package org.seleniumtm.po;

import java.util.Random;

public class SeleniumUtils {
	
	public static int generateNumber(int max) {

		Random rand = new Random();

		int n = rand.nextInt(max) + 1;
		return n;
	}

}
