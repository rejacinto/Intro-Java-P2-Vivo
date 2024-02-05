package org.bootcamp;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    final static Double AMOUNT_NO_AVAILABE_FOR_AGE = -1.0;

    public static void main( String[] args ) {
        Category smallCircuit = new Category("Circuito chico", "Menores de 18 años $1300. Mayores de 18 años $1500.", 1300.0, 1500.0);
        Category mediumCircuit = new Category("Circuito medio", "Menores de 18 años $2000. Mayores de 18 años $2300.", 2000.0, 2300.0);
        Category advancedCircuit = new Category("Circuito avanzado", "No se permite inscripciones a menores de 18 años. Mayores de 18 años $2800.", AMOUNT_NO_AVAILABE_FOR_AGE, 2800.0);

        List<Category> categories = new ArrayList<>(Arrays.asList(smallCircuit, mediumCircuit, advancedCircuit));

        Competitor competitor1 = new Competitor("12345678", "Fulano", "Sosa", 15, "2284219331", "2284453886", "0-");
        Competitor competitor2 = new Competitor("41211589", "Renzo", "Jacinto", 25, "2284219331", "2284453886", "0-");
        Competitor competitor3 = new Competitor("12345678", "Mengano", "Cauto", 15, "2284219331", "2284453886", "0-");
        Competitor competitor4 = new Competitor("12345678", "Suplicio", "Seco", 25, "2284219331", "2284453886", "0-");
        Competitor competitor5 = new Competitor("12345678", "Sultano", "Mango", 25, "2284219331", "2284453886", "0-");
        Competitor competitor6 = new Competitor("12345678", "Meleno", "Buto", 25, "2284219331", "2284453886", "0-");

        List<Competitor> competitors = new ArrayList<>(Arrays.asList(competitor1, competitor2, competitor3, competitor4, competitor5, competitor6));

        try {
            competitor1.suscribe(smallCircuit);
            competitor2.suscribe(smallCircuit);
            competitor3.suscribe(mediumCircuit);
            competitor4.suscribe(mediumCircuit);
            competitor5.suscribe(advancedCircuit);
            competitor6.suscribe(advancedCircuit);

            showCompetitorsPerCategory(categories, competitors);
            unsuscribeCompetitorAndShowCategory(competitor1, competitors);
            showPerCategoryAndTotalAmount(categories, competitors);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showCompetitorsPerCategory(List<Category> categories, List<Competitor> competitors) {
        for (Category category : categories) {
            System.out.println("Inscriptos a la categoría " + category.getName() + ":");
            competitors.stream()
                    .filter(c -> c.getInscription() != null
                            && c.getInscription().getCategory().getId().equals(category.getId()))
                    .forEach(c -> showCompetitorOfCategory(c.getNumberOfCompetitor(), competitors, category));
        }
    }

    private static void showCompetitorOfCategory(int competitorId, List<Competitor> competitors, Category category) {
        Competitor competitor = competitors.stream().filter(c -> c.getNumberOfCompetitor() == competitorId).collect(Collectors.toList()).get(0);
        if (competitor == null || competitor.getInscription() == null
                || !category.getId().equals(competitor.getInscription().getCategory().getId())) return;
        Inscription inscription = competitor.getInscription();
        System.out.println("Con número de inscripción " + inscription.getNumberOfInscription() + ": " + competitor.getFirstName() + " " + competitor.getLastName());
    }

    private static void unsuscribeCompetitorAndShowCategory(Competitor competitor, List<Competitor> competitors) {
        Inscription inscription = competitor.getInscription();
        if (inscription == null) {
            System.out.println("El participante no se inscribió a ninguna categoría");
            return;
        }
        Category category = inscription.getCategory();
        competitor.unsuscribe();
        System.out.println("Inscriptos a la categoría " + category.getName() + " luego de desinscribir a " + competitor.getFirstName() + " " + competitor.getLastName() + ":");
        competitors.stream()
                .filter(c -> c.getInscription() != null
                        && c.getInscription().getCategory().getId().equals(category.getId()))
                .forEach(c -> showCompetitorOfCategory(c.getNumberOfCompetitor(), competitors, category));
    }

    private static void showPerCategoryAndTotalAmount(List<Category> categories, List<Competitor> competitors) {
        double total = 0;
        for (Category category : categories) {
            double sumOfAmounts = 0;
            List<Competitor> competitorsOfCategory = competitors.stream()
                    .filter(c -> c.getInscription() != null
                            && c.getInscription().getCategory().getId().equals(category.getId())).collect(Collectors.toList());
            for (Competitor competitor : competitorsOfCategory) {
                double amountOfCompetitor = competitor.getInscription().getAmount();
                sumOfAmounts += amountOfCompetitor;
            }
            total += sumOfAmounts;
            System.out.println("La suma total para la categoría " + category.getName() + " es de: " + sumOfAmounts);
        }
        System.out.println("La suma total de todas las categorías fue de: " + total);
    }
}
