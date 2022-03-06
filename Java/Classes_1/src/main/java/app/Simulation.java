package app;

public class Simulation {

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        MageAcademy academy = new MageAcademy(1);
        simulation.init(academy);
        academy.printSet();
        academy.printStatistics();
    }

    public void init(MageAcademy academy){

        Mage Bartek = new Mage("Bartek", 22, 21.37);
        academy.addMages(Bartek);
        Mage Kobas = new Mage("Kobas", 21, 4.20);
        academy.addMages(Kobas);
        Mage Wrzo = new Mage("Wrzo", 50, 1.1);
        academy.addMages(Wrzo);
        Mage Zmudson = new Mage("Zmudson", 2, 0.5);
        academy.addMages(Zmudson);
        Mage Marcin = new Mage("Marcin", 20, 3.3);
        academy.addMages(Marcin);
        Mage Pitya = new Mage("Pitya",19, 6.66);
        academy.addMages(Pitya);
        Mage Marek = new Mage("Marek", 18, 6.9);
        academy.addMages(Marek);
        Mage Wisnia = new Mage("Wisnia", 23, 8.1);
        academy.addMages(Wisnia);
        Mage Weektor = new Mage("Weektor", 20, 19.69);
        academy.addMages(Weektor);
        Mage Kulpas = new Mage("Kulpas", 51, 22.22);
        academy.addMages(Kulpas);

        Kulpas.addApprentice(Wisnia);
        Wisnia.addApprentice(Wrzo);
        Kulpas.addApprentice(Bartek);
        Bartek.addApprentice(Pitya);
        Weektor.addApprentice(Marcin);
        Marcin.addApprentice(Kobas);
        Marek.addApprentice(Zmudson);
    }


}
