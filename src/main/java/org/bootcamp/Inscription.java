package org.bootcamp;

import java.util.concurrent.atomic.AtomicInteger;

public class Inscription {

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);
    Integer numberOfInscription = ID_GENERATOR.getAndIncrement();
    Category category;
    Competitor competitor;

    public Integer getNumberOfInscription() {
        return numberOfInscription;
    }

    public void setNumberOfInscription(Integer numberOfInscription) {
        this.numberOfInscription = numberOfInscription;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Competitor getCompetitor() {
        return competitor;
    }

    public void setCompetitor(Competitor competitor) {
        this.competitor = competitor;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    Double amount;

    public Inscription(Category category, Competitor competitor, Double amount) {
        this.category = category;
        this.competitor = competitor;
        this.amount = amount;
    }
    
}
