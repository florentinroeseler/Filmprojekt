package main.java.com.model;

public class Actor extends Person {

    public Actor(int id, String name) {
        super(id, name);
    }

    @Override
    public void addFilm(Film film) {
        super.addFilm(film);
        if (!film.getActors().contains(this)) {
            film.addActor(this);
        }
    }
}
