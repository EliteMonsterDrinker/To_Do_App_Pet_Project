package com.ui;
import java.util.List;
import com.model.Task;

public interface TaskView
{
    void renderTasks(List<Task> tasks);
    void showMessage(String message);
    String promptForTaskTitle();
}
