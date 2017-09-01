package com.smartmarket.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
	
	
	public static long readFile(String fileName) {
		
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

}
