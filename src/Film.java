import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

public class Film {
    private int id;
    private String title;
    private String description;
    private String genre;
    private String releaseDate;
    private int imdbVotes;
    private double imdbRating;
    private Set<Actor> actors = new HashSet<>();
    private List<Director> directors;

    public Film(int id, String title, String description, String genre, String releaseDate, int imdbVotes, double imdbRating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.imdbVotes = imdbVotes;
        this.imdbRating = imdbRating;
        this.actors = new HashSet<>();
        this.directors = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getImdbVotes() {
        return imdbVotes;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public Set<Actor> getActors() {
        return this.actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void addActor(Actor actor) {
        this.actors.add(actor);
    }

    public void addDirector(Director director) {
        this.directors.add(director);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
