package com.coderscampus.com.Assignment6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {


		
		public List<DateSales> readFile(String file) throws IOException {
            List<DateSales> dateSales = new ArrayList<>();
			BufferedReader reader = null;

			try {
				reader = new BufferedReader(new FileReader(file));
				String line = reader.readLine();
				while ((line = reader.readLine()) != null) {
					String[] elementData = line.split(",");
					dateSales.add(new DateSales(elementData[0],(elementData[1])));
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