package com.practice;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;
import javafx.geometry.Pos;

public class TaskCell extends ListCell<Task> {
    
    private Region region = new Region();
    private Label titleLabel = new Label();
    private Label statusLabel = new Label();
    private HBox hBox = new HBox();
    private Tooltip toolTip = new Tooltip();

    public TaskCell(){

        hBox.getChildren().add(titleLabel);
        hBox.getChildren().add(region);
        hBox.getChildren().add(statusLabel);
        hBox.setSpacing(15.0);
        hBox.setHgrow(region,Priority.ALWAYS);
        hBox.setAlignment(Pos.CENTER_LEFT);

        titleLabel.setMinWidth(0);
        titleLabel.setTextOverrun(OverrunStyle.ELLIPSIS);
        titleLabel.getStyleClass().add("task-title");
        titleLabel.setTooltip(toolTip);
        statusLabel.getStyleClass().add("task-status");
        statusLabel.setMinWidth(Region.USE_PREF_SIZE);

        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        setGraphicTextGap(0);
        setPrefWidth(0);

    }

    @Override 
    protected void updateItem(Task task , boolean empty){
        super.updateItem(task, empty);

        if(empty == true || task == null){
            toolTip.setText(null);
            setText(null);
            setGraphic(null);
        }
        else{
            toolTip.setText(task.getTitle());
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
