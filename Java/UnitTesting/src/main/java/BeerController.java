import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class BeerController {
    private BeerBase base;

    public String find(String name){
        Optional<Beer> beers = base.find(name);
        if (beers.isEmpty()){
            return "not found";
        }
        else{
            return beers.get().toString();
        }
    }

    public String delete(String name){
        try {
            base.delete(name);
        }
        catch (IllegalArgumentException ex){
            return "not found";
        }
        return "done";
    }

    public String add(String name, float percentage){
        try {
            base.add(new Beer(name, percentage));
        }
        catch (IllegalArgumentException ex){
            return "bad request";
        }
        return "done";
    }

}
