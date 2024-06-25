import java.util.List;
import java.util.stream.Collectors;

public class ActorSearch {
    private List<Actor> actors;

    public ActorSearch(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Actor> searchActorByName(String name) {
        return actors.stream()
                .filter(actor -> actor.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
