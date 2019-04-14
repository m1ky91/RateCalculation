package it.micheledichio.racalsys.util;

import java.io.File;

public class ArgsHandler {
	
	private String[] args;

	public ArgsHandler(String[] args) {
		this.args = args;
	}

	public boolean argsValid() {
		if (args.length != 2) {
			System.out.println("Please provide correct number of args [market_file] [loan_amount]");
			return false;
		} else {
			File file = new File(args[0]);
			if (!file.exists()) {
				System.out.println("Please provide a name of an existing file [market_file] [loan_amount]");
				return false;
			} else {
				String fileExtension = args[0].substring(args[0].lastIndexOf('.') + 1);
				if (!fileExtension.equalsIgnoreCase("csv")) {
					System.out.println("Please provide a name of a CSV file [market_file] [loan_amount]");
					return false;
				} else {
					Integer amount = 0;
					try {
						amount = Integer.parseInt(args[1]);
			        } catch (NumberFormatException e) {
			        	System.out.println("Please provide a valid number for loan amount [market_file] [loan_amount]");
			            return false;
			        }
					if (amount % 100 != 0) {
						System.out.println("Please provide a number for loan amount of any 100 [market_file] [loan_amount]");
						return false;
					} else if (amount < 1000){
						System.out.println("Please provide a number for loan amount greater than or equal to 1000 [market_file] [loan_amount]");
						return false;
					} else if (amount > 15000){
						System.out.println("Please provide a number for loan amount less than or equal to 15000 [market_file] [loan_amount]");
						return false;
					} else {
						return true;
					}
				}
			}
		}
	}
	
}
