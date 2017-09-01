package com.smartmarket.utils;

import java.io.IOException;

public class CommandUtils {
	
	public static void takePicture(String dstDirectory) {
		
		try {
			
			Runtime.getRuntime().exec("fswebcam -r 1280x720 --no-banner "+dstDirectory+"/photo1.jpg");
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
