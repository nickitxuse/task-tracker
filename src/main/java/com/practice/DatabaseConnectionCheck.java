package com.practice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseConnectionCheck {

    public static ArrayList<Task> loadTasks(String url , String user , String password) throws SQLException{

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
    

    public static void main(String[] args){
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if(url == null || url.isBlank() || user == null || user.isBlank() || password == null){
            System.out.println("Сбой в полученных данных для ДБ.");
            return;
        }

        try{

            ArrayList<Task> taskList = loadTasks(url, user, password);
            for(Task task : taskList){
                System.out.println(task);
            }
        }catch(SQLException e){
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

        


}
