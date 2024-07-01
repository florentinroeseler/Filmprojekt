package main;

import main.java.com.model.Actor;
import main.java.com.model.Director;
import main.java.com.model.Film;
import main.java.com.service.DataImporter;
import main.java.com.service.ActorSearchService;
import main.java.com.service.FilmSearchService;
import main.java.com.service.DirectorSearchService;
import main.java.com.service.NetworkService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataImporter importer = new DataImporter();
        importer.loadData("src/main/resources/movieproject2024.db");

        ActorSearchService actorSearchService = new ActorSearchService(importer.getActors());
        FilmSearchService filmSearchService = new FilmSearchService(importer.getFilms());
        DirectorSearchService directorSearchService = new DirectorSearchService(importer.getDirectors());
        NetworkService networkService = new NetworkService(filmSearchService, actorSearchService);

        if (args.length > 0) {
            handleArgs(importer, actorSearchService, filmSearchService, directorSearchService, networkService, args);
        } else {
            showMenu(importer, actorSearchService, filmSearchService, directorSearchService, networkService);
        }
    }

    private static void handleArgs(DataImporter importer, ActorSearchService actorSearchService, FilmSearchService filmSearchService, DirectorSearchService directorSearchService, NetworkService networkService, String[] args) {
        for (String arg : args) {
            if (arg.startsWith("--filmnetzwerk=")) {
                String filmIdStr = arg.split("=")[1];
                try {
                    int filmId = Integer.parseInt(filmIdStr);
                    Film film = filmSearchService.searchFilmByTitle(getFilmTitleById(filmId, importer));
                    if (film != null) {
                        networkService.printFilmNetwork(film.getTitle());
                    } else {
                        System.out.println("Film not found.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid film ID format.");
                }
            } else if (arg.startsWith("--schauspielernetzwerk=")) {
                String actorIdStr = arg.split("=")[1];
                try {
                    int actorId = Integer.parseInt(actorIdStr);
                    Actor actor = actorSearchService.searchActorByName(getActorNameById(actorId, importer));
                    if (actor != null) {
                        networkService.printActorNetwork(actor.getName());
                    } else {
                        System.out.println("Actor not found.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid actor ID format.");
                }
            }
        }
    }

    private static String getFilmTitleById(int filmId, DataImporter importer) {
        for (Film film : importer.getFilms()) {  // Use the getter method
            if (film.getId() == filmId) {
                return film.getTitle();
            }
        }
        return null;
    }

    private static String getActorNameById(int actorId, DataImporter importer) {
        for (Actor actor : importer.getActors()) {  // Use the getter method
            if (actor.getId() == actorId) {
                return actor.getName();
            }
        }
        return null;
    }

    private static void showMenu(DataImporter importer, ActorSearchService actorSearchService, FilmSearchService filmSearchService, DirectorSearchService directorSearchService, NetworkService networkService) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Search for an actor");
            System.out.println("2. Search for a film");
            System.out.println("3. Search for a director");
            System.out.println("4. Film network");
            System.out.println("5. Actor network");
            System.out.println("6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the actor's name: ");
                    String actorName = scanner.nextLine();
                    Actor actor = actorSearchService.searchActorByName(actorName);
                    if (actor != null) {
                        System.out.println("Actor found: " + actor.getName());
                        // Print actor's films
                        for (Film film : actor.getFilms()) {
                            System.out.println(" - " + film.getTitle());
                        }
                    } else {
                        System.out.println("Actor not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter the film's title: ");
                    String filmTitle = scanner.nextLine();
                    Film film = filmSearchService.searchFilmByTitle(filmTitle);
                    if (film != null) {
                        System.out.println("Film found: " + film.getTitle());
                        System.out.println("Plot: " + film.getDescription());
                        System.out.println("Genre: " + film.getGenre());
                        System.out.println("Release Date: " + film.getReleaseDate());
                        System.out.println("IMDB Votes: " + film.getImdbVotes());
                        System.out.println("IMDB Rating: " + film.getImdbRating());
                    } else {
                        System.out.println("Film not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the director's name: ");
                    String directorName = scanner.nextLine();
                    Director director = directorSearchService.searchDirectorByName(directorName);
                    if (director != null) {
                        System.out.println("Director found: " + director.getName());
                        // Print director's films
                        for (Film dirFilm : director.getFilms()) {
                            System.out.println(" - " + dirFilm.getTitle());
                        }
                    } else {
                        System.out.println("Director not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter the film's title: ");
                    String filmNetworkTitle = scanner.nextLine();
                    networkService.printFilmNetwork(filmNetworkTitle);
                    break;
                case 5:
                    System.out.print("Enter the actor's name: ");
                    String actorNetworkName = scanner.nextLine();
                    networkService.printActorNetwork(actorNetworkName);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
