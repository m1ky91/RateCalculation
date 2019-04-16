package it.micheledichio.racalsys.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

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

	public TreeSet<Lender> extractOrderedLenders(String marketFilename) {
		TreeSet<Lender> lenderSet = new TreeSet<Lender>();
		List<Lender> lenderList = new ArrayList<Lender>();
		try {
			repository.init(marketFilename);
			lenderList = repository.findAll();
			repository.close();
		} catch (FileNotFoundException e) {
			System.out.println("Unexpected error opening the market file");
			return null;
		} catch (IOException e) {
			System.out.println("Unexpected error closing the market file");
			return null;
		}
		
		if (lenderList == null) {
			return null;
		} else {
			lenderList.stream().forEach(l -> lenderSet.add(l));
		}
		
		return lenderSet;
	}

}
