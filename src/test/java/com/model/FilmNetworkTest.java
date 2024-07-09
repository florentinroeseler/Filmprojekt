package test.java.com.model;

import main.java.com.service.DataImporter;
import main.java.com.service.FilmSearchService;
import main.java.com.service.ActorSearchService;
import main.java.com.service.NetworkService;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class FilmNetworkTest {

    private DataImporter importer;
    private FilmSearchService filmSearchService;
    private ActorSearchService actorSearchService;
    private NetworkService networkService;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        importer = new DataImporter();
        importer.loadData("src/main/resources/movieproject2024.db");
        filmSearchService = new FilmSearchService(importer.getFilms());
        actorSearchService = new ActorSearchService(importer.getActors());
        networkService = new NetworkService(filmSearchService, actorSearchService);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testPrintFilmNetwork() {
        // Arrange
        int filmId = 1491;

        // Act
        networkService.printFilmNetwork(filmId);

        // Assert
        String output = outContent.toString();
        assertTrue("Output should contain the film's title", output.contains("The Spirits Within"));
        assertTrue("Output should contain related actors", output.contains("Soon-Tek Oh"));
    }
}
