package com.smartmarket.utils;

import java.io.IOException;

public class CommandUtils {
	
	public static void takePicture(String dstDirectory) {
		
		try {
			
			Runtime.getRuntime().exec("fswebcam -r 1280x1280 --no-banner "+dstDirectory+"image.jpg");
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
