package app;

import java.util.Scanner;


public class Main {
    public static void main (String args[]){
        Scanner scanner = new Scanner(System.in);
        int threadsNumber = Integer.parseInt(args[0]);
        Application application = new Application();
        application.createThreads(threadsNumber);
        application.generateQuests();
        boolean exit = false;

        menu();
        while(!exit){
            switch(scanner.next()){
                case "1":
                    System.out.print("Type a number to check: ");
                    int number = scanner.nextInt();
                    application.addQuest(number);
                    System.out.println("A quest was added successfully.");
                    menu();
                    break;
                case "2":
                    exit = true;
                    break;
                default:
                    System.out.println("Choose a valid option.");
            }
        }
        scanner.close();
        application.stopThreads();
        //application.printResults();
    }

    public static void menu(){
        System.out.println("Choose your option:");
        System.out.println("1. Add quest.");
        System.out.println("2. Quit");
    }
}

