import org.junit.Before;
import org.junit.Test;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class BeerControllerTest {

    private BeerController controller;
    private BeerBase base;

    @Before
    public void init(){
        base = mock(BeerBase.class);
        controller = new BeerController(base);
    }

    @Test
    public void findFound(){
        String name = "Amber";
        Beer beer = new Beer(name, 5.7f);
        doReturn(Optional.of(beer)).when(base).find(name);
        assertThat(base.find(name)).isEqualTo(Optional.of(beer));
    }

    @Test
    public void findNotFound(){
        String name = "Tatra";
        doReturn(Optional.empty()).when(base).find(name);
        assertThat(controller.find(name)).isEqualTo("not found");
    }

    @Test
    public void deleteFound(){
        String name = "Amber";
        assertThat(controller.delete(name)).isEqualTo("done");
    }

    @Test
    public void deleteNotFound(){
        String name = "Tatra";
        doThrow(new IllegalArgumentException("beer not found")).when(base).delete(name);
        assertThat(controller.delete(name)).isEqualTo("not found");
    }

    @Test
    public void addExisting(){
        String name = "Stern Extra Strong";
        Beer beer = new Beer(name, 9.2f);
        doThrow(new IllegalArgumentException("beer already exist")).when(base).add(any(Beer.class));
        assertThat(controller.add("Stern Extra Strong", 9.2f)).isEqualTo("bad request");
    }

    @Test
    public void addNotExisting(){
        String name = "Stern Strong";
        assertThat(controller.add(name, 7.2f)).isEqualTo("done");
    }


}
