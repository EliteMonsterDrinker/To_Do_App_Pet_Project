import java.util.List;
public interface TaskView
{
    void renderTasks(List<Task> tasks);
    void showMessage(String message);
    String promptForTaskTitle();
}
