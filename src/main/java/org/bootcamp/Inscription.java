package org.bootcamp;

import java.util.concurrent.atomic.AtomicInteger;

public class Inscription {

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);
    Integer numberOfInscription = ID_GENERATOR.getAndIncrement();
    Category category;
    Competitor competitor;
    Double amount;

    public Inscription(Category category, Competitor competitor, Double amount) {
        this.category = category;
        this.competitor = competitor;
        this.amount = amount;
    }
    
}
