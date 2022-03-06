package app;

import java.util.Comparator;

public class MyComparator implements Comparator<Mage> {

    @Override
    public int compare(Mage m1, Mage m2) {
        int result = Integer.compare(m1.getLevel(), m2.getLevel());
        if (result != 0){
            return result;
        }
        result = m1.getName().compareTo(m2.getName());
        if (result != 0){
            return result;
        }
        result = Double.compare(m1.getPower(), m2.getPower());
        return result;
    }
}
