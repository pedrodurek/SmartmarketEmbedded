package com.smartmarket.main;

import org.apache.log4j.Logger;

import com.smartmarket.configuration.Configuration;
import com.smartmarket.events.ProcessPhoto;
import com.smartmarket.events.TakePhoto;
import com.smartmarket.utils.XMLConfig;
import com.smartmarket.utils.XMLType;

public class Smartmarket {
	
	private static Logger log =  Logger.getLogger(Smartmarket.class.getName());
	
	public static void main(String[] args) {

		log.info("Initializing Smartmarket Application - Version "+XMLConfig.getConfig(XMLType.Version));
		
		// Load default configs
		Configuration config = Configuration.getInstance();
		config.loadConfiguration();
		
		// Initialize take photo task
		log.info("Initiliaze Take Photo Task");
		TakePhoto takePhoto = new TakePhoto(config.getIntervalTakePhoto());
		Thread threadTakePhoto = new Thread(takePhoto);
		threadTakePhoto.start();
		
		// Initialize process photo task
		log.info("Initiliaze Process Photo Task");
		ProcessPhoto processPhotos = new ProcessPhoto(config.getIntervalProcessPhoto());
		Thread threadProcessPhotos = new Thread(processPhotos);
		threadProcessPhotos.start();
		
		
		
		
	}
	
}
