package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import config.*;
import exceptions.IllegalPropertyException;
import exceptions.InvalidPortRangeException;

public class ConfigTest {
	Config conf;
	
	@Before
	public void instatiateClass() {
		conf = new Config();
	}
	
	@Test
	public void updatePortValidTest() throws IOException, InvalidPortRangeException, IllegalPropertyException {
		conf.setPort(10088);
		assertEquals(10088, conf.getPort());
	}
	
	@Test(expected = InvalidPortRangeException.class)
	public void updatePortException() throws IOException, InvalidPortRangeException, IllegalPropertyException {
		conf.setPort(-10088);
	}
	
	@Test(expected = IllegalPropertyException.class)
	public void setIllegalPropertyTest() throws IOException, IllegalPropertyException {
		conf.updateProperty("unexistent", "123");
	}
	
}
