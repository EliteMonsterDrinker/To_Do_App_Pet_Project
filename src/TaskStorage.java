import java.util.List;
public interface TaskStorage
{
    List<Task> load();
    void save(List<Task> tasks);
}
