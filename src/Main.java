import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataImporter importer = new DataImporter();
        importer.loadData("src/movieproject2024.db");

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
                    Actor actor = importer.searchActorByName(actorName);
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
                    Film film = importer.searchFilmByTitle(filmTitle);
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
                    Director director = importer.searchDirectorByName(directorName);
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
