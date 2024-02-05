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

    public Integer getNumberOfCompetitor() {
        return numberOfCompetitor;
    }

    public void setNumberOfCompetitor(Integer numberOfCompetitor) {
        this.numberOfCompetitor = numberOfCompetitor;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Inscription getInscription() {
        return inscription;
    }

    public void setInscription(Inscription inscription) {
        this.inscription = inscription;
    }

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
