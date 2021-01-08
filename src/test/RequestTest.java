package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import config.Config;
import webserver.Request;
import exceptions.*;

import static org.mockito.Mockito.*;

import java.io.IOException;
import exceptions.*;

public class RequestTest {
//	Request mockedRequest;
//
//	@Before
//	public void createMockRequest() {
//		Request mockedRequest = mock(Request.class);
//	}
	
	@Test
	public void testRequestGuessContentType() throws IOException {
		// create mock object		
		//user mock object
		Request mockedRequest = mock(Request.class);
		mockedRequest.setPath("img.jpg");
		
		when(mockedRequest.guessContentType()).thenReturn("img");
//		when(mockedRequest.getPath()).thenReturn("");
		assertEquals("img", mockedRequest.guessContentType());
	}
	
	@Ignore
	@Test
	public void testIllegalRequestMethod() {
		Request mockedRequest = mock(Request.class);
		mockedRequest.setMethod("POST");
		
		when(mockedRequest.getMethod()).thenThrow(new exceptions.InvalidRequestMethodException());
//		try {
//			
//		}
	}
}
