package main;

import main.java.com.model.Actor;
import main.java.com.model.Director;
import main.java.com.model.Film;
import main.java.com.service.DataImporter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataImporter importer = new DataImporter();
        importer.loadData("C:\\Users\\flore\\IdeaProjects\\Filmprojekt\\src\\main\\resources\\movieproject2024.db");

        if (args.length > 0) {
            // Handle command-line arguments
            handleArgs(importer, args);
        } else {
            // Show menu for interactive use
            showMenu(importer);
        }
    }

    private static void handleArgs(DataImporter importer, String[] args) {
        for (String arg : args) {
            if (arg.startsWith("--filmnetzwerk=")) {
                String filmIdStr = arg.split("=")[1];
                try {
                    int filmId = Integer.parseInt(filmIdStr);
                    Film film = importer.searchFilmByTitle(getFilmTitleById(filmId, importer));
                    if (film != null) {
                        importer.printFilmNetwork(film.getTitle());
                    } else {
                        System.out.println("main.java.com.model.Film not found.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid film ID format.");
                }
            } else if (arg.startsWith("--schauspielernetzwerk=")) {
                String actorIdStr = arg.split("=")[1];
                try {
                    int actorId = Integer.parseInt(actorIdStr);
                    Actor actor = importer.searchActorByName(getActorNameById(actorId, importer));
                    if (actor != null) {
                        importer.printActorNetwork(actor.getName());
                    } else {
                        System.out.println("main.java.com.model.Actor not found.");
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

    private static void showMenu(DataImporter importer) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Search for an actor");
            System.out.println("2. Search for a film");
            System.out.println("3. Search for a director");
            System.out.println("4. main.java.com.model.Film network");
            System.out.println("5. main.java.com.model.Actor network");
            System.out.println("6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the actor's name: ");
                    String actorName = scanner.nextLine();
                    Actor actor = importer.searchActorByName(actorName);
                    if (actor != null) {
                        System.out.println("main.java.com.model.Actor found: " + actor.getName());
                        // Print actor's films
                        for (Film film : actor.getFilms()) {
                            System.out.println(" - " + film.getTitle());
                        }
                    } else {
                        System.out.println("main.java.com.model.Actor not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter the film's title: ");
                    String filmTitle = scanner.nextLine();
                    Film film = importer.searchFilmByTitle(filmTitle);
                    if (film != null) {
                        System.out.println("main.java.com.model.Film found: " + film.getTitle());
                        System.out.println("Plot: " + film.getDescription());
                        System.out.println("Genre: " + film.getGenre());
                        System.out.println("Release Date: " + film.getReleaseDate());
                        System.out.println("IMDB Votes: " + film.getImdbVotes());
                        System.out.println("IMDB Rating: " + film.getImdbRating());
                    } else {
                        System.out.println("main.java.com.model.Film not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the director's name: ");
                    String directorName = scanner.nextLine();
                    Director director = importer.searchDirectorByName(directorName);
                    if (director != null) {
                        System.out.println("main.java.com.model.Director found: " + director.getName());
                        // Print director's films
                        for (Film dirFilm : director.getFilms()) {
                            System.out.println(" - " + dirFilm.getTitle());
                        }
                    } else {
                        System.out.println("main.java.com.model.Director not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter the film's title: ");
                    String filmNetworkTitle = scanner.nextLine();
                    importer.printFilmNetwork(filmNetworkTitle);
                    break;
                case 5:
                    System.out.print("Enter the actor's name: ");
                    String actorNetworkName = scanner.nextLine();
                    importer.printActorNetwork(actorNetworkName);
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
