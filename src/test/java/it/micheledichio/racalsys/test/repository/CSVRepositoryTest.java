package it.micheledichio.racalsys.test.repository;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.opencsv.CSVReader;

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
	
	@Test 
	public void whenTheReaderReadsLinesAListOfLendersIsRedurded() throws IOException {
		List<Lender> lenderList = new ArrayList<>();
		lenderList.add(new Lender("Jane", new BigDecimal("0.069"), new BigDecimal("480")));
		List<String[]> list = new ArrayList<>();
		String[] row = {"Jane", "0.069", "480"};
		list.add(row);
		
		CSVReader csvReader = createMock(CSVReader.class);
		expect(csvReader.readAll()).andReturn(list);
		replay(csvReader);
		
		CSVRepository repository = new CSVRepository(csvReader);
		assertEquals(lenderList, repository.findAll());
		verify(csvReader);
	}	
	
	@Test 
	public void whenTheReaderThrowsIOExceptionANullListIsRedurded() throws IOException {
		CSVReader csvReader = createMock(CSVReader.class);
		expect(csvReader.readAll()).andThrow(new IOException());
		replay(csvReader);
		
		CSVRepository repository = new CSVRepository(csvReader);
		assertEquals(null, repository.findAll());
		verify(csvReader);
	}

}
