package com.smartmarket.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

public class XMLConfig {
	
	private static Logger log =  Logger.getLogger(XMLConfig.class.getName());
	private static XMLConfiguration xmlConfig;
	
	public static int getConfigInt(XMLType type) {
		return Integer.parseInt(getConfig(type));
	}
	
	public static String getConfig(XMLType type) {
		
		if (xmlConfig == null) {
			
			try {
				xmlConfig = new XMLConfiguration("config.xml");
			} catch (ConfigurationException e) {
				log.error("Failure loading XML Config.", e);
			}
			
		}
		
		try {
			return xmlConfig.getString(type.getParam());
		} catch (Exception e) {
			log.error("Parameter not found.", e);
		}
		return null;
		
	}
	
	

}
