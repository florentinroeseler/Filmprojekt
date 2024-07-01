package test.java.com.model;

import main.java.com.model.Film;
import main.java.com.service.DataImporter;
import main.java.com.service.FilmSearchService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FilmSearchTest {

    private DataImporter importer;
    private FilmSearchService filmSearchService;

    @Before
    public void setUp() {
        importer = new DataImporter();
        importer.loadData("src/main/resources/movieproject2024.db"); // Adjust the path as needed
        filmSearchService = new FilmSearchService(importer.getFilms());
    }

    @Test
    public void testSearchFilm() {
        // Arrange
        String filmTitle = "Interstellar"; // Use an existing film title from your dataset

        // Act
        Film film = filmSearchService.searchFilmByTitle(filmTitle);

        // Assert
        assertNotNull("main.java.com.model.Film should be found", film);
        assertEquals("main.java.com.model.Film title should match", filmTitle, film.getTitle());
    }
}
