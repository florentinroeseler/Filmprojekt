package main.java.com.service;

import main.java.com.model.Film;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for searching films by title.
 */
public class FilmSearchService {
    private List<Film> films;

    // Constructor
    public FilmSearchService(List<Film> films) {
        this.films = films;
    }

    // Search film by title in the list of films
    // The search is case-insensitive and checks if the film's title contains the specified title.
    public List<Film> searchFilmByTitle(String title) {
        String lowerCaseTitle = title.toLowerCase();
        List<Film> matchingFilms = new ArrayList<>();
        for (Film film : films) {
            if (film.getTitle().toLowerCase().contains(lowerCaseTitle)) {
                matchingFilms.add(film);
            }
        }
        return matchingFilms;
    }

    // Search film by ID in the list of films
    public Film searchFilmById(int id) {
        for (Film film : films) {
            if (film.getId() == id) {
                return film;
            }
        }
        return null;
    }

}
