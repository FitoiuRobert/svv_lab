package validators;

public class ConfigValidator {
	public Boolean isValidProperty(String property) {
		return property.equals("port")
				|| property.equals("state")
				|| property.equals("rootDir");
	}

	public Boolean isValidState(String state) {
		return state.equals("running")
				|| state.equals("maintainance")
				|| state.equals("stopped");
	}
	
	public Boolean isValidPort(int port) {
		return port > 0
				&& port < 65536;
	}
	
}
