package pg.edu.pl;

import java.lang.*;
import java.util.*;


public class Main{

    public static void main(String[] args) {

        TasksBoard tasksBoard = new TasksBoard();
        Results results = new Results();

        int n = Integer.parseInt(args[0]);

        Thread[] thread = new Thread[n];

        for(int i=0; i<n; i++){
            thread[i]= new Thread(new Worker(tasksBoard, results));
            thread[i].start();
        }

        Scanner scan = new Scanner(System.in);
        int task;

        while(true) {
            task = scan.nextInt();
            if(task!=0) {
                tasksBoard.put(task);
            }else
                break;
        }

        for(int i=0; i<n; i++){
            thread[i].interrupt();
        }

        System.out.print(results.getResults()+"\n");

    }

}

