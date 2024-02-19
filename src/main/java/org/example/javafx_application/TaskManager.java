package org.example.javafx_application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskManager {
    private ObservableList<Task> tasks;

    public TaskManager() {
        this.tasks = FXCollections.observableArrayList();

    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }


}
