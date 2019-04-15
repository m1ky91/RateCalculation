package it.micheledichio.racalsys.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import it.micheledichio.racalsys.model.Lender;
import it.micheledichio.racalsys.repository.CSVRepository;

public class LenderExtractor {
	
	private CSVRepository repository;

	public LenderExtractor(CSVRepository repository) {
		this.repository = repository;
	}

	public LenderExtractor() {
		this.repository = new CSVRepository();
	}

	public Set<Lender> extractOrderedLenders(String marketFilename) {
		try {
			repository.init(marketFilename);
			repository.findAll();
			repository.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
