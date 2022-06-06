package com.coderscampus.com.Assignment6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DateSalesReport {

	

	public static void main(String[] args) {

		try {

			FileService fileService = new FileService();
			List<DateSales> model3Report = fileService.readFile("model3.csv");
			List<DateSales> modelSReport = fileService.readFile("modelS.csv");
			List<DateSales> modelXReport = fileService.readFile("modelX.csv");
			
			fileService.salesGeneratorReport(model3Report, "Model 3");
			fileService.salesGeneratorReport(modelSReport, "Model S");
			fileService.salesGeneratorReport(modelXReport, "Model X");

		} catch (IOException e) {
			System.err.println(e.toString());
		}

	}
	
}