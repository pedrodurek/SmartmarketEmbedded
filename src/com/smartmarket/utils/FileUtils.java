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
	public static long readFile(String fileName) {
		
		File file = new File(fileName);
		try {
			return Long.parseLong(readFile(file));
		} catch (Exception e) {
			return -1;
		}
		
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
			log.info(folder+"image.jpg");
			if (image.exists()) {
				
				BufferedImage originalImgage = ImageIO.read(image);
				int x = originalImgage.getWidth()/2, y = originalImgage.getHeight()/2;
				
				if (slice == 1) {
					
					/* --------------
					 * -  X  -      -
					 * --------------
					 * -	 -		-
					 * --------------
					 * */
					BufferedImage SubImgage = originalImgage.getSubimage(0, 0, x, y);
					ImageIO.write(SubImgage, "jpg", new File(folder+"image1.jpg"));
					
				} else if (slice == 2) {
				
					/* --------------
					 * -     -   X  -
					 * --------------
					 * -	 -		-
					 * --------------
					 * */
					BufferedImage SubImgage = originalImgage.getSubimage(x, 0, x, y);
					ImageIO.write(SubImgage, "jpg", new File(folder+"image2.jpg"));
					
				} else if (slice == 3) {
				
					/* --------------
					 * -     -      -
					 * --------------
					 * -  X  -		-
					 * --------------
					 * */
					BufferedImage SubImgage = originalImgage.getSubimage(0, y, x, y);
					ImageIO.write(SubImgage, "jpg", new File(folder+"image3.jpg"));
				
				} else if (slice == 4) {
					
					/* --------------
					 * -     -      -
					 * --------------
					 * -     -	X	-
					 * --------------
					 * */
					BufferedImage SubImgage = originalImgage.getSubimage(x, y, x, y);
					ImageIO.write(SubImgage, "jpg", new File(folder+"image4.jpg"));
					
				}
				image.delete();
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
