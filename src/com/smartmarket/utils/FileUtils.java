package com.smartmarket.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class FileUtils {
	
	private static Logger log =  Logger.getLogger(FileUtils.class.getName());
	public static long readFile(String fileName) throws Exception {
		
		File file = new File(fileName);
		return Long.parseLong(readFile(file));
		
	}
	
	public static String readFile(File file) {
		
		BufferedReader br = null;
		try {
			
			br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				
	            sb.append(line);
	            line = br.readLine();
	            
	        }
	        return sb.toString();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		}
		return null;
		
	}
	
	public static void cropImage(int slice, String folder) {
		
		try {
			
			File image = new File(folder+"image.jpg");
			BufferedImage SubImgage = null;
			if (image.exists()) {
				
				BufferedImage originalImgage = ImageIO.read(image);
				int x = originalImgage.getWidth()/2, y = originalImgage.getHeight()/2;
				int aux = 200;
				if (slice == 1) {
					
					/* --------------
					 * -  X  -      -
					 * --------------
					 * -	 -		-
					 * --------------
					 * */
					SubImgage = originalImgage.getSubimage(aux, 0, x-aux, y);
					
				} else if (slice == 2) {
				
					/* --------------
					 * -     -   X  -
					 * --------------
					 * -	 -		-
					 * --------------
					 * */
					SubImgage = originalImgage.getSubimage(x, 0, x-aux, y);
					
				} else if (slice == 3) {
				
					/* --------------
					 * -     -      -
					 * --------------
					 * -  X  -		-
					 * --------------
					 * */
					SubImgage = originalImgage.getSubimage(aux, y, x-aux, y);
				
				} else if (slice == 4) {
					
					/* --------------
					 * -     -      -
					 * --------------
					 * -     -	X	-
					 * --------------
					 * */
					SubImgage = originalImgage.getSubimage(x, y, x-aux, y);
					
				}
				ImageIO.write(SubImgage, "jpg", new File(folder+"image"+slice+".jpg"));
				Thread.sleep(1000);
				image.delete();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
