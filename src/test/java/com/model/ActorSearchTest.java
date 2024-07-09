package test.java.com.model;

import main.java.com.model.Actor;
import main.java.com.service.DataImporter;
import main.java.com.service.ActorSearchService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class ActorSearchTest {

    private DataImporter importer;
    private ActorSearchService actorSearchService;

    @Before
    public void setUp() {
        importer = new DataImporter();
        importer.loadData("src/main/resources/movieproject2024.db"); // Adjust the path as needed
        actorSearchService = new ActorSearchService(importer.getActors());
    }

    @Test
    public void testSearchActor() {
        // Arrange
        String actorName = "Matthew McConaughey"; // Use an existing actor name from your dataset

        // Act
        List<Actor> actors = actorSearchService.searchActorByName(actorName);

        // Assert
        assertNotNull("Actors should be found", actors);
        assertFalse("Actors list should not be empty", actors.isEmpty());
        boolean found = false;
        for (Actor actor : actors) {
            if (actor.getName().equals(actorName)) {
                found = true;
                break;
            }
        }
        assertTrue("The actor with the exact name should be found", found);
    }

    @Test
    public void testSearchActorById() {
        // Arrange
        int actorId = 10147; // Use an existing actor ID from your dataset

        // Act
        Actor actor = actorSearchService.searchActorById(actorId);

        // Assert
        assertNotNull("Actor should be found", actor);
        assertEquals("Actor ID should match", actorId, actor.getId());
    }
}
