package webserver;

import java.io.IOException;
import java.net.*;

import config.*;
import exceptions.IllegalPropertyException;

public class WebServer extends Thread{
	private ServerSocket serverSocket;
	private Config config = new Config();
	private String serverState;

	public WebServer() throws IOException, IllegalPropertyException {
		this.serverState = config.getState();
		this.serverSocket = new ServerSocket(config.getPort());
		start();
	}

	
	@Override
	public void run() {
		System.out.println("Starting Web Server...");
		while(true) {
			try {
				serverState = config.getState();
			} catch (IOException e1) {
				e1.printStackTrace();
				System.exit(1);
			} catch (IllegalPropertyException e1) {
				e1.printStackTrace();
				System.exit(1);
			}
			switch (serverState) {
				case "running":
					try {
						System.out.println("Ready at: 127.0.0.1:"+ config.getPort());
						Socket clientSocket = serverSocket.accept();
						Client client = new Client(clientSocket, config.getRootDir());
						client.start();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				case "maintainence": 
					break;

				case "stopped":
					break;

				default:
					throw new IllegalArgumentException("Unexpected value: " + serverState);
			}
		}
	}
	
	public void setWebServerState(String state) throws IOException, IllegalPropertyException {
		config.setState(state);
		this.serverState = state;
	}
	
	public String getServerState() throws IOException, IllegalPropertyException {
		return this.serverState;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new WebServer();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
