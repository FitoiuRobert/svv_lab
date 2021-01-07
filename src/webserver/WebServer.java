package webserver;

import java.io.IOException;
import java.net.*;

import config.*;

public class WebServer extends Thread{
	private ServerSocket serverSocket;
	private Config config = new Config();
	private String serverState;

	public WebServer() throws IOException {
		this.serverState = config.getState();
		this.serverSocket = new ServerSocket(config.getPort());
		start();
	}

	
	@Override
	public void run() {
		System.out.println("Starting Web Server...");
		while(true) {
			switch (serverState) {
				case "running":
					System.out.println("Ready at: 127.0.0.1:"+ config.getPort());
					try {
						Socket clientSocket = serverSocket.accept();
						Client client = new Client(clientSocket, config.getRootDir());
						client.start();
					} catch (IOException e) {
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new WebServer();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
