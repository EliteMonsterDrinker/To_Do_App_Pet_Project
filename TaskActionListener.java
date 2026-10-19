public interface TaskActionListener
{
    void onMoveUp(int selectedIndex);
    void onMoveDown(int selectedIndex);
    void onComplete(int selectedIndex);
    void onDelete(int selectedIndex);
    void onCreateNew();
}
