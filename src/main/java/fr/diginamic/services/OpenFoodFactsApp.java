package fr.diginamic.services;

import fr.diginamic.utils.OpenFoodFactsIntegrationCSV;

public class OpenFoodFactsApp {

	public static void main(String[] args) {
		OpenFoodFactsIntegrationCSV off = new OpenFoodFactsIntegrationCSV();

		off.Convert();

	}

}
