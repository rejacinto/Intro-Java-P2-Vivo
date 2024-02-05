package org.bootcamp;

import java.util.concurrent.atomic.AtomicInteger;

public class Competitor {

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);
    Integer numberOfCompetitor = ID_GENERATOR.getAndIncrement();
    String dni;
    String firstName;
    String lastName;
    Integer age;
    String phone;
    String emergencyPhone;
    String bloodType;
    Inscription inscription = null;

    public Competitor(String dni, String firstName, String lastName, int age, String phone,
                      String emergencyPhone, String bloodType) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.emergencyPhone = emergencyPhone;
        this.bloodType = bloodType;
    }

    public Inscription suscribe(Category category) throws Exception {
        this.inscription = new Inscription(category, this, category.getAmountByAge(age));
        return this.inscription;
    }

    public void unsuscribe() {
        this.inscription = null;
    }

}
