package app;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mage {
    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", tower=" + tower +
                '}';
    }

    public Mage() {
    }

    public Mage(String name, int level, Tower tower) {
        this.name = name;
        this.level = level;
        this.tower = tower;
    }

    @Getter
    @Setter
    @Id
    private String name;

    @Getter
    @Setter
    private int level;

    @Getter
    @Setter
    @ManyToOne
    private Tower tower;
}
