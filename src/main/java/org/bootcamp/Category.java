package org.bootcamp;

import java.util.concurrent.atomic.AtomicInteger;

public class Category {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);
    Integer id = ID_GENERATOR.getAndIncrement();
    String name;
    String description;
    Double amountForNoAdults;
    Double amountForAdults;

    public Category(String name, String description, Double amountForNoAdults, Double amountForAdults) {
        this.name = name;
        this.description = description;
        this.amountForNoAdults = amountForNoAdults;
        this.amountForAdults = amountForAdults;
    }

    public double getAmountByAge(int age) throws Exception {
        double amountToReturn = age < 18 ? amountForNoAdults : amountForAdults;
        if (amountToReturn == Main.AMOUNT_NO_AVAILABE_FOR_AGE) throw new NotAllowedByAgeException(name, age);
        return amountToReturn;
    }

}
