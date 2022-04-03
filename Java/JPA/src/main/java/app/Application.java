package app;

import javax.persistence.*;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("app");
        init(emf);
        addMage(emf, args[0], Integer.parseInt(args[1]), args[2]);
        printDatabase(emf);
        printSmallestTowers(emf, 500);
        deleteTower(emf, "Orthanc");
        printSmallestTowers(emf, 500);
        printStrongestMages(emf, 500);
        deleteMage(emf, "Sauron");
        printStrongestMages(emf, 500);
        emf.close();
    }

    public static void init(EntityManagerFactory emf){
        addTower(emf, "Barad-dur", 1000);
        addTower(emf, "Orthanc", 150);
        addTower(emf, "Wieżyca", 35);

        addMage(emf, "Sauron", 999, "Barad-dur");
        addMage(emf, "Dumbledore", 501, "Orthanc");
        addMage(emf, "Saruman", 99, "Orthanc");
        addMage(emf, "Gandalf the White", 101, "Orthanc");
    }

    public static void addTower(EntityManagerFactory emf, String name, int height){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Tower(name, height));
        em.getTransaction().commit();
        em.close();
    }

    public static void addMage(EntityManagerFactory emf, String name, int level, String towerName){
        EntityManager em = emf.createEntityManager();
        String queryString = "SELECT tower FROM Tower tower WHERE tower.name LIKE :name";
        Query query = em.createQuery(queryString, Tower.class).setParameter("name", towerName);
        List <Tower> towers = query.getResultList();
        Tower tower = towers.get(0);
        em.getTransaction().begin();
        em.persist(new Mage(name, level, tower));
        em.getTransaction().commit();
        em.close();
    }

    public static void printDatabase(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();

        String queryString = "SELECT tower FROM Tower tower";
        Query query = em.createQuery(queryString, Tower.class);
        List <Tower> towers = query.getResultList();
        for (Tower tower : towers){
            System.out.println(tower);
        }

        queryString = "SELECT mage FROM Mage mage";
        query = em.createQuery(queryString, Mage.class);
        List <Mage> mages = query.getResultList();
        for (Mage mage : mages){
            System.out.println(mage);
        }

        System.out.println();
        em.close();
    }

    public static void deleteTower(EntityManagerFactory emf, String towerName){
        EntityManager em = emf.createEntityManager();

        String queryString = "SELECT tower FROM Tower tower WHERE tower.name LIKE :towerName";
        Query query = em.createQuery(queryString).setParameter("towerName", towerName);
        List <Tower> towers = query.getResultList();
        Tower tower = towers.get(0);
        List<Mage> mages = tower.getMages();
        for (Mage mage : mages){
            mage.setTower(null);
        }
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(em.merge(tower));
        transaction.commit();

        System.out.println();
        em.close();
    }

    public static void deleteMage(EntityManagerFactory emf, String mageName){
        EntityManager em = emf.createEntityManager();

        String queryString = "SELECT mage FROM Mage mage WHERE mage.name LIKE :mageName";
        Query query = em.createQuery(queryString).setParameter("mageName", mageName);
        List <Mage> mages = query.getResultList();
        Mage mage = mages.get(0);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(em.merge(mage));
        transaction.commit();

        System.out.println();
        em.close();
    }

    public static void printStrongestMages(EntityManagerFactory emf, int minLevel){
        EntityManager em = emf.createEntityManager();

        String queryString = "SELECT mage FROM Mage mage WHERE mage.level > :minLevel";
        Query query = em.createQuery(queryString, Mage.class).setParameter("minLevel", minLevel);
        List <Mage> mages = query.getResultList();
        System.out.println("Strongest mages:");
        for (Mage mage : mages){
            System.out.println(mage);
        }

        System.out.println();
        em.close();
    }

    public static void printSmallestTowers(EntityManagerFactory emf, int maxHeight){
        EntityManager em = emf.createEntityManager();

        String queryString = "SELECT tower FROM Tower tower WHERE tower.height < :maxHeight";
        Query query = em.createQuery(queryString, Tower.class).setParameter("maxHeight", maxHeight);
        List <Tower> towers = query.getResultList();
        System.out.println("Smallest towers:");
        for (Tower tower : towers){
            System.out.println(tower);
        }

        System.out.println();
        em.close();
    }
}
