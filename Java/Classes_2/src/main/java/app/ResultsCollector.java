package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultsCollector {
    List<Boolean> resultsList = new ArrayList<>();
    List<Boolean> synchronizedResultsList = Collections.synchronizedList(resultsList);

    public synchronized void collect(boolean result){
        resultsList.add(result);
    }

    public synchronized void print(){
        for (boolean result : resultsList){
            String a = String.valueOf(result);
            System.out.println(a);
        }
    }
}
