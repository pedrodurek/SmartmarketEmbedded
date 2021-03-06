package com.smartmarket.task;

import java.io.File;

import org.apache.log4j.Logger;

import com.smartmarket.configuration.Configuration;
import com.smartmarket.utils.CommandUtils;
import com.smartmarket.utils.FileUtils;
import com.smartmarket.utils.XMLConfig;
import com.smartmarket.utils.XMLType;

public class TakePhoto extends Task {
	
	private static Logger log =  Logger.getLogger(TakePhoto.class.getName());

	private TakePhoto nextTask;
	private int indexSensor;
	private long previousWeight;
	
	public TakePhoto(int indexSensor) {
		
		super(100);
		this.indexSensor = indexSensor+1;
		
	}

	@Override
	public void runTask() {
		
		Configuration config = Configuration.getInstance();
		
		// Read the current weight detect by the load cell sensor
		String filePath = String.format(XMLConfig.getConfig(XMLType.LoadCellOutputPath), indexSensor);
		long currentWeight = 0;
		try {
			currentWeight = FileUtils.readFile(filePath);
		} catch (Exception e) {
			return;
		}
		
		// Calculate the current weight with the previous one from the load cell,
		// if the difference between the values was higher than the rate configured 
		// in the config file, take the photo in order to process it and detect the object
		File file = new File(XMLConfig.getConfig(XMLType.PendentPhotosPath)+"image"+indexSensor+".jpg");
		if ((Math.abs(currentWeight-previousWeight) > config.getRateWeight()) && !file.exists()) {
			
			log.info("Product detected, taking picture");
			log.info("Index Sensor: "+indexSensor);
			CommandUtils.takePicture(XMLConfig.getConfig(XMLType.PendentPhotosPath));
			FileUtils.cropImage(indexSensor, XMLConfig.getConfig(XMLType.PendentPhotosPath)); 
			previousWeight = currentWeight; // Update the weight from the load cell
			
			
		}
		
	}

	public TakePhoto getNextTask() {
		return nextTask;
	}

	public void setNextTask(TakePhoto nextTask) {
		this.nextTask = nextTask;
	}

}
