package com.smartmarket.task;

import org.apache.log4j.Logger;

import com.smartmarket.utils.CommandUtils;
import com.smartmarket.utils.FileUtils;
import com.smartmarket.utils.XMLConfig;
import com.smartmarket.utils.XMLType;

public class TakePhotoTask extends Task implements Runnable {
	
	private static Logger log =  Logger.getLogger(TakePhotoTask.class.getName());
	private static int numberSensors = 4;
	
	private TakePhoto takePhoto;
	
	public TakePhotoTask(int intervalTime) {
		
		super(intervalTime);
		TakePhoto headTask = null;
		for (int i = 0; i < numberSensors; i++) {
			
			if (headTask == null) { // Set the head task
				
				headTask = new TakePhoto(i);
				takePhoto = headTask;
				
			} else { // Set the next task as a linked list
				
				takePhoto.setNextTask(new TakePhoto(i));
				takePhoto = takePhoto.getNextTask();
				
			}
			
		}
		
		// Set the next task from the last task as the head task		
		takePhoto.setNextTask(headTask);
		log.info("Take Photo linked list created!");
		
	}
	
	@Override
	public void run() {
		
		while (true) {
			
			if (System.currentTimeMillis() > (takePhoto.getLastRunTime()+takePhoto.getIntervalTime()) || takePhoto.getLastRunTime() == 0) {
				 
				takePhoto.runTask();
				takePhoto.updateLastRunTime();
				
			} else if (System.currentTimeMillis() > (getLastRunTime()+getIntervalTime()*1000)) {
				 
				 CommandUtils.takePicture(XMLConfig.getConfig(XMLType.PendentPhotosPath));
				 for (int i = 1; i <= numberSensors; i++) {
					 FileUtils.cropImage(i, XMLConfig.getConfig(XMLType.PendentPhotosPath));
				 }
				 updateLastRunTime();
				 
			 }
			takePhoto = takePhoto.getNextTask();
			
		}
		
	}

	@Override
	public void runTask() {}

}
