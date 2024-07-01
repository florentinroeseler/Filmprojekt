package test.java.com.model;

import main.java.com.model.Director;
import main.java.com.service.DataImporter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DirectorSearchTest {

    private DataImporter importer;

    @Before
    public void setUp() {
        importer = new DataImporter();
        importer.loadData("C:\\Users\\flore\\IdeaProjects\\Filmprojekt\\src\\main\\resources\\movieproject2024.db"); // Adjust the path as needed
    }

    @Test
    public void testSearchDirector() {
        // Arrange
        String directorName = "Christopher Nolan"; // Use an existing director name from your dataset

        // Act
        Director director = importer.searchDirectorByName(directorName);

        // Assert
        assertNotNull("main.java.com.model.Director should be found", director);
        assertEquals("main.java.com.model.Director name should match", directorName, director.getName());
    }
}
