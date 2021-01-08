package webserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import validators.RequestValidator;
import exceptions.*;

public class Request {

	private String method;
    private String path;
    private String version;
    private String host;
    private List<String> headers;
    private RequestValidator requestValidator = new RequestValidator();

    public Request(String method, String path, String version, String host, List<String> headers) throws InvalidRequestMethodException, InvalidRequestVersion {
    	if(! requestValidator.isValidMethod(method)) {
    		throw new InvalidRequestMethodException();
    	} else {
    		this.method = method;
    	}
    	this.path = path;
    	if(! requestValidator.isValidVersion(version)) {
    		throw new InvalidRequestVersion();
    	} else {
    		this.version = version;
    	}
    	this.host = host;
    	this.headers = headers;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public String prepareRequestedContent(String path) {
		return "<html>Hello there</html>";
		
	}
	
    public String guessContentType() throws IOException {
        return Files.probeContentType(Paths.get(this.path));
    }
}
