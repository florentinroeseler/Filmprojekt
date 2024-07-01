package test.java.com.service;

import main.java.com.model.Actor;
import main.java.com.model.Film;
import main.java.com.service.DataImporter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DataImporterTest {

    private DataImporter importer;

    @Before
    public void setUp() {
        importer = new DataImporter();
        importer.loadData("C:\\Users\\flore\\IdeaProjects\\Filmprojekt\\src\\main\\resources\\movieproject2024.db"); // Adjust the path as needed
    }

    @Test
    public void testSearchFilm() {
        // Arrange
        String filmTitle = "Interstellar"; // Use an existing film title from your dataset

        // Act
        Film film = importer.searchFilmByTitle(filmTitle);

        // Assert
        assertNotNull("main.java.com.model.Film should be found", film);
        assertEquals("main.java.com.model.Film title should match", filmTitle, film.getTitle());
    }

    @Test
    public void testSearchActor() {
        // Arrange
        String actorName = "Matthew McConaughey"; // Use an existing actor name from your dataset

        // Act
        Actor actor = importer.searchActorByName(actorName);

        // Assert
        assertNotNull("main.java.com.model.Actor should be found", actor);
        assertEquals("main.java.com.model.Actor name should match", actorName, actor.getName());
    }
}
