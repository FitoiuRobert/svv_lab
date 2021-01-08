package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;
import static org.mockito.Mockito.*;

public class test {

	@Test
    public void invocation() {
         //mock creation
         List<String> mockedList = mock(List.class);

         //using mock object
         mockedList.add("one");
         mockedList.clear();

         //verification
         verify(mockedList).add("one");
         verify(mockedList).clear();
     }

}
