package it.micheledichio.racalsys.util;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ArgsHandlerTest {
	
	@Test
	public void argsLengthHandledCorrectly() {
		String[] args = {"test"};
		
		ArgsHandler argsHandler = new ArgsHandler(args);
		
		assertFalse(argsHandler.argsValid());
	}
	
	@Test
	public void firstArgIsAnExistingFile() {
		String[] args = {"market.file", "10"};
		
		ArgsHandler argsHandler = new ArgsHandler(args);
		
		assertFalse(argsHandler.argsValid());
	}
	
	@Test
	public void firstArgIsAnExistingCSVFile() {
		String[] args = {"market.txt", "10"};
		
		ArgsHandler argsHandler = new ArgsHandler(args);
		
		assertFalse(argsHandler.argsValid());
	}
	
	@Test
	public void secondArgIsAValidNumber() {
		String[] args = {"market.csv", "abc"};
		
		ArgsHandler argsHandler = new ArgsHandler(args);
		
		assertFalse(argsHandler.argsValid());
	}
	
	@Test
	public void secondArgIsANumberOfAny100Increment() {
		String[] args = {"market.csv", "1110"};
		
		ArgsHandler argsHandler = new ArgsHandler(args);
		
		assertFalse(argsHandler.argsValid());
	}
	
	@Test
	public void secondArgIsANumberGreaterThan1000() {
		String[] args = {"market.csv", "900"};
		
		ArgsHandler argsHandler = new ArgsHandler(args);
		
		assertFalse(argsHandler.argsValid());
	}
	
	@Test
	public void secondArgIsANumberLowerThan15000() {
		String[] args = {"market.csv", "16000"};
		
		ArgsHandler argsHandler = new ArgsHandler(args);
		
		assertFalse(argsHandler.argsValid());
	}

}
