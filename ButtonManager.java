import javax.swing.*;
import java.util.function.IntSupplier;

public final class ButtonManager
{
    public static void wire(JButton moveUpButton, JButton moveDownButton, JButton completeButton,
                            JButton deleteButton, JButton createButton, IntSupplier selectedIndex, TaskActionListener listener)
    {
        moveUpButton.addActionListener(e->listener.onMoveUp(selectedIndex.getAsInt()));
        moveDownButton.addActionListener(e->listener.onMoveDown(selectedIndex.getAsInt()));
        completeButton.addActionListener(e->listener.onComplete(selectedIndex.getAsInt()));
        deleteButton.addActionListener(e->listener.onDelete(selectedIndex.getAsInt()));
        createButton.addActionListener(e->listener.onCreateNew());
    }

}
