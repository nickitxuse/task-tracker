package com.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskRepository {
    private String url;
    private String user;
    private String password;

    public TaskRepository(String url ,String user, String password){

        this.url = url;
        this.user = user;
        this.password = password;

    }

    public boolean completeTask(int id) throws SQLException{
        try(Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement("UPDATE tasks SET completed = TRUE WHERE id = ?;")) {
                
                statement.setInt(1,id);
                int result = statement.executeUpdate();

                if(result > 0){
                    return true;
                }
                return false;

            }
        
    }

    public void addTask(String title) throws SQLException{
        try(    Connection connection = DriverManager.getConnection(url,user,password);
                PreparedStatement statement = connection.prepareStatement("INSERT INTO tasks (title) VALUES (?);");
            ){

                statement.setString(1,title);
                statement.executeUpdate();

            }
    }

    public boolean deleteTask(int id)throws SQLException{
        try(Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM tasks WHERE id = ?;")) {
                
                statement.setInt(1,id);
                int result = statement.executeUpdate();

                if(result > 0){
                    return true;
                }
                return false;

            }
    }

    public ArrayList<Task> loadTasks() throws SQLException{

        try(    Connection connection = DriverManager.getConnection(url,user,password);
                PreparedStatement statement = connection.prepareStatement("SELECT id,title,completed FROM tasks;");
                ResultSet result = statement.executeQuery(); ) {

                    
            ArrayList<Task> tempArray = new ArrayList<>();

            while(result.next()){

                int id = result.getInt("id");
                String title = result.getString("title");
                boolean completed = result.getBoolean("completed");
                Task newTask = new Task(id, title);

                if(completed){
                    newTask.complete();
                }

                tempArray.add(newTask);}
            
            return tempArray;
            }

    }
    
}
