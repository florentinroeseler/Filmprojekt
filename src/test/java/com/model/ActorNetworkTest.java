package test.java.com.model;

import main.java.com.service.DataImporter;
import main.java.com.service.ActorSearchService;
import main.java.com.service.FilmSearchService;
import main.java.com.service.NetworkService;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ActorNetworkTest {

    private DataImporter importer;
    private ActorSearchService actorSearchService;
    private FilmSearchService filmSearchService;
    private NetworkService networkService;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        importer = new DataImporter();
        importer.loadData("src/main/resources/movieproject2024.db");
        actorSearchService = new ActorSearchService(importer.getActors());
        filmSearchService = new FilmSearchService(importer.getFilms());
        networkService = new NetworkService(filmSearchService, actorSearchService);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testPrintActorNetwork() {
        // Arrange
        int actorId = 17562;

        // Act
        networkService.printActorNetwork(actorId);

        // Assert
        String output = outContent.toString();
        assertTrue("Output should contain the actor's name", output.contains("Gabrielle Union"));
        assertTrue("Output should contain related films", output.contains("Suicide Squad"));
    }
}

