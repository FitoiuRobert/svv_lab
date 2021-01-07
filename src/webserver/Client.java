package webserver;

import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Client extends Thread {
	
	private Socket clientSocket;
	private static String rootDir;

	public Client (Socket clientSocket, String rootDir) {
		this.clientSocket = clientSocket;
		Client.rootDir = rootDir;
	}
	
	@Override
	public void run(){
		try {
			BufferedReader clientInputStream = getClientInputStream(clientSocket);

			String rawRequest = getRawRequest(clientInputStream);
			Request request = parseRequest(rawRequest);
			
	        Path filePath = getFilePath(request.getPath());
	        if (Files.exists(filePath)) {
	            // file exist
	            String contentType = guessContentType(filePath);
	            sendResponse(clientSocket, "200 OK", contentType, Files.readAllBytes(filePath));
	        } else {
	            // 404
	        	System.out.println(filePath);
	            byte[] notFoundContent = "<h1>Not found :(</h1>".getBytes();
	            sendResponse(clientSocket, "404 Not Found", "text/html", notFoundContent);
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private BufferedReader getClientInputStream(Socket clientSocket) throws IOException {
		return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	private String getRawRequest(BufferedReader clientInputStream) throws IOException {
		StringBuilder requestBuilder = new StringBuilder();
		String line;
		
		while (!(line = clientInputStream.readLine()).isBlank()) {
			requestBuilder.append(line + "\r\n");
		}
		
		return requestBuilder.toString();
	}

	private Request parseRequest(String rawRequest) throws IOException {

        String[] requestsLines = rawRequest.split("\r\n");
        String[] requestLine = requestsLines[0].split(" ");
        String method = requestLine[0];
        String path = requestLine[1];
        String version = requestLine[2];
        String host = requestsLines[1].split(" ")[1];

        List<String> headers = new ArrayList<>();
        for (int h = 2; h < requestsLines.length; h++) {
            String header = requestsLines[h];
            headers.add(header);
        }

        String accessLog = String.format("clientSocket %s, method %s, path %s, version %s, host %s, headers %s",
                clientSocket.toString(), method, path, version, host, headers.toString());
        System.out.println(accessLog);
        
        return new Request(method, path, version, host, headers);
		
	}
	
    private static void sendResponse(Socket clientSocket, String status, String contentType, byte[] content) throws IOException {
        OutputStream clientSocketOutput = clientSocket.getOutputStream();
        clientSocketOutput.write(("HTTP/1.1 \r\n" + status).getBytes());
        clientSocketOutput.write(("ContentType: " + contentType + "\r\n").getBytes());
        clientSocketOutput.write("\r\n".getBytes());
        clientSocketOutput.write(content);
        clientSocketOutput.write("\r\n\r\n".getBytes());
        clientSocketOutput.flush();
        clientSocket.close();
    }
    
    private static Path getFilePath(String path) throws UnsupportedEncodingException {
        if ("/".equals(path)) {
            path = "index.html";
        }

        return Paths.get(rootDir, URLDecoder.decode(path, "UTF-8"));
    }
    
    private static String guessContentType(Path filePath) throws IOException {
        return Files.probeContentType(filePath);
    }
}
