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
    public static void main(String[] args) {
        
        ArrayList<Task> taskList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int idCounter = 1;
        boolean running = true;
        
        while(running == true){
            System.out.println("Меню: \n 1 - Список задач.\n 2 - Добавить задачу. \n 3 - Завершить задачу. \n 4 - Удалить задачу. \n 0 - Закрыть. \n " );

            String command = scanner.nextLine();

            switch(command){
                case("4"):
                    showTasks(taskList);
                    System.out.println("Введите id задачи: ");
                    String removeId = scanner.nextLine();

                    try{
                        int taskId = Integer.parseInt(removeId);

                        boolean found = false;
                        System.out.println("\n");

                        for(int i = 0; i < taskList.size() ; i++){
                            if(taskId == taskList.get(i).getId()){
                                found = true;
                                taskList.remove(i);
                                break;
                            }
                        }
                        if(found == true){
                            System.out.println("Задача удалена.\n");
                        }
                        else{
                            System.out.println("Задача не найдена.\n");
                        }
                    }

                    catch(NumberFormatException e){
                        System.out.println("Ошибка! Введите целое число.\n");
                    }

                    break;
                case("3"):
                    showTasks(taskList);
                    System.out.println("Введите id задачи: ");
                    String completeId = scanner.nextLine();

                    try{
                        int taskID = Integer.parseInt(completeId);
                    
                        boolean found = false;
                        System.out.println("\n");

                        for(Task task : taskList){
                            if(taskID == task.getId()){ 
                                found = true;
                                task.complete();
                                break;
                            }
                        }
                   
                    
                        if(found == true){
                            System.out.println("Задача помечена как выполненная.\n");
                        }
                            
                        else{
                            System.out.println("Задача не найдена.\n");
                        }
                    }
                        
                    catch(NumberFormatException e){
                        System.out.println("Ошибка! Введите целое число.\n");
                    }
                
                    break;

                case("2"):
                    System.out.println("Название задачи: ");
                    String title = scanner.nextLine();
                    System.out.println("\n");
                    taskList.add(new Task(idCounter, title));
                    System.out.println("Задача добавлена! \n");
                    idCounter++;
                    break;

                case("1"):
                    showTasks(taskList);
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