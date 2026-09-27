package com.practice;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {

    public static void showTasks(ArrayList<Task> taskList){
        System.out.println("Список задач: ");
        if(taskList.isEmpty()){
            System.out.println("Список пуст.");
        }
        else{
            for(Task task : taskList)
            {System.out.println(task);}
            }     

    }

    public static void completeTask(TaskManager taskManager , Scanner scanner){

        showTasks(taskManager.getTasks());
        System.out.println("Введите id задачи: ");
        String completeId = scanner.nextLine();

        try{
            int taskID = Integer.parseInt(completeId);
            System.out.println("\n");

            if(taskManager.completeTask(taskID)){
                System.out.println("Задача помечена как выполненная.\n");
            }
                
            else{
                System.out.println("Задача не найдена.\n");
            }
        }
            
        catch(NumberFormatException e){
            System.out.println("Ошибка! Введите целое число.\n");
        }
    }

    public static void deleteTask(TaskManager taskManager , Scanner scanner){
        showTasks(taskManager.getTasks());
        System.out.println("Введите id задачи: ");
        String removeId = scanner.nextLine();

        try{
            int taskId = Integer.parseInt(removeId);
            System.out.println("\n");

            if(taskManager.deleteTask(taskId)){
                System.out.println("Задача удалена.\n");
            }
            else{
                System.out.println("Задача не найдена.\n");
            }
        }

        catch(NumberFormatException e){
            System.out.println("Ошибка! Введите целое число.\n");
        }
    }

    public static void addTask(TaskManager taskManager, Scanner scanner){ 
        System.out.println("Название задачи: ");
        String title = scanner.nextLine();
        if(taskManager.addTask(title) == false){
            System.out.println("Ошибка! Вы ввели пустое значение.\n");
        }
        else{
            System.out.println("\n");
            System.out.println("Задача добавлена! \n");
        }
    }
    public static void main(String[] args) {
        
        
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while(running == true){
            System.out.println("Меню: \n 1 - Список задач.\n 2 - Добавить задачу. \n 3 - Завершить задачу. \n 4 - Удалить задачу. \n 0 - Закрыть. \n " );

            String command = scanner.nextLine();

            switch(command){
                case("4"):
                
                    deleteTask(taskManager, scanner);
                    break;
                    
                case("3"):

                    completeTask(taskManager, scanner);
                    break;

                case("2"):
                    
                    addTask(taskManager, scanner);
                    break;

                case("1"):
                    showTasks(taskManager.getTasks());
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