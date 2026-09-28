package com.practice;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList = new ArrayList<>();
    private int idCounter = 1;

    public boolean addTask(String title){
        if(title.isBlank()){
            return false;
        }
        else{
            taskList.add(new Task(idCounter, title));
            idCounter++;
            return true;
        }
    }

    public ArrayList<Task> getTasks(){
        ArrayList<Task> copyList = new ArrayList<>(taskList);
        return copyList;
    }

    public boolean completeTask(int id){
        for(Task task : taskList){
            if(task.getId() == id){
                task.complete();
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int id){
        for(int i = 0; i < taskList.size() ; i++){
            if(taskList.get(i).getId() == id){
                taskList.remove(i);
                return true;
            }
        }
        return false;
    }

}

