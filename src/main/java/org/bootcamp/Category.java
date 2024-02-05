package org.bootcamp;

import java.util.concurrent.atomic.AtomicInteger;

public class Category {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);
    Integer id = ID_GENERATOR.getAndIncrement();
    String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmountForNoAdults() {
        return amountForNoAdults;
    }

    public void setAmountForNoAdults(Double amountForNoAdults) {
        this.amountForNoAdults = amountForNoAdults;
    }

    public Double getAmountForAdults() {
        return amountForAdults;
    }

    public void setAmountForAdults(Double amountForAdults) {
        this.amountForAdults = amountForAdults;
    }

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
