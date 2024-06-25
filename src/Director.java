import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Director {
    private int id;
    private String name;
    private List<Film> films;

    public Director(int id, String name) {
        this.id = id;
        this.name = name;
        this.films = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void addFilm(Film film) {
        this.films.add(film);
        if (!film.getDirectors().contains(this)) {
            film.addDirector(this);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return id == director.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
