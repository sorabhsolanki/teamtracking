package com.teamtrack.util;

import java.util.Random;

public class CommonHelper {

	private static final Random RANDOM = new Random();
	
	public static int getRandom(){
		return RANDOM.nextInt(10000);
	}
}
