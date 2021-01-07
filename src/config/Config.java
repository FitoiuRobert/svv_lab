package config;

public class Config {
	private int port = 10008; 
	private String state = "running" ;
//	private String state = "stopped" ;
	private String rootDir = "C:\\Users\\fitoiu\\Desktop\\workspace\\java_workspace\\TestSite";

	
	public int getPort() {
		return this.port;
	}
	
	public String getState() {
		return this.state;
	}
	
	public String getRootDir() {
		return this.rootDir;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}
	
	
	
}
