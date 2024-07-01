package main.java.com.service;

import main.java.com.model.Film;

import java.util.List;

public class FilmSearchService {
    private List<Film> films;

    public FilmSearchService(List<Film> films) {
        this.films = films;
    }

    public Film searchFilmByTitle(String title) {
        for (Film film : films) {
            if (film.getTitle().equalsIgnoreCase(title)) {
                return film;
            }
        }
        return null;
    }
}
