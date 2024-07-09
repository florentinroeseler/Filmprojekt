//package test.java.com.model;
//
//import main.java.com.model.Actor;
//import main.java.com.service.DataImporter;
//import main.java.com.service.ActorSearchService;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class ActorSearchTest {
//
//    private DataImporter importer;
//    private ActorSearchService actorSearchService;
//
//    @Before
//    public void setUp() {
//        importer = new DataImporter();
//        importer.loadData("src/main/resources/movieproject2024.db"); // Adjust the path as needed
//        actorSearchService = new ActorSearchService(importer.getActors());
//    }
//
//    @Test
//    public void testSearchActor() {
//        // Arrange
//        String actorName = "Matthew McConaughey"; // Use an existing actor name from your dataset
//
//        // Act
//        Actor actor = actorSearchService.searchActorByName(actorName);
//
//        // Assert
//        assertNotNull("main.java.com.model.Actor should be found", actor);
//        assertEquals("main.java.com.model.Actor name should match", actorName, actor.getName());
//    }
//}
