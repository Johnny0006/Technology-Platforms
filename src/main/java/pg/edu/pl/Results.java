package pg.edu.pl;

import java.util.Map;
import java.util.TreeMap;

public class Results {

    private Map<Integer,Boolean> results= new TreeMap<>();


    public synchronized void put(Integer task, Boolean result) {
        getResults().put(task, result);
    }


    public Map<Integer, Boolean> getResults() {
        return results;
    }

    public void setResults(Map<Integer, Boolean> results) {
        this.results = results;
    }
}
