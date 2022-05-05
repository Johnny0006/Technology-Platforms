package pg.edu.pl;

import java.util.Random;

public class Worker implements Runnable{

    private TasksBoard tasksBoard;
    private Results results;
    private int time;

    public Worker(TasksBoard tasksBoard, Results results) {
        this.tasksBoard = tasksBoard;
        this.results = results;
        this.time=0;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Boolean result=true;
                Integer task = tasksBoard.take();
                if(task<2) {
                    result=false;
                } else{
                    for(int i=2;i*i<=task;i++){
                        if(task%i==0){
                            result=false;
                            break;
                        }
                    }
                }
                results.put(task, result);
                Random random = new Random();
                this.time+=random.nextInt(5001);
                Thread.sleep(this.time);


            } catch (InterruptedException ex) {
                System.out.print("Thread "+Thread.currentThread().getId() + " slept for " + this.time + " miliseconds.\n");
                break;
            }
        }
    }

    public TasksBoard getTasksBoard() {
        return tasksBoard;
    }

    public Results getResults() {
        return results;
    }

    public void setTasksBoard(TasksBoard tasksBoard) {
        this.tasksBoard = tasksBoard;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
