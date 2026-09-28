package com.practice;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseConnectionCheck {



    public static void main(String[] args){
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if(url == null || url.isBlank() || user == null || user.isBlank() || password == null){
            System.out.println("Сбой в полученных данных для ДБ.");
            return;
        }

        TaskRepository taskRepository = new TaskRepository(url, user, password);

        try{
            taskRepository.addTask("Rukoblud sanina");
            ArrayList<Task> taskList = taskRepository.loadTasks();
            for(Task task : taskList){
                System.out.println(task);
            }
        }catch(SQLException e){
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

        


}
