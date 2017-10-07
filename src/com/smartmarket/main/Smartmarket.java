package com.smartmarket.main;

import org.apache.log4j.Logger;

import com.smartmarket.configuration.Configuration;
import com.smartmarket.task.ProcessPhotoTask;
import com.smartmarket.task.TakePhotoTask;
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
		log.info("Initiliazing Take Photo Task");
		TakePhotoTask takePhoto = new TakePhotoTask(config.getIntervalTakePhoto());
		Thread threadTakePhoto = new Thread(takePhoto);
		threadTakePhoto.start();
		
		// Initialize process photo task
		log.info("Initiliazing Process Photo Task");
		ProcessPhotoTask processPhotos = new ProcessPhotoTask(config.getIntervalProcessPhoto());
		Thread threadProcessPhotos = new Thread(processPhotos);
		threadProcessPhotos.start();
		
		
		
		
	}
	
}
