package com.smartmarket.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.smartmarket.utils.XMLConfig;
import com.smartmarket.utils.XMLType;

public class Configuration {
	
	private static Configuration instance;
	private int intervalTakePhoto;
	private int intervalProcessPhoto;
	private long rateWeight;
	private long previousWeight;
	private long devicesNumber;
	
	public static Configuration getInstance() {
		
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
		
	}
	
	public void loadConfiguration() {
		
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File(XMLConfig.getConfig(XMLType.ConfigPath))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.intervalTakePhoto = Integer.parseInt(p.getProperty("IntervalTakePhoto"));
		this.intervalProcessPhoto = Integer.parseInt(p.getProperty("IntervalProcessPhoto"));
		this.rateWeight = Integer.parseInt(p.getProperty("RateWeight"));
		this.devicesNumber = Integer.parseInt(p.getProperty("DevicesNumber"));
		
	}
	
	public int getIntervalTakePhoto() {
		return intervalTakePhoto;
	}
	
	public void setIntervalTakePhoto(int intervalTakePhoto) {
		this.intervalTakePhoto = intervalTakePhoto;
	}

	public long getRateWeight() {
		return rateWeight;
	}

	public void setRateWeight(long rateWeight) {
		this.rateWeight = rateWeight;
	}

	public long getPreviousWeight() {
		return previousWeight;
	}

	public void setPreviousWeight(long previousWeight) {
		this.previousWeight = previousWeight;
	}

	public long getDevicesNumber() {
		return devicesNumber;
	}

	public void setDevicesNumber(long devicesNumber) {
		this.devicesNumber = devicesNumber;
	}

	public int getIntervalProcessPhoto() {
		return intervalProcessPhoto;
	}

	public void setIntervalProcessPhoto(int intervalProcessPhoto) {
		this.intervalProcessPhoto = intervalProcessPhoto;
	}

}
