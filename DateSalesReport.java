package com.coderscampus.com.Assignment6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DateSalesReport {

	private List<DateSales> model3Report = new ArrayList<>();
	private List<DateSales> modelSReport = new ArrayList<>();
	private List<DateSales> modelXReport = new ArrayList<>();

	public static void main(String[] args) {

		try {

			FileService file = new FileService();
			List<DateSales> model3Report = file.readFile("model3.csv");
			List<DateSales> modelSReport = file.readFile("modelS.csv");
			List<DateSales> modelXReport = file.readFile("modelX.csv");
			
			salesStatement(model3Report, "Model 3");
			salesStatement(modelSReport, "Model S");
			salesStatement(modelXReport, "Model X");

		} catch (IOException e) {
			System.err.println(e.toString());
		}

	}
	private static void salesStatement(List<DateSales> dataSales, String model) {
		
		System.out.printf("%s%17s", model, "Annual Sales" + "\n");
		System.out.println("-----------------------------------");
		
		Map<Integer, List<DateSales>> collectionOfYearData = dataSales.stream()
		         .collect(Collectors.groupingBy(year -> year.getDate().getYear()));
		         
		String annualSales = collectionOfYearData.entrySet()
				             .stream()
				             .map(key -> key.getKey() + " ->     $" + key.getValue().stream()
				             .collect(Collectors.summingInt(DateSales::getSales)))
				             .collect(Collectors.joining("\n"));
		
		System.out.println(annualSales + "\n");

		Optional<DateSales> highestSales = dataSales.stream()
				                                    .max((DateSales o1, DateSales o2)-> o1.getSales().compareTo(o2.getSales()));
		
		Optional<DateSales> lowestSales = dataSales.stream()
                .min((DateSales o1, DateSales o2)-> o1.getSales().compareTo(o2.getSales()));
		
		System.out.println("The best month for " + model + " was: " + highestSales.orElse(new DateSales("Jan-70", "0")).getDate());
		         
		System.out.println("The worst month for " + model + " was: " + lowestSales.orElse(new DateSales("Jan-70", "0")).getDate()+ "\n");
	}
	
}