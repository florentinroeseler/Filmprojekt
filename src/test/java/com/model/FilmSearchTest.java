package test.java.com.model;

import main.java.com.model.Film;
import main.java.com.service.DataImporter;
import main.java.com.service.FilmSearchService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class FilmSearchTest {

    private DataImporter importer;
    private FilmSearchService filmSearchService;

    @Before
    public void setUp() {
        importer = new DataImporter();
        importer.loadData("src/main/resources/movieproject2024.db");
        filmSearchService = new FilmSearchService(importer.getFilms());
    }

    @Test
    public void testSearchFilm() {
        // Arrange
        String filmTitle = "Interstellar";

        // Act
        List<Film> films = filmSearchService.searchFilmByTitle(filmTitle);

        // Assert
        assertNotNull("Films should be found", films);
        assertFalse("Films list should not be empty", films.isEmpty());
        boolean found = false;
        for (Film film : films) {
            if (film.getTitle().equals(filmTitle)) {
                found = true;
                break;
            }
        }
        assertTrue("The film with the exact title should be found", found);
    }

    @Test
    public void testSearchFilmById() {
        // Arrange
        int filmId = 1491;

        // Act
        Film film = filmSearchService.searchFilmById(filmId);

        // Assert
        assertNotNull("Film should be found", film);
        assertEquals("Film ID should match", filmId, film.getId());
    }
}
