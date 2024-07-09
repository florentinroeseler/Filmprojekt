package main.java.com.service;

import main.java.com.model.Actor;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for searching actors in a list.
 */
public class ActorSearchService {
    private List<Actor> actors;

    // Constructor
    public ActorSearchService(List<Actor> actors) {
        this.actors = actors;
    }

    // Search actor by name in the list of actors
    // The search is case-insensitive and checks if the actor's name contains the specified name.
    public List<Actor> searchActorByName(String name) {
        String lowerCaseName = name.toLowerCase();
        List<Actor> matchingActors = new ArrayList<>();
        for (Actor actor : actors) {
            if (actor.getName().toLowerCase().contains(lowerCaseName)) {
                matchingActors.add(actor);
            }
        }
        return matchingActors;
    }

    public Actor searchActorById(int id) {
        for (Actor actor : actors) {
            if (actor.getId() == id) {
                return actor;
            }
        }
        return null;
    }
}
