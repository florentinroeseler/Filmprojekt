package main.java.com.service;

import main.java.com.model.Actor;
import main.java.com.model.Director;
import main.java.com.model.Film;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Service class for importing data from a file and populating lists of films, actors, and directors.
 */
public class DataImporter {
    private List<Film> films;
    private List<Actor> actors;
    private List<Director> directors;
    private Map<Integer, Film> filmMap;
    private Map<Integer, Actor> actorMap;
    private Map<Integer, Director> directorMap;

    // Constructs a new DataImporter object and initializes the lists and maps.
    public DataImporter() {
        this.films = new ArrayList<>();
        this.actors = new ArrayList<>();
        this.directors = new ArrayList<>();
        this.filmMap = new HashMap<>();
        this.actorMap = new HashMap<>();
        this.directorMap = new HashMap<>();
    }

    // Getters
    public List<Film> getFilms() {
        return films;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    // Parses a line of text into an array of strings, handling quoted commas.
    private String[] parseLine(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '\"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                tokens.add(sb.toString().trim());
                sb.setLength(0); // reset the buffer
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString().trim()); // add the last token
        return tokens.toArray(new String[0]);
    }

    // Loads data from the specified file path and populates the lists of films, actors, and directors.
    public void loadData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { // Open file for reading
            String line;
            String currentEntity = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("New_Entity:")) {
                    currentEntity = line;
                } else {
                    switch (currentEntity) {
                        case "New_Entity: \"actor_id\",\"actor_name\"":
                            // Parse actor data and add to list and map
                            String[] actorData = parseLine(line); // Parse line into array of strings
                            int actorId = Integer.parseInt(actorData[0].replaceAll("\"", "")); // Remove quotes from ID and parse to int
                            String actorName = actorData[1].replaceAll("\"", ""); // Remove quotes from name
                            Actor actor = new Actor(actorId, actorName);
                            actors.add(actor);
                            actorMap.put(actorId, actor);
                            break;
                        case "New_Entity: \"movie_id\",\"movie_title\",\"movie_plot\",\"genre_name\",\"movie_released\",\"movie_imdbVotes\",\"movie_imdbRating\"":
                            // Parse film data and add to list and map
                            String[] filmData = parseLine(line); // Parse line into array of strings
                            int movieId = Integer.parseInt(filmData[0].replaceAll("\"", "")); // Remove quotes from ID and parse to int
                            String movieTitle = filmData[1].replaceAll("\"", ""); // Remove quotes from title
                            String moviePlot = filmData[2].replaceAll("\"", ""); // Remove quotes from plot
                            String genreName = filmData[3].replaceAll("\"", ""); // Remove quotes from genre
                            String movieReleased = filmData[4].replaceAll("\"", ""); // Remove quotes from release date
                            int movieImdbVotes = filmData[5].replaceAll("\"", "").isEmpty() ? 0 : Integer.parseInt(filmData[5].replaceAll("\"", "")); // Remove quotes from votes and parse to int
                            double movieImdbRating = filmData[6].replaceAll("\"", "").isEmpty() ? 0.0 : Double.parseDouble(filmData[6].replaceAll("\"", "")); // Remove quotes from rating and parse to double
                            Film film = new Film(movieId, movieTitle, moviePlot, genreName, movieReleased, movieImdbVotes, movieImdbRating);
                            if(filmMap.containsKey(movieId)) break;
                            films.add(film);
                            filmMap.put(movieId, film);
                            break;
                        case "New_Entity: \"director_id\",\"director_name\"":
                            // Parse director data and add to list and map
                            String[] directorData = parseLine(line); // Parse line into array of strings
                            int directorId = Integer.parseInt(directorData[0].replaceAll("\"", "")); // Remove quotes from ID and parse to int
                            String directorName = directorData[1].replaceAll("\"", ""); // Remove quotes from name
                            Director director = new Director(directorId, directorName);
                            directors.add(director);
                            directorMap.put(directorId, director);
                            break;
                        case "New_Entity: \"actor_id\",\"movie_id\"":
                            // Link actor to film
                            String[] actorFilmData = parseLine(line); // Parse line into array of strings
                            int aId = Integer.parseInt(actorFilmData[0].replaceAll("\"", "")); // Remove quotes from actor ID and parse to int
                            int mId = Integer.parseInt(actorFilmData[1].replaceAll("\"", "")); // Remove quotes from movie ID and parse to int
                            if (actorMap.containsKey(aId) && filmMap.containsKey(mId)) { // Check if actor and film exist
                                // Add actor to film and film to actor
                                Actor actorInFilm = actorMap.get(aId);
                                Film filmForActor = filmMap.get(mId);
                                actorInFilm.addFilm(filmForActor);
                                filmForActor.addActor(actorInFilm);
                            }
                            break;
                        case "New_Entity: \"director_id\",\"movie_id\"":
                            // Link director to film
                            String[] directorFilmData = parseLine(line); // Parse line into array of strings
                            int dId = Integer.parseInt(directorFilmData[0].replaceAll("\"", "")); // Remove quotes from director ID and parse to int
                            int movId = Integer.parseInt(directorFilmData[1].replaceAll("\"", "")); // Remove quotes from movie ID and parse to int
                            if (directorMap.containsKey(dId) && filmMap.containsKey(movId)) { // Check if director and film exist
                                // Add director to film and film to director
                                Director directorInFilm = directorMap.get(dId);
                                Film filmForDirector = filmMap.get(movId);
                                directorInFilm.addFilm(filmForDirector);
                                filmForDirector.addDirector(directorInFilm);
                            }
                            break;
                    }
                }
            }
        } catch (IOException e) {   // Catch any IO exceptions
            e.printStackTrace();    // Print the stack trace to error output
        }
    }
}
