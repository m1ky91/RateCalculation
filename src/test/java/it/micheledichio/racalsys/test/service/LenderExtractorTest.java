package it.micheledichio.racalsys.test.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.strictMock;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

	@Test
	public void whenAMarketFileNameRefersToAFileNotPresentReturnsANullSet() {
		CSVRepository repository = new CSVRepository();
		LenderExtractor lenderExtractor = new LenderExtractor(repository);
		Set<Lender> lenders = lenderExtractor.extractOrderedLenders("marketFilename");
		assertTrue(lenders == null);
	}

	@Test
	public void aNotEmptyListOfLendersReturnsAnOrderedSetOfLenders() throws IOException {
		CSVRepository repository = strictMock(CSVRepository.class);

		LenderExtractor lenderExtractor = new LenderExtractor(repository);

		List<Lender> lenderList = Arrays.asList(
				new Lender("Dave", new BigDecimal("0.074"), new BigDecimal("140")),
				new Lender("Jane", new BigDecimal("0.069"), new BigDecimal("480")));
		List<Lender> lenderListSorted = Arrays.asList(
				new Lender("Jane", new BigDecimal("0.069"), new BigDecimal("480")),
				new Lender("Dave", new BigDecimal("0.074"), new BigDecimal("140")));

		repository.init("marketFilename");
		expectLastCall();
		expect(repository.findAll()).andReturn(lenderList);
		repository.close();
		expectLastCall();
		replay(repository);

		TreeSet<Lender> lenders = (TreeSet<Lender>) lenderExtractor.extractOrderedLenders("marketFilename");

		assertEquals(lenders.size(), lenderList.size());
		lenderListSorted.stream().forEach(l -> {
			assertEquals(l, lenders.first());
			lenders.remove(lenders.first());
		});

		verify(repository);
	}

}
