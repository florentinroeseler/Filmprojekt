package main;

import main.java.com.model.Actor;
import main.java.com.model.Director;
import main.java.com.model.Film;
import main.java.com.service.DataImporter;
import main.java.com.service.ActorSearchService;
import main.java.com.service.FilmSearchService;
import main.java.com.service.DirectorSearchService;
import main.java.com.service.NetworkService;

import java.util.List;

/**
 * Main class for Filmprojekt.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize the DataImporter and load data from the specified file
        DataImporter importer = new DataImporter();
        importer.loadData("src/main/resources/movieproject2024.db");

        // Initialize the search services with the imported data
        ActorSearchService actorSearchService = new ActorSearchService(importer.getActors());
        FilmSearchService filmSearchService = new FilmSearchService(importer.getFilms());
        DirectorSearchService directorSearchService = new DirectorSearchService(importer.getDirectors());
        NetworkService networkService = new NetworkService(filmSearchService, actorSearchService);

        // Handle command-line arguments if provided
        if (args.length > 0) {
            handleArgs(importer, actorSearchService, filmSearchService, directorSearchService, networkService, args);
        }
        else {
            System.out.println("No arguments provided. Please use the following format:");
            System.out.println("--filmnetzwerk=<filmId> --schauspielernetzwerk=<actorId> --schauspielersuche=<name> --filmsuche=<title> --regisseursuche=<name>");
        }
    }

    // Handles the command-line arguments to perform the appropriate actions.
    private static void handleArgs(DataImporter importer, ActorSearchService actorSearchService, FilmSearchService filmSearchService, DirectorSearchService directorSearchService, NetworkService networkService, String[] args) {
        for (String arg : args) {
            if (arg.startsWith("--filmnetzwerk=")) {
                String filmIdStr = arg.split("=")[1];
                try {
                    int filmId = Integer.parseInt(filmIdStr);
                    networkService.printFilmNetwork(filmId);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid film ID format.");
                }
            } else if (arg.startsWith("--schauspielernetzwerk=")) {
                String actorIdStr = arg.split("=")[1];
                try {
                    int actorId = Integer.parseInt(actorIdStr);
                    networkService.printActorNetwork(actorId);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid actor ID format.");
                }
            } else if (arg.startsWith("--schauspielersuche=")) {
                String actorName = arg.split("=")[1];
                searchAndPrintActors(actorSearchService, actorName);
            } else if (arg.startsWith("--filmsuche=")) {
                String filmTitle = arg.split("=")[1];
                searchAndPrintFilms(filmSearchService, filmTitle);
            } else if (arg.startsWith("--regisseursuche=")) {
                String directorName = arg.split("=")[1];
                searchAndPrintDirectors(directorSearchService, directorName);
            }
            else {
                System.out.println("Invalid argument: " + arg);
            }
        }
    }

    // Searches for actors by name and prints the results.
    private static void searchAndPrintActors(ActorSearchService actorSearchService, String name) {
        List<Actor> actors = actorSearchService.searchActorByName(name);
        if (!actors.isEmpty()) {
            System.out.println("Actors found:");
            for (Actor actor : actors) {
                System.out.println(" - " + actor.getName());
                // Print actor's films
                for (Film film : actor.getFilms()) {
                    System.out.println("   - " + film.getTitle());
                }
            }
        } else {
            System.out.println("Actor not found.");
        }
    }

    // Searches for films by title and prints the results.
    private static void searchAndPrintFilms(FilmSearchService filmSearchService, String title) {
        List<Film> films = filmSearchService.searchFilmByTitle(title);
        if (!films.isEmpty()) {
            System.out.println("Films found:");
            for (Film film : films) {
                System.out.println(" - " + film.getTitle());
                System.out.println("   Plot: " + film.getDescription());
                System.out.println("   Genre: " + film.getGenre());
                System.out.println("   Release Date: " + film.getReleaseDate());
                System.out.println("   IMDB Votes: " + film.getImdbVotes());
                System.out.println("   IMDB Rating: " + film.getImdbRating());
            }
        } else {
            System.out.println("Film not found.");
        }
    }

    // Searches for directors by name and prints the results.
    private static void searchAndPrintDirectors(DirectorSearchService directorSearchService, String name) {
        List<Director> directors = directorSearchService.searchDirectorByName(name);
        if (!directors.isEmpty()) {
            System.out.println("Directors found:");
            for (Director director : directors) {
                System.out.println(" - " + director.getName());
                // Print director's films
                for (Film film : director.getFilms()) {
                    System.out.println("   - " + film.getTitle());
                }
            }
        } else {
            System.out.println("Director not found.");
        }
    }
}
