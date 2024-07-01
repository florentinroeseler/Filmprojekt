package main.java.com.service;

import main.java.com.model.Actor;
import main.java.com.model.Film;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NetworkService {

    private FilmSearchService filmSearchService;
    private ActorSearchService actorSearchService;

    public NetworkService(FilmSearchService filmSearchService, ActorSearchService actorSearchService) {
        this.filmSearchService = filmSearchService;
        this.actorSearchService = actorSearchService;
    }

    public void printFilmNetwork(String title) {
        Film film = filmSearchService.searchFilmByTitle(title);
        if (film != null) {
            Set<Actor> actorsInFilm = new HashSet<>(film.getActors());
            Set<Film> relatedFilms = new HashSet<>();

            // Find films related to these actors
            for (Actor actor : actorsInFilm) {
                relatedFilms.addAll(actor.getFilms());
            }

            relatedFilms.remove(film); // Remove the original film from the related films

            System.out.println("Actors in " + film.getTitle() + ":");
            for (Actor actor : actorsInFilm) {
                System.out.println(" - " + actor.getName());
            }

            System.out.println("Related Films:");
            for (Film relatedFilm : relatedFilms) {
                System.out.println(" - " + relatedFilm.getTitle());
            }

            // Print statements for debugging
            System.out.println("Film is: " + (film == null ? "null" : "not null"));
            System.out.println("Number of actors in film: " + actorsInFilm.size());
            System.out.println("Number of related films: " + relatedFilms.size());
        } else {
            System.out.println("Film not found.");
        }
    }

    public void printActorNetwork(String actorName) {
        Actor actor = actorSearchService.searchActorByName(actorName);
        if (actor != null) {
            Set<Film> filmsActedIn = new HashSet<>(actor.getFilms());
            Set<Actor> relatedActors = new HashSet<>();

            // Find actors related to these films
            for (Film film : filmsActedIn) {
                relatedActors.addAll(film.getActors());
            }

            System.out.println("Films acted in by " + actor.getName() + ":");
            for (Film film : filmsActedIn) {
                System.out.println(" - " + film.getTitle());
            }

            System.out.println("Related Actors:");
            for (Actor relatedActor : relatedActors) {
                System.out.println(" - " + relatedActor.getName());
            }
        } else {
            System.out.println("Actor not found.");
        }
    }
}
