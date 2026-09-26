package com.practice;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        
        ArrayList<Task> taskList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int idCounter = 1;
        boolean running = true;
        
        while(running == true){
            System.out.println("Меню: \n 1 - Список задач.\n 2 - Добавить задачу. \n 0 - Закрыть. \n " );

            String command = scanner.nextLine();

            switch(command){
                case("2"):
                    System.out.println("Название задачи: ");
                    String title = scanner.nextLine();
                    System.out.println("\n");
                    taskList.add(new Task(idCounter, title));
                    System.out.println("Задача добавлена! \n");
                    idCounter++;
                    break;

                case("1"):
                    System.out.println("Список задач: ");

                    if(taskList.isEmpty()){
                        System.out.println("Список пуст.");
                    }
                    else{
                        for(Task task : taskList)
                        {System.out.println(task);}
                        }

                    System.out.println("\n");
                    break;

                case("0"):
                    running = false;
                    break;
                    
                default:
                    System.out.println("Неверная комманда.\n");
            }       

        }        

    }
}