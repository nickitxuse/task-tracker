package com.practice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DatabaseConnectionCheck {

    public static void main(String[] args){
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if(url == null || url.isBlank() || user == null || user.isBlank() || password == null){
            System.out.println("Данные подключения к базе данных некорректны. Строка пуста.");
        }
        else{
            System.out.println("Данные для подключения к ДБ получены.");

            try(Connection connection = DriverManager.getConnection(url,user,password);
                PreparedStatement statement = connection.prepareStatement("SELECT id,title,completed FROM tasks;");
                ResultSet result = statement.executeQuery();) {

                System.out.println("Соединение установлено.");

                while(result.next()){
                    System.out.println(result.getInt("id"));
                    System.out.println(result.getString("title"));
                    System.out.println(result.getBoolean("completed"));
                    
                }
            }
            catch(SQLException e){
                System.out.println("Ошибка при работе с базой данных. " + e.getMessage());
            }
        }
    }

}
