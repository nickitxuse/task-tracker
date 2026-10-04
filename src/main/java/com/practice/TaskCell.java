package com.practice;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class TaskCell extends ListCell<Task> {
    
    private Label titleLabel = new Label();
    private Label statusLabel = new Label();
    private HBox hBox = new HBox();

    public TaskCell(){

        hBox.getChildren().add(titleLabel);
        hBox.getChildren().add(statusLabel);
        hBox.setSpacing(15.0);

        titleLabel.getStyleClass().add("task-title");
        statusLabel.getStyleClass().add("task-status");

    }

    @Override 
    protected void updateItem(Task task , boolean empty){
        super.updateItem(task, empty);

        if(empty == true || task == null){
            setText(null);
            setGraphic(null);
        }
        else{
            setText(null);
            titleLabel.setText(task.getTitle());
            statusLabel.getStyleClass().removeAll("status-done");
            statusLabel.getStyleClass().removeAll("status-pending");
            if(task.isCompleted()){
                statusLabel.setText("Выполнено.");
                statusLabel.getStyleClass().add("status-done");
            }
            else{
                statusLabel.setText("В работе...");
                statusLabel.getStyleClass().add("status-pending");
            }
            setGraphic(hBox);
        }

    }   



}
