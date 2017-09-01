package com.smartmarket.events;

import org.apache.log4j.Logger;

import com.smartmarket.configuration.Configuration;
import com.smartmarket.utils.CommandUtils;
import com.smartmarket.utils.FileUtils;
import com.smartmarket.utils.XMLConfig;
import com.smartmarket.utils.XMLType;

public class TakePhoto extends Task implements Runnable {
	
	private static Logger log =  Logger.getLogger(TakePhoto.class.getName());
	
	public TakePhoto(int intervalTime) {
		super(intervalTime);
	}
	
	public void run() {
		
		Configuration config = Configuration.getInstance();
		while (true) {
			
			long currentWeight = FileUtils.readFile(XMLConfig.getConfig(XMLType.LoadCellOutputPath));
			if ((Math.abs(currentWeight-config.getPreviousWeight()) > config.getRateWeight()) || System.currentTimeMillis() > (getLastRunTime()+getIntervalTime()*1000)) {
				
				log.info("Product detected, taking picture.");
				CommandUtils.takePicture(XMLConfig.getConfig(XMLType.PendentPhotosPath));
				config.setPreviousWeight(currentWeight);
				updateLastRunTime();
				
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}

}
