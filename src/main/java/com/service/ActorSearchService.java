package main.java.com.service;

import main.java.com.model.Actor;

import java.util.List;

public class ActorSearchService {
    private List<Actor> actors;

    public ActorSearchService(List<Actor> actors) {
        this.actors = actors;
    }

    public Actor searchActorByName(String name) {
        for (Actor actor : actors) {
            if (actor.getName().equalsIgnoreCase(name)) {
                return actor;
            }
        }
        return null;
    }
}
