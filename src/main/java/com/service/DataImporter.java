package main.java.com.service;

import main.java.com.model.Actor;
import main.java.com.model.Director;
import main.java.com.model.Film;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataImporter {
    private List<Film> films;
    private List<Actor> actors;
    private List<Director> directors;
    private Map<Integer, Film> filmMap;
    private Map<Integer, Actor> actorMap;
    private Map<Integer, Director> directorMap;

    public DataImporter() {
        this.films = new ArrayList<>();
        this.actors = new ArrayList<>();
        this.directors = new ArrayList<>();
        this.filmMap = new HashMap<>();
        this.actorMap = new HashMap<>();
        this.directorMap = new HashMap<>();
    }

    public List<Film> getFilms() {
        return films;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

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

    public void loadData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String currentEntity = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("New_Entity:")) {
                    currentEntity = line;
                } else {
                    switch (currentEntity) {
                        case "New_Entity: \"actor_id\",\"actor_name\"":
                            String[] actorData = parseLine(line);
                            int actorId = Integer.parseInt(actorData[0].replaceAll("\"", ""));
                            String actorName = actorData[1].replaceAll("\"", "");
                            Actor actor = new Actor(actorId, actorName);
                            actors.add(actor);
                            actorMap.put(actorId, actor);
                            break;
                        case "New_Entity: \"movie_id\",\"movie_title\",\"movie_plot\",\"genre_name\",\"movie_released\",\"movie_imdbVotes\",\"movie_imdbRating\"":
                            String[] filmData = parseLine(line);
                            int movieId = Integer.parseInt(filmData[0].replaceAll("\"", ""));
                            String movieTitle = filmData[1].replaceAll("\"", "");
                            String moviePlot = filmData[2].replaceAll("\"", "");
                            String genreName = filmData[3].replaceAll("\"", "");
                            String movieReleased = filmData[4].replaceAll("\"", "");
                            int movieImdbVotes = filmData[5].replaceAll("\"", "").isEmpty() ? 0 : Integer.parseInt(filmData[5].replaceAll("\"", ""));
                            double movieImdbRating = filmData[6].replaceAll("\"", "").isEmpty() ? 0.0 : Double.parseDouble(filmData[6].replaceAll("\"", ""));
                            Film film = new Film(movieId, movieTitle, moviePlot, genreName, movieReleased, movieImdbVotes, movieImdbRating);
                            if(filmMap.containsKey(movieId)) break;
                            films.add(film);
                            filmMap.put(movieId, film);
                            break;
                        case "New_Entity: \"director_id\",\"director_name\"":
                            String[] directorData = parseLine(line);
                            int directorId = Integer.parseInt(directorData[0].replaceAll("\"", ""));
                            String directorName = directorData[1].replaceAll("\"", "");
                            Director director = new Director(directorId, directorName);
                            directors.add(director);
                            directorMap.put(directorId, director);
                            break;
                        case "New_Entity: \"actor_id\",\"movie_id\"":
                            String[] actorFilmData = parseLine(line);
                            int aId = Integer.parseInt(actorFilmData[0].replaceAll("\"", ""));
                            int mId = Integer.parseInt(actorFilmData[1].replaceAll("\"", ""));
                            if (actorMap.containsKey(aId) && filmMap.containsKey(mId)) {
                                Actor actorInFilm = actorMap.get(aId);
                                Film filmForActor = filmMap.get(mId);
                                actorInFilm.addFilm(filmForActor);
                                filmForActor.addActor(actorInFilm);
                            }
                            break;
                        case "New_Entity: \"director_id\",\"movie_id\"":
                            String[] directorFilmData = parseLine(line);
                            int dId = Integer.parseInt(directorFilmData[0].replaceAll("\"", ""));
                            int movId = Integer.parseInt(directorFilmData[1].replaceAll("\"", ""));
                            if (directorMap.containsKey(dId) && filmMap.containsKey(movId)) {
                                Director directorInFilm = directorMap.get(dId);
                                Film filmForDirector = filmMap.get(movId);
                                directorInFilm.addFilm(filmForDirector);
                                filmForDirector.addDirector(directorInFilm);
                            }
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public Actor searchActorByName(String name) {
//        for (Actor actor : actors) {
//            if (actor.getName().equalsIgnoreCase(name)) {
//                return actor;
//            }
//        }
//        return null;
//    }
//
//    public Film searchFilmByTitle(String title) {
//        for (Film film : films) {
//            if (film.getTitle().equalsIgnoreCase(title)) {
//                return film;
//            }
//        }
//        return null;
//    }
//
//    public Director searchDirectorByName(String name) {
//        for (Director director : directors) {
//            if (director.getName().equalsIgnoreCase(name)) {
//                return director;
//            }
//        }
//        return null;
//    }
//
//    public void printFilmNetwork(String title) {
//        Film film = searchFilmByTitle(title);
//        if (film != null) {
//            Set<Actor> actorsInFilm = new HashSet<>(film.getActors());
//            Set<Film> relatedFilms = new HashSet<>();
//
//            // Find films related to these actors
//            for (Actor actor : actorsInFilm) {
//                relatedFilms.addAll(actor.getFilms());
//            }
//
//            relatedFilms.remove(film); // Remove the original film from the related films
//
//            System.out.println("Actors in " + film.getTitle() + ":");
//            for (Actor actor : actorsInFilm) {
//                System.out.println(" - " + actor.getName());
//            }
//
//            System.out.println("Related Films:");
//            for (Film relatedFilm : relatedFilms) {
//                System.out.println(" - " + relatedFilm.getTitle());
//            }
//
//            // Print statements for debugging
//            System.out.println("main.java.com.model.Film is: " + (film == null ? "null" : "not null"));
//            System.out.println("Number of actors in film: " + actorsInFilm.size());
//            System.out.println("Number of related films: " + relatedFilms.size());
//        } else {
//            System.out.println("main.java.com.model.Film not found.");
//        }
//    }
//
//    public void printActorNetwork(String actorName) {
//        Actor actor = searchActorByName(actorName);
//        if (actor != null) {
//            Set<Film> filmsActedIn = new HashSet<>(actor.getFilms());
//            Set<Actor> relatedActors = new HashSet<>();
//
//            // Find actors related to these films
//            for (Film film : filmsActedIn) {
//                relatedActors.addAll(film.getActors());
//            }
//
//            System.out.println("Films acted in by " + actor.getName() + ":");
//            for (Film film : filmsActedIn) {
//                System.out.println(" - " + film.getTitle());
//            }
//
//            System.out.println("Related Actors:");
//            for (Actor relatedActor : relatedActors) {
//                System.out.println(" - " + relatedActor.getName());
//            }
//        } else {
//            System.out.println("main.java.com.model.Actor not found.");
//        }
//    }
}
