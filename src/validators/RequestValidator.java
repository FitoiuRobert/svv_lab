package validators;

public class RequestValidator {

	public Boolean isValidMethod(String method) {
		return method.equals("GET");
	}
	
	public Boolean isValidVersion(String version) {
		return version.equals("HTTP/1.1");
	}
	
	public Boolean isValidHost(String host) {
		return host.startsWith("127.0.0.1:") || host.startsWith("localhost:");
	}
}
