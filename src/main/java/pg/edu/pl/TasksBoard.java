package pg.edu.pl;

import java.util.ArrayList;
import java.util.List;

public class TasksBoard {

    private List<Integer> tasks= new ArrayList<>();

    public synchronized Integer take() throws InterruptedException {
        while (tasks.isEmpty()) {
            wait();
        }
        return tasks.remove(0);
    }

    public synchronized void put(Integer task) {
        getTasks().add(task);
        notifyAll();
    }

    public void setTasks(List<Integer> tasks) {
        this.tasks = tasks;
    }

    public List<Integer> getTasks() {
        return tasks;
    }
}
