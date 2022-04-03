package app;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Tower {
    public Tower() {
    }

    @Override
    public String toString() {
        return "Tower{" +
                "name='" + name + '\'' +
                ", height=" + height +
                '}';
    }

    public Tower(String name, int height) {
        this.name = name;
        this.height = height;
    }

    @Getter
    @Setter
    @Id
    private String name;

    @Getter
    @Setter
    private int height;

    @Getter
    @Setter
    @OneToMany(mappedBy = "tower")
    private List<Mage> mages;
}
