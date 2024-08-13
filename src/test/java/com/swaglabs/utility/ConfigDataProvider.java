package com.swaglabs.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {
Properties pro;
	
	//Constructor 
	public ConfigDataProvider() {
		//Load Configuration/config.properties
		File src = new File("./Configuration/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Not able to load Config File "+ e.getMessage());
		}
	}
	
	public String getBrowser() {
		return pro.getProperty("browser");
	}
	
	public String getStagingUrl() {
		return pro.getProperty("testurl");
	}
}
