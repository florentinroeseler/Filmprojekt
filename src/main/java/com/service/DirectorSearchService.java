package main.java.com.service;

import main.java.com.model.Director;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for searching directors in a list.
 */
public class DirectorSearchService {
    private List<Director> directors;

    // Constructor
    public DirectorSearchService(List<Director> directors) {
        this.directors = directors;
    }

    // Search director by name in the list of directors
    // The search is case-insensitive and checks if the director's name contains the specified name.
    public List<Director> searchDirectorByName(String name) {
        String lowerCaseName = name.toLowerCase();
        List<Director> matchingDirectors = new ArrayList<>();
        for (Director director : directors) {
            if (director.getName().toLowerCase().contains(lowerCaseName)) {
                matchingDirectors.add(director);
            }
        }
        return matchingDirectors;
    }
}
