package com.smartmarket.utils;


public class CommandUtils {
	
	public static void takePicture(String dstDirectory) {
		
		try {
			
			Thread.sleep(2000);
			Runtime.getRuntime().exec("fswebcam -r 1280x1280 --no-banner "+dstDirectory+"image.jpg");
			Thread.sleep(2000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
