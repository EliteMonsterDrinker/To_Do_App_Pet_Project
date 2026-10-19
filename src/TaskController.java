import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class TaskController implements TaskActionListener
{
    public final List<Task> tasks = new ArrayList<>();
    private final TaskView view;
    public TaskController(TaskView view)
    {
        this.view = view;
    }
    public void start()
    {
        render();
    }

    @Override
    public void onComplete(int index)
    {
        mutate(list->{
            if(isValid(index, list)){
                list.get(index).changeDoneStatus();
            }
        });
    }

    @Override
    public void onMoveUp(int index)
    {
        mutate(list->{
            if(index>0 && index<list.size()){
                Collections.swap(list, index, index-1);
            }
        });
    }

    @Override
    public void onMoveDown(int index)
    {
        mutate(list->{
            if(index>=0 && index<list.size()-1){
                Collections.swap(list, index, index+1);
            }
        });
    }
    private static boolean isValid(int i, List<Task> list)
    {
        return i>=0 && i<list.size();
    }

    private void mutate(Consumer<List<Task>> change)
    {
        if(SwingUtilities.isEventDispatchThread())
        {
            change.accept(tasks);
            render();                                        //если мы на EDT, делаем сразу, иначе просим EDT принять наши изменения через invokeLater
        }
        else{
            SwingUtilities.invokeLater(()->{
                change.accept(tasks);
                render();
            });
        }
    }
    private void render()
    {
        view.renderTasks(List.copyOf(tasks)); //делаем копию, чтобы не влезать напрямую
    }

    @Override
    public void onCreateNew()
    {
        String title = view.promptForTaskTitle();
        if(title == null || title.trim().isEmpty()){
            return;
        }

        mutate(list->list.add(new Task(title.trim())));
    }

    @Override
    public void onDelete(int index)
    {
        mutate(list->{
            if(isValid(index, list)){
                view.showMessage("Запись " + list.get(index).getTitle() + " удалена");
                list.remove(index);
            }
        });
    }
}
