package main.java.com.model;

/**
 * Class representing an actor involved in films.
 * This class extends the Person class and provides additional functionality specific to actors.
 */
public class Actor extends Person {

    // Constructor
    public Actor(int id, String name) {
        super(id, name);
    }

    // Add film to list of films
    // If the film does not contain the actor, add the actor to the film
    @Override
    public void addFilm(Film film) {
        super.addFilm(film);
        if (!film.getActors().contains(this)) {
            film.addActor(this);
        }
    }
}
