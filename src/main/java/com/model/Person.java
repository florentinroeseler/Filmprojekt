package main.java.com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Person {
    private int id;
    private String name;
    private List<Film> films;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
        this.films = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void addFilm(Film film) {
        this.films.add(film);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
