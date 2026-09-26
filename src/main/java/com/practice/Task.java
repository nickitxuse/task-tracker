package com.practice;

public class Task {
    private int id;
    private String title;
    private boolean completed;

    public Task(int id, String title){

        this.id = id;
        this.title = title;
        this.completed = false;

    }

    public void complete(){

        this.completed = true;

    }

    @Override
    public String toString(){

        if(this.completed == true){
            return id + ". "  + title + " [x]";
        }
        else{
            return id + ". "  + title + " [ ]";
        }
    }

    public int getId(){
        return this.id;
    }
}
