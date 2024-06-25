import java.util.List;
import java.util.stream.Collectors;

public class FilmSearch {
    private List<Film> films;

    public FilmSearch(List<Film> films) {
        this.films = films;
    }

    public List<Film> searchFilmByTitle(String title) {
        return films.stream()
                .filter(film -> film.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
}
