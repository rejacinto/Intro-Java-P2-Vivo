package org.bootcamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    final static Double AMOUNT_NO_AVAILABE_FOR_AGE = -1.0;

    public static void main( String[] args ) {
        Category smallCircuit = new Category("Circuito chico", "Menores de 18 años $1300. Mayores de 18 años $1500.", 1300.0, 1500.0);
        Category mediumCircuit = new Category("Circuito medio", "Menores de 18 años $2000. Mayores de 18 años $2300.", 2000.0, 2300.0);
        Category advancedCircuit = new Category("Circuito avanzado", "No se permite inscripciones a menores de 18 años. Mayores de 18 años $2800.", AMOUNT_NO_AVAILABE_FOR_AGE, 2800.0);

        Map<Integer, Category> categories = new HashMap<>();
        categories.put(smallCircuit.id, smallCircuit);
        categories.put(mediumCircuit.id, mediumCircuit);
        categories.put(advancedCircuit.id, advancedCircuit);

        Competitor competitor1 = new Competitor("12345678", "Fulano", "Sosa", 15, "2284219331", "2284453886", "0-");
        Competitor competitor2 = new Competitor("41211589", "Renzo", "Jacinto", 25, "2284219331", "2284453886", "0-");
        Competitor competitor3 = new Competitor("12345678", "Mengano", "Cauto", 15, "2284219331", "2284453886", "0-");
        Competitor competitor4 = new Competitor("12345678", "Suplicio", "Seco", 25, "2284219331", "2284453886", "0-");
        Competitor competitor5 = new Competitor("12345678", "Sultano", "Mango", 25, "2284219331", "2284453886", "0-");
        Competitor competitor6 = new Competitor("12345678", "Meleno", "Buto", 25, "2284219331", "2284453886", "0-");

        Map<Integer, Competitor> competitors = new HashMap<>();
        competitors.put(competitor1.numberOfCompetitor, competitor1);
        competitors.put(competitor2.numberOfCompetitor, competitor2);
        competitors.put(competitor3.numberOfCompetitor, competitor3);
        competitors.put(competitor4.numberOfCompetitor, competitor4);
        competitors.put(competitor5.numberOfCompetitor, competitor5);
        competitors.put(competitor6.numberOfCompetitor, competitor6);

        Map<Integer, List<Integer>> competitorsOfCategories = new HashMap<>();
        for (Category category : categories.values()) {
            competitorsOfCategories.put(category.id, new ArrayList<>());
        }

        try {
            suscribe(competitor1, smallCircuit, competitorsOfCategories);
            suscribe(competitor2, smallCircuit, competitorsOfCategories);
            suscribe(competitor3, mediumCircuit, competitorsOfCategories);
            suscribe(competitor4, mediumCircuit, competitorsOfCategories);
            suscribe(competitor5, advancedCircuit, competitorsOfCategories);
            suscribe(competitor6, advancedCircuit, competitorsOfCategories);

            showCompetitorsPerCategory(categories, competitors, competitorsOfCategories);
            unsuscribeCompetitorAndShowCategory(competitor1, competitors, competitorsOfCategories);
            showPerCategoryAndTotalAmount(competitorsOfCategories, categories, competitors);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void suscribe(Competitor competitor, Category category, Map<Integer, List<Integer>> competitorsOfCategories) throws Exception {
            competitor.suscribe(category);
            List<Integer> competitors = competitorsOfCategories.get(category.id);
            if (!competitors.contains(competitor.numberOfCompetitor)) {
                competitors.add(competitor.numberOfCompetitor);
                competitorsOfCategories.put(category.id, competitors);
            }
    }

    private static void showCompetitorsPerCategory(Map<Integer, Category> categories, Map<Integer, Competitor> competitors, Map<Integer, List<Integer>> competitorsOfCategories) {
        for (int categoryId : competitorsOfCategories.keySet()) {
            Category category = categories.get(categoryId);
            System.out.println("Inscriptos a la categoría " + category.name + ":");
            for (int competitorId : competitorsOfCategories.get(categoryId)) {
                showCompetitorOfCategory(competitorId, competitors, category);
            }
        }
    }

    private static void showCompetitorOfCategory(int competitorId, Map<Integer, Competitor> competitors, Category category) {
        Competitor competitor = competitors.get(competitorId);
        if (competitor.inscription == null || category.id != competitor.inscription.category.id) return;
        Inscription inscription = competitor.inscription;
        System.out.println("Con número de inscripción " + inscription.numberOfInscription + ": " + competitor.firstName + " " + competitor.lastName);
    }

    private static void unsuscribeCompetitorAndShowCategory(Competitor competitor, Map<Integer, Competitor> competitors, Map<Integer, List<Integer>> competitorsOfCategories) {
        Inscription inscription = competitor.inscription;
        if (inscription == null) {
            System.out.println("Competitor was not suscribed to any category");
            return;
        }
        Category category = inscription.category;
        competitor.unsuscribe();
        List<Integer> competitorsOfCategory = competitorsOfCategories.get(category.id);
        if (competitorsOfCategory.contains(competitor.numberOfCompetitor)) {
            competitorsOfCategory.remove(competitor.numberOfCompetitor);
            competitorsOfCategories.put(category.id, competitorsOfCategory);
        }
        System.out.println("Inscriptos a la categoría " + category.name + " luego de desinscribir a " + competitor.firstName + " " + competitor.lastName + ":");
        for (int competitorId : competitorsOfCategories.get(category.id)) {
            showCompetitorOfCategory(competitorId, competitors, category);
        }
    }

    private static void showPerCategoryAndTotalAmount(Map<Integer, List<Integer>> competitorsOfCategories, Map<Integer, Category> categories, Map<Integer, Competitor> competitors) {
        double total = 0;
        for (int categoryId : competitorsOfCategories.keySet()) {
            Category category = categories.get(categoryId);
            List<Integer> competitorsIdsOfCategory = competitorsOfCategories.get(categoryId);
            double sumOfAmounts = 0;
            for (int competitorId : competitorsIdsOfCategory) {
                double amountOfCompetitor = competitors.get(competitorId).inscription.amount;
                sumOfAmounts += amountOfCompetitor;
            }
            total += sumOfAmounts;
            System.out.println("La suma total para la categoría " + category.name + " es de: " + sumOfAmounts);
        }
        System.out.println("La suma total de todas las categorías fue de: " + total);
    }
}
