package app;

public class Worker implements Runnable{
    private QuestBoard questBoard;
    private ResultsCollector resultsCollector;

    public Worker(QuestBoard questBoard, ResultsCollector resultsCollector) {
        this.questBoard = questBoard;
        this.resultsCollector = resultsCollector;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try{
                Quest quest = questBoard.take();
                //Thread.sleep(1000); //symulacja obliczen
                boolean result = quest.isPrime();
                resultsCollector.collect(result);
            } catch(InterruptedException ex){
                break;
            }
        }
    }
}
