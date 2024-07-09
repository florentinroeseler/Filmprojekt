package main.java.com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class representing a person involved in films, such as an Actor or Director.
 * This class provides common properties and methods for its subclasses.
 */
public abstract class Person {
    private int id;
    private String name;
    private List<Film> films;

    // Constructor
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
        this.films = new ArrayList<>();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Film> getFilms() {
        return films;
    }

    // Add film to list of films
    public void addFilm(Film film) {
        this.films.add(film);
    }

    // Indicates whether some other object is "equal to" this one.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    // Returns a hash code value for the object.
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
