package test.java.com.model;

import main.java.com.model.Director;
import main.java.com.service.DataImporter;
import main.java.com.service.DirectorSearchService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class DirectorSearchTest {

    private DataImporter importer;
    private DirectorSearchService directorSearchService;

    @Before
    public void setUp() {
        importer = new DataImporter();
        importer.loadData("src/main/resources/movieproject2024.db"); // Adjust the path as needed
        directorSearchService = new DirectorSearchService(importer.getDirectors());
    }

    @Test
    public void testSearchDirector() {
        // Arrange
        String directorName = "Christopher Nolan"; // Use an existing director name from your dataset

        // Act
        List<Director> directors = directorSearchService.searchDirectorByName(directorName);

        // Assert
        assertNotNull("Directors should be found", directors);
        assertFalse("Directors list should not be empty", directors.isEmpty());
        boolean found = false;
        for (Director director : directors) {
            if (director.getName().equals(directorName)) {
                found = true;
                break;
            }
        }
        assertTrue("The director with the exact name should be found", found);
    }

    @Test
    public void testSearchDirectorById() {
        // Arrange
        int directorId = 28814; // Use an existing director ID from your dataset

        // Act
        Director director = directorSearchService.searchDirectorById(directorId);

        // Assert
        assertNotNull("Director should be found", director);
        assertEquals("Director ID should match", directorId, director.getId());
    }
}
