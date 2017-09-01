package com.smartmarket.utils;

public enum XMLType {
	
	/* Paths */	
	ConfigPath("paths.config"),
	LoadCellOutputPath("paths.loadcelloutput"),
	PendentPhotosPath("paths.pendentphotos"),
	
	/* Information */
	Version("info.version"),
	
	/* URLs */
	ProcessPhotoURL("urls.processphoto");
	
	private String param;
	
	private XMLType(String param) {
		this.setParam(param);
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	

}
