package main.java.com.model;

public class Director extends Person {

    // Constructor
    public Director(int id, String name) {
        super(id, name);
    }

    // Add film to list of films
    // If the film does not contain the director, add the director to the film
    @Override
    public void addFilm(Film film) {
        super.addFilm(film);
        if (!film.getDirectors().contains(this)) {
            film.addDirector(this);
        }
    }
}
