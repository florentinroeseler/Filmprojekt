package main.java.com.service;

import main.java.com.model.Actor;
import main.java.com.model.Film;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Service class for printing film and actor networks.
 */
public class NetworkService {

    private FilmSearchService filmSearchService;
    private ActorSearchService actorSearchService;

    // Constructor
    public NetworkService(FilmSearchService filmSearchService, ActorSearchService actorSearchService) {
        this.filmSearchService = filmSearchService;
        this.actorSearchService = actorSearchService;
    }

    // Prints the network of a film, including its actors and related films.
    public void printFilmNetwork(int filmId) {
        Film film = filmSearchService.searchFilmById(filmId);
        if (film != null) {
            Set<Actor> actorsInFilm = new HashSet<>(film.getActors());
            Set<Film> relatedFilms = new HashSet<>();

            // Find films related to these actors
            for (Actor actor : actorsInFilm) {
                relatedFilms.addAll(actor.getFilms());
            }

            // Remove the original film from the related films
            relatedFilms.remove(film);

            // Print actors in the film
            System.out.println();
            System.out.print("Schauspieler: ");
            Iterator<Actor> actorIterator = actorsInFilm.iterator();
            while (actorIterator.hasNext()) {
                System.out.print(actorIterator.next().getName());
                if (actorIterator.hasNext()) {
                    System.out.print(", ");
                }
            }

            // Print related films
            System.out.println();
            System.out.print("Filme: ");
            Iterator<Film> filmIterator = relatedFilms.iterator();
            while (filmIterator.hasNext()) {
                System.out.print(filmIterator.next().getTitle());
                if (filmIterator.hasNext()) {
                    System.out.print(", ");
                }
            }
        } else {
            System.out.println("Film nicht gefunden.");
        }
    }

    // Prints the network of an actor, including the films they have acted in and related actors.
    public void printActorNetwork(int actorId) {
        Actor actor = actorSearchService.searchActorById(actorId);
        if (actor != null) {
            Set<Film> filmsActedIn = new HashSet<>(actor.getFilms());
            Set<Actor> relatedActors = new HashSet<>();

            // Find actors related to these films
            for (Film film : filmsActedIn) {
                relatedActors.addAll(film.getActors());
            }

            // Remove the original actor from the related actors
            relatedActors.remove(actor);

            // Print films the actor has acted in
            System.out.println();
            System.out.print("Filme: ");
            Iterator<Film> filmIterator = filmsActedIn.iterator();
            while (filmIterator.hasNext()) {
                System.out.print(filmIterator.next().getTitle());
                if (filmIterator.hasNext()) {
                    System.out.print(", ");
                }
            }

            // Print related actors
            System.out.println();
            System.out.print("Schauspieler: ");
            Iterator<Actor> actorIterator = relatedActors.iterator();
            while (actorIterator.hasNext()) {
                System.out.print(actorIterator.next().getName());
                if (actorIterator.hasNext()) {
                    System.out.print(", ");
                }
            }
        } else {
            System.out.println("Schauspieler nicht gefunden.");
        }
    }
}
