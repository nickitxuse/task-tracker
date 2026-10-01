package com.practice;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;

public class TaskTrackerApp extends javafx.application.Application {
    @Override 
    public void start(Stage stage){
        stage.setTitle("TASK TRACKER");
        stage.setWidth(600.0);
        stage.setHeight(400.0);


        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        HBox addButtonHBox = new HBox();
        TextField textField = new TextField();
        Label inputLabel = new Label();
        Button addButton = new Button("Добавить");
        ListView<Task> taskListView = new ListView<>();
        HBox removeAndFinishHBOX = new HBox();
        Button finishButton = new Button("Завершить");
        Button removeButton = new Button("Удалить");
        Label label = new Label("TASK TRACKER");
        VBox vBox = new VBox();    
        
        addButtonHBox.setSpacing(10.0);
        textField.setPromptText("Название задачи");
        addButtonHBox.setHgrow(textField, Priority.ALWAYS);
        removeAndFinishHBOX.setSpacing(15.0);
        addButtonHBox.getChildren().add(textField);
        addButtonHBox.getChildren().add(addButton);
        removeAndFinishHBOX.getChildren().add(removeButton);
        removeAndFinishHBOX.getChildren().add(finishButton);
        vBox.getChildren().add(label);
        vBox.getChildren().add(addButtonHBox);
        vBox.getChildren().add(inputLabel);
        vBox.getChildren().add(taskListView);
        vBox.getChildren().add(removeAndFinishHBOX);
        vBox.setVgrow(taskListView, Priority.ALWAYS);
        vBox.setPadding(new Insets(20.0));
        vBox.setSpacing(12);
        inputLabel.setWrapText(true);
        
        label.getStyleClass().add("title");
        addButton.getStyleClass().add("add-button");
        removeButton.getStyleClass().add("remove-button");
        finishButton.getStyleClass().add("finish-button");
   
        Scene scene = new Scene(vBox);
        String fileURL = getClass().getResource("/styles.css").toExternalForm();
        scene.getStylesheets().add(fileURL);

        if(url == null || url.isBlank() || user == null || user.isBlank() || password == null){
            inputLabel.setText("Сбой в полученных данных для ДБ.");
            addButton.setDisable(true);
            finishButton.setDisable(true);
            removeButton.setDisable(true);
        }
        else{
            TaskRepository taskRepository = new TaskRepository(url, user, password);
            TaskManager taskManager = new TaskManager(taskRepository);

            removeButton.disableProperty().bind(taskListView.getSelectionModel().selectedItemProperty().isNull());
            finishButton.disableProperty().bind(taskListView.getSelectionModel().selectedItemProperty().isNull());

            removeButton.setOnAction(event -> {
                    Task selectedTask = taskListView.getSelectionModel().getSelectedItem();

                    if(selectedTask == null){
                        inputLabel.setText("Выберите задачу.");
                        return;
                    }                
                    else{
                        try{
                            if(taskManager.deleteTask(selectedTask.getId())){
                                inputLabel.setText("Задача удалена!");

                                try{
                                taskListView.getItems().setAll(taskManager.getTasks());}
                                catch(SQLException e){inputLabel.setText("Задача удалена, но список не удалось обновить.");}      
                                                          
                            }
                            else{
                                inputLabel.setText("Задача не найдена");
                            }

                        }catch(SQLException e){
                            inputLabel.setText("Ошибка! " + e.getMessage());
                        }
                    }    
            });

            finishButton.setOnAction( event -> {
                    Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
                    if(selectedTask == null){
                        inputLabel.setText("Выберите задачу.");
                        return;
                    }
                    else{
                        try{
                        if(taskManager.completeTask(selectedTask.getId())){

                            inputLabel.setText("Задача помечена как выполненная.");

                            try{
                            taskListView.getItems().setAll(taskManager.getTasks());}
                            catch(SQLException e){inputLabel.setText("Задача завершена, но список не удалось обновить.");}
                        }

                        else{
                            inputLabel.setText("Задача не найдена");
                        }

                        }

                        catch(SQLException e){
                            inputLabel.setText("Ошибка! " + e.getMessage());
                        }
                    }
            });

            addButton.setOnAction( event -> {
                    String title = textField.getText();

                        try{
                            if(taskManager.addTask(title)){
                                textField.clear();
                                inputLabel.setText("Задача добавлена");
                                try{
                                taskListView.getItems().setAll(taskManager.getTasks());}
                                catch(SQLException e){inputLabel.setText("Задача добавлена, но список не удалось обновить.");}
                            }
                            else{
                                inputLabel.setText("Введите название задачи.");
                            }
                        }catch(SQLException e){
                            inputLabel.setText("Ошибка! " + e.getMessage());
                        }

                });

            try{
                taskListView.getItems().setAll(taskManager.getTasks()) ;
            }catch(SQLException e){
                inputLabel.setText("Ошибка! " + e.getMessage());
            }
        }

        stage.setScene(scene);

        


        stage.show();
    }

    public static void main(String[] args){

        launch(args);

    }
}
