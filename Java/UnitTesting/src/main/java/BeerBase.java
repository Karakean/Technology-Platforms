import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class BeerBase {

    private final Collection<Beer> beers = new HashSet<>();

    public Optional<Beer> find(String name){
        return beers.stream().filter(beer -> beer.getName().equals(name)).findFirst();
    }

    public void delete(String name) {
        Beer beer = beers.stream()
                .filter(beer1 -> beer1.getName().equals(name)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("beer not found"));
        beers.remove(beer);
    }

    public void add(Beer beer){
        beers.forEach(beer1 -> {
            if (beer1.getName().equals(beer.getName()))
                throw new IllegalArgumentException("beer already exists");
        });
        beers.add(beer);
    }
}
