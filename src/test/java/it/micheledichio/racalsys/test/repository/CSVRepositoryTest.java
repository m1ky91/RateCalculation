package it.micheledichio.racalsys.test.repository;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import it.micheledichio.racalsys.model.Lender;
import it.micheledichio.racalsys.repository.CSVRepository;

public class CSVRepositoryTest {
	
	@Test(expected = FileNotFoundException.class)
	public void initWithAWrongCSVFilenameThrowsFileNotFoundException() throws FileNotFoundException {
		CSVRepository repository = new CSVRepository();
		repository.init("test.csv");
	}
	
	@Test
	public void aMalformedCSVFileProducesANullList() throws IOException {
		CSVRepository repository = new CSVRepository();
		repository.init("market.csv");
		List<Lender> lenders = repository.findAll();
		repository.close();
		
		assertTrue(lenders.isEmpty());
	}

}
