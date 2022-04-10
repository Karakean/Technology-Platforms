import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private float percentage;
}
