package app;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Mage implements Comparable <Mage> {
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;


    public Mage(String name, int level, double power){
        this.name = name;
        this.level = level;
        this.power = power;
        apprentices = new TreeSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mage mage = (Mage) o;

        if (level != mage.level) return false;
        if (Double.compare(mage.power, power) != 0) return false;
        if (!name.equals(mage.name)) return false;
        return apprentices != null ? apprentices.equals(mage.apprentices) : mage.apprentices == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + level;
        temp = Double.doubleToLongBits(power);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (apprentices != null ? apprentices.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", power=" + power +
                '}';
    }

    @Override
    public int compareTo(Mage m) {
        int result = this.name.compareTo(m.name);
        if (result != 0){
            return result;
        }
        result = level - m.level;
        if(result != 0){
            return result;
        }
        result = Double.compare(this.power, m.power);
        return result;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public double getPower() {
        return power;
    }

    public Set<Mage> getApprentices() {
        return apprentices;
    }

    public void addApprentice(Mage m){
        this.apprentices.add(m);
    }

    public void print(String sign){
        System.out.print(sign);
        System.out.println(this);
        for (Mage m : apprentices){
            m.print(sign+sign.charAt(0));
        }
    }
}

