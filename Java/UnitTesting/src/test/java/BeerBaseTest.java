import org.junit.Before;
import org.junit.Test;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class BeerBaseTest {

    private BeerBase base;

    @Before
    public void init(){
        base = new BeerBase();
        Beer beer = new Beer("Amber", 5.7f);
        base.add(beer);
    }

    @Test
    public void find(){
        Optional<Beer> empty = Optional.empty();
        String name = "Amber";
        assertThat(base.find(name)).isNotEqualTo(empty);
    }

    @Test
    public void findNotFound(){
        Optional<Beer> empty = Optional.empty();
        String name = "Tatra";
        assertThat(base.find(name)).isEqualTo(empty);
    }

    @Test
    public void deleteNotExisting(){
        String name = "Tatra";
        assertThatThrownBy(() -> base.delete(name)).hasMessage("beer not found");
    }

    @Test
    public void addExisting(){
        String name = "Amber";
        Beer beer = new Beer(name, 5.7f);
        assertThatThrownBy(() -> base.add(beer)).hasMessage("beer already exists");
    }
}
