package webserver;

import java.util.List;

public class Request {

	private String method;
    private String path;
    private String version;
    private String host;
    private List<String> headers;

    public Request(String method, String path, String version, String host, List<String> headers) {
    	this.method = method;
    	this.path = path;
    	this.version = version;
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


}
