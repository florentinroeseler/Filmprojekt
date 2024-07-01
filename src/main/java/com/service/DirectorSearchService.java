package main.java.com.service;

import main.java.com.model.Director;

import java.util.List;

public class DirectorSearchService {
    private List<Director> directors;

    public DirectorSearchService(List<Director> directors) {
        this.directors = directors;
    }

    public Director searchDirectorByName(String name) {
        for (Director director : directors) {
            if (director.getName().equalsIgnoreCase(name)) {
                return director;
            }
        }
        return null;
    }
}
