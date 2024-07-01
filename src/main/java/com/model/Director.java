package main.java.com.model;

public class Director extends Person {

    public Director(int id, String name) {
        super(id, name);
    }

    @Override
    public void addFilm(Film film) {
        super.addFilm(film);
        if (!film.getDirectors().contains(this)) {
            film.addDirector(this);
        }
    }
}
