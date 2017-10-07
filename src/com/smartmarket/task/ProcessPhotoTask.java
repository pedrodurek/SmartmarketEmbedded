package com.smartmarket.task;

import org.apache.log4j.Logger;

public class ProcessPhotoTask implements Runnable {
	
	private static Logger log =  Logger.getLogger(TakePhotoTask.class.getName());
	private static int[] addressesLCD = {0x26, 0x27};
	
	private ProcessPhoto processPhoto;
	
	public ProcessPhotoTask(int intervalTime) {
	
		ProcessPhoto headTask = null;
		for (int i = 0; i < addressesLCD.length; i++) {
			
			if (headTask == null) { // Set the head task
				
				headTask = new ProcessPhoto(addressesLCD[i], i, intervalTime);
				processPhoto = headTask;
				
			} else { // Set the next task as a linked list
				
				processPhoto.setNextTask(new ProcessPhoto(addressesLCD[i], i, intervalTime));
				processPhoto = processPhoto.getNextTask();
				
			}
			
		}
		// Set the next task from the last task as the head task		
		processPhoto.setNextTask(headTask);
		log.info("Process Photo linked list created!");
		
	}

	@Override
	public void run() {

		while (true) {
			
			if (System.currentTimeMillis() > (processPhoto.getLastRunTime()+processPhoto.getIntervalTime()*1000) || processPhoto.getLastRunTime() == 0) {
				
				processPhoto.runTask();
				processPhoto.updateLastRunTime();
				
			}
			processPhoto = processPhoto.getNextTask(); // Jump to next task
			
		}
		
	}

}
