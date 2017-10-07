package com.smartmarket.task;

public abstract class Task implements TaskHandler {
	
	public Task(int intervalTime) {
		
		updateLastRunTime();
		setIntervalTime(intervalTime);
		
	}
	
	private int intervalTime;
	private long lastRunTime;
	
	public int getIntervalTime() {
		return intervalTime;
	}
	
	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}

	public long getLastRunTime() {
		return lastRunTime;
	}

	public void setLastRunTime(long lastRunTime) {
		this.lastRunTime = lastRunTime;
	}
	
	public void updateLastRunTime() {
		lastRunTime = System.currentTimeMillis();
	}

}
