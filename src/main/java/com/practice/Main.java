package com.practice;
import java.util.ArrayList;



public class Main {
    public static void main(String[] args) {
        
        ArrayList<Task> taskList = new ArrayList<>();

        taskList.add(new Task(1, "Закончить настройку GitHub"));
        taskList.add(new Task(2, "Провести тесты программы"));
        taskList.add(new Task(3, "Помыться"));

        taskList.get(0).complete();

        for(Task e : taskList){
            System.out.println(e);
        }

    }
}