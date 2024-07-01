package test.java.com.service;

import main.java.com.model.Actor;
import main.java.com.model.Film;
import main.java.com.service.DataImporter;
import main.java.com.service.FilmSearchService;
import main.java.com.service.ActorSearchService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DataImporterTest {

    private DataImporter importer;
    private FilmSearchService filmSearchService;
    private ActorSearchService actorSearchService;

    @Before
    public void setUp() {
        importer = new DataImporter();
        importer.loadData("src/main/resources/movieproject2024.db"); // Adjust the path as needed
        filmSearchService = new FilmSearchService(importer.getFilms());
        actorSearchService = new ActorSearchService(importer.getActors());
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

    @Test
    public void testSearchActor() {
        // Arrange
        String actorName = "Matthew McConaughey"; // Use an existing actor name from your dataset

        // Act
        Actor actor = actorSearchService.searchActorByName(actorName);

        // Assert
        assertNotNull("main.java.com.model.Actor should be found", actor);
        assertEquals("main.java.com.model.Actor name should match", actorName, actor.getName());
    }
}
