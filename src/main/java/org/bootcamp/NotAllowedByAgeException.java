package org.bootcamp;

public class NotAllowedByAgeException extends Exception {

    public NotAllowedByAgeException(String name, int age) {
        super("Trataste de inscribir a un participante de " + age + " años de edad a la categoría " + name);
    }

}
