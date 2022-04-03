package app;

import java.util.*;

public class MageAcademy {
    private int sort; //0 - bez sortowania, 1 - sortowanie zgodnie z naturalnym porzadkiem
    //2 - sortowanie zgodnie z alternatywnym porzadkiem
    private Set<Mage> mages;
    private Map<Mage, Integer> statistics;

    public MageAcademy(int _sort) {
        this.sort = _sort;
        if (this.sort == 0) {
            this.mages = new HashSet<>();
        } else if (this.sort == 1) {
            this.mages = new TreeSet<>();
        } else {
            this.mages = new TreeSet<>(new MyComparator());
        }
    }

    public void addMages(Mage m) {
        mages.add(m);
    }

    public void printSet() {
        Set<Mage> students = new TreeSet<>();
        for (Mage m : mages) {
            dfs(students, m);
        }
        for (Mage m : mages) {
            if (!students.contains(m)) {
                m.print("-");
            }
        }
    }

    private void dfs(Set<Mage> students, Mage m) {
        for (Mage student : m.getApprentices()) {
            students.add(student);
            dfs(students, student);
        }
    }

    public void printStatistics() {
        statistics = this.sort == 0 ? new HashMap<>() : new TreeMap<>();

        for (Mage m : mages) {
            Set<Mage> students = new HashSet<>();
            dfs(students, m);
            int size = students.size();
            statistics.put(m, size);
        }

        for (Mage m : statistics.keySet()){
            System.out.println(m + " : " + statistics.get(m));
        }
    }
}
