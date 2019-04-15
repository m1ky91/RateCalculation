package it.micheledichio.racalsys.test.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.strictMock;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;

import it.micheledichio.racalsys.model.Lender;
import it.micheledichio.racalsys.repository.CSVRepository;
import it.micheledichio.racalsys.service.LenderExtractor;

public class LenderExtractorTest {
	
	@Test
	public void aListOfLendersEmptyDoesNotProduceAValidSet() throws IOException {
		CSVRepository repository = strictMock(CSVRepository.class);
		
		LenderExtractor lenderExtractor = new LenderExtractor(repository);
		
		repository.init("marketFilename");
		expectLastCall();
		expect(repository.findAll()).andReturn(null);
		repository.close();
		expectLastCall();
		replay(repository);
		
		Set<Lender> lenders = lenderExtractor.extractOrderedLenders("marketFilename");
		assertTrue(lenders == null);
		
		verify(repository);
	}

}
