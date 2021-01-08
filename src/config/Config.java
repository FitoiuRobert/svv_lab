package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import exceptions.*;
import validators.*;

public class Config {
//	private int port = 10008; 
//	private String state = "running" ;
////	private String state = "stopped" ;
//	private String rootDir = "C:\\Users\\fitoiu\\Desktop\\workspace\\java_workspace\\TestSite";
	private String configPath = "src/resources/config.properties";
	private Properties prop = new Properties();
	private ConfigValidator configValidator = new ConfigValidator(); 
	
	public void updateProperty(String name, String val) throws IOException, IllegalPropertyException {
		if (!configValidator.isValidProperty(name)) {
			throw new IllegalPropertyException();
		}
		FileOutputStream out = new FileOutputStream(configPath);
		prop.setProperty(name, val);
		prop.store(out, "updated property " + name);
		out.close();
	}
	
	private String getProperty(String name) throws IOException, IllegalPropertyException {
		if (!configValidator.isValidProperty(name)) {
			throw new IllegalPropertyException();
		}
		FileInputStream in = new FileInputStream(configPath);
		prop.load(in);
		String val = prop.getProperty(name);
		return val;
	}
	
	public int getPort() throws NumberFormatException, IOException, IllegalPropertyException {
		return Integer.parseInt(this.getProperty("port"));
	}
	
	public String getState() throws IOException, IllegalPropertyException {
		return this.getProperty("state");
	}
	
	public String getRootDir() throws IOException, IllegalPropertyException {
		return this.getProperty("rootDir");
	}
	
	public void setPort(int port) throws IOException, InvalidPortRangeException, IllegalPropertyException {
		if(! configValidator.isValidPort(port)) {
			throw new InvalidPortRangeException();
		}
		this.updateProperty("port", Integer.toString(port));
	}
	
	public void setState(String state) throws IOException, IllegalPropertyException {
		this.updateProperty("state", state);
	}
	
	public void setRootDir(String rootDir) throws IOException, IllegalPropertyException {
		this.updateProperty("rootDir", rootDir);
	}
	
	
	
}
