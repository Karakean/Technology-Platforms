package app;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private QuestBoard questBoard = new QuestBoard();
    private ResultsCollector resultsCollector = new ResultsCollector();
    private List<Thread> threadList = new ArrayList<>();

    public void createThreads(int threadsNumber) {
        for(int i = 0; i < threadsNumber; i++){
            Thread thread = new Thread(new Worker(questBoard, resultsCollector));
            threadList.add(thread);
            thread.start();
        }
    }

    public void addQuest(int number){
        questBoard.put(new Quest(number));
    }

    public void generateQuests() {
        addQuest(1);
        addQuest(2);
        addQuest(420);
        addQuest(2137);
        addQuest(8191);
        addQuest(10000);
        addQuest(131071);
    }

    public void stopThreads() {
        for (Thread thread : threadList){
            thread.interrupt();
        }
    }

    public void printResults(){
        resultsCollector.print();
    }
}
