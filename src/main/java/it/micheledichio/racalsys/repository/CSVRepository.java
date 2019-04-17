package it.micheledichio.racalsys.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import it.micheledichio.racalsys.model.Lender;

public class CSVRepository implements AbstractRepository<Lender> {
	
	Logger logger = LoggerFactory.getLogger(CSVRepository.class);
	
	private String marketFilename;
	private Reader reader;
	private CSVParser parser;
	private CSVReader csvReader;
	
	public CSVRepository() {}
	
	public CSVRepository(CSVReader csvReader) {
		this.csvReader = csvReader;
	}

	@Override
	public List<Lender> findAll() {
		List<Lender> lenderList = new ArrayList<>();
		List<String[]> list = new ArrayList<>();
	    try {
			list = csvReader.readAll();
			list.stream().forEach(e -> {
				Lender lender = new Lender(e[0], new BigDecimal(e[1]), new BigDecimal(e[2]));
				lenderList.add(lender);
			});
		} catch (IOException | NullPointerException | NumberFormatException e) {
			logger.error("Unexpected error reading the market file");
			return null;
		}
	   
		return lenderList;
	}

	public void init(String marketFilename) throws FileNotFoundException {
		this.marketFilename = marketFilename;
		File initialFile = new File(this.marketFilename);
		this.reader = new FileReader(initialFile);
		this.parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(true).build();
		this.csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();
	}

	public void close() throws IOException {
		reader.close();
		csvReader.close();
	}

}
