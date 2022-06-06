package com.coderscampus.com.Assignment6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileService {

	public void salesGeneratorReport(List<DateSales> dataSales, String model) {

		System.out.printf("%s%17s", model, "Annual Sales" + "\n");
		System.out.println("-----------------------------------");

		Map<Integer, List<DateSales>> collectionOfYearData = dataSales.stream()
				.collect(Collectors.groupingBy(year -> year.getDate().getYear()));

		String annualSales = collectionOfYearData.entrySet().stream()
				.map(key -> key.getKey() + " ->     $"
						+ key.getValue().stream().collect(Collectors.summingInt(DateSales::getSales)))
				.collect(Collectors.joining("\n"));

		System.out.println(annualSales + "\n");

		Optional<DateSales> highestSales = dataSales.stream()
				.max((DateSales o1, DateSales o2) -> o1.getSales().compareTo(o2.getSales()));

		Optional<DateSales> lowestSales = dataSales.stream()
				.min((DateSales o1, DateSales o2) -> o1.getSales().compareTo(o2.getSales()));

		System.out.println(
				"The best month for " + model + " was: " + highestSales.orElse(new DateSales("Jan-70", "0")).getDate());

		System.out.println("The worst month for " + model + " was: "
				+ lowestSales.orElse(new DateSales("Jan-70", "0")).getDate() + "\n");
	}

	public List<DateSales> readFile(String file) throws IOException {
		List<DateSales> dateSales = new ArrayList<>();
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while ((line = reader.readLine()) != null) {
				String[] elementData = line.split(",");
				dateSales.add(new DateSales(elementData[0], (elementData[1])));
			}
			return dateSales;
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception ex) {
				System.out.println("Reader cannot be closed");
				ex.printStackTrace();
			}
		}
	}
}