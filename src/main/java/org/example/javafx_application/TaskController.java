package org.example.javafx_application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;

public class TaskController {
    private TaskManager taskManager;

    @FXML
    private TableView<Task> taskTableView;
    @FXML
    private TableColumn nameColumn, descriptionColumn, statusColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField statusField;

    public void setTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
        nameColumn = new TableColumn("Name");
        nameColumn.setMinWidth(80);
        descriptionColumn = new TableColumn("Description");
        descriptionColumn.setMinWidth(80);
        statusColumn = new TableColumn("Status");
        statusColumn.setMinWidth(80);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("Name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("Description"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("Status"));
        taskTableView.getColumns().addAll(nameColumn, descriptionColumn, statusColumn);
        taskTableView.setItems(taskManager.getTasks());
        System.out.print("HIIII");
    }

    @FXML
    public void addTask() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        String status = statusField.getText();

        if (!name.isEmpty() && !description.isEmpty() && !status.isEmpty()) {
            Task newTask = new Task(name, description, status);
            taskManager.addTask(newTask);
            clearFields();
            taskTableView.refresh();
            System.out.println(taskManager.getTasks().get(0).getName());
        } else {
            showAlert("Error", "All fields are required.");
        }
    }

    @FXML
    public void editTask() {
        Task selectedTask = taskTableView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            String name = nameField.getText();
            String description = descriptionField.getText();
            String status = statusField.getText();

            if (!name.isEmpty() && !description.isEmpty() && !status.isEmpty()) {
                selectedTask.setName(name);
                selectedTask.setDescription(description);
                selectedTask.setStatus(status);
                taskTableView.refresh();
                clearFields();
            } else {
                showAlert("Error", "All fields are required.");
            }
        } else {
            showAlert("Error", "Please select a task to edit.");
        }
    }

    @FXML
    public void deleteTask() {
        Task selectedTask = taskTableView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Task");
            alert.setContentText("Are you sure you want to delete this task?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                taskManager.removeTask(selectedTask);
                clearFields();
            }
        } else {
            showAlert("Error", "Please select a task to delete.");
        }
    }

    private void clearFields() {
        nameField.clear();
        descriptionField.clear();
        statusField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
