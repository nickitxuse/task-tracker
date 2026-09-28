package com.practice;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskManager {
    private TaskRepository taskRepository;

    public TaskManager(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public boolean addTask(String title)throws SQLException{
        if(title.isBlank()){
            return false;
        }
        else{
            taskRepository.addTask(title);
            return true;
        }
    }

    public ArrayList<Task> getTasks()throws SQLException{
        return taskRepository.loadTasks();
    }

    public boolean completeTask(int id)throws SQLException{
        return taskRepository.completeTask(id);
    }

    public boolean deleteTask(int id)throws SQLException{
        return taskRepository.deleteTask(id);
    }
}   
