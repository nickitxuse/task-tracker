package com.practice;



public class Main {
    public static void main(String[] args) {
        
        Task first = new Task(1,"Изучить Git");
        Task second = new Task(2,"Создать первый класс");

        second.complete();

        System.out.println(first);
        System.out.println(second);

    }
}