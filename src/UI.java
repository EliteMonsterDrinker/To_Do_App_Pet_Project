import javax.swing.JTextArea;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JScrollPane;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;


public class UI extends JFrame implements TaskView{
    private static final int PREFERRED_X_DIMENSION= 920;
    private static final int PREFERRED_Y_DIMENSION= 640;

    public static final Color SECONDARY_BACKGROUND_COLOR = new Color(0x11384D); //deep-blue
    public static final Color PRIMARY_BACKGROUND_COLOR = new Color(0x24292e); //github-dark blue

    private JTextArea centralArea;
    private JButton moveUpButton, moveDownButton, completeButton, deleteButton;
    private JButton createNewTaskButton;

    public UI() //конструктор интерфейса, вызывает фабричные методы
    {
        configureFrame();
        add(createButtonPanel(), BorderLayout.WEST);
        add(createCenterPanel(), BorderLayout.CENTER);
        TaskController controller = new TaskController(this);
        ButtonManager.wire(moveUpButton, moveDownButton, completeButton, deleteButton, createNewTaskButton, this::getSelectedLine, controller);
        controller.start();
    }

    // Selection
    private int getSelectedLine()
    {
        try{
            return centralArea.getLineOfOffset(centralArea.getCaretPosition());
        }
        catch(BadLocationException e){
            return -1;
        }
    }


    private void configureFrame()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(PREFERRED_X_DIMENSION, PREFERRED_Y_DIMENSION);
        setTitle("выбор действия");
        setResizable(false);
        getContentPane().setBackground(PRIMARY_BACKGROUND_COLOR);
        setLayout(new BorderLayout());
    }

    private JPanel createButtonPanel()
    {
        moveUpButton = new JButton("↑");
        moveDownButton = new JButton("↓");
        completeButton = new JButton("✔");
        deleteButton = new JButton("Х");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(moveUpButton);
        buttonPanel.add(moveDownButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(deleteButton);
        buttonPanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        return buttonPanel;
    }

    private JTextArea createCentralArea()
    {
        centralArea = new JTextArea();
        centralArea.setEditable(false);
        centralArea.setLineWrap(true);
        centralArea.setWrapStyleWord(true);
        centralArea.setBackground(PRIMARY_BACKGROUND_COLOR);
        centralArea.setForeground(Color.WHITE);
        centralArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        DefaultCaret caret = (DefaultCaret) centralArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        return centralArea;
    }

    private JLabel createMessageLabel(String text)
    {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setBackground(PRIMARY_BACKGROUND_COLOR);
        return label;
    }

    private JButton createNewTaskButton()
    {
        createNewTaskButton = new JButton("СОЗДАТЬ НОВУЮ ЗАДАЧУ");
        createNewTaskButton.setBackground(new Color(0x202535));
        createNewTaskButton.setForeground(Color.WHITE);
        return createNewTaskButton;
    }

    private JPanel createCenterPanel()
    {
        centralArea = createCentralArea();
        centralArea.setRows(10);
        centralArea.setColumns(30); //по-умолчанию задаёт размер текстового поля 10 на 30, чтобы оно не было пустым
        JScrollPane scrollpane = new JScrollPane(centralArea);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PRIMARY_BACKGROUND_COLOR);
        panel.add(scrollpane, BorderLayout.CENTER);
        panel.add(createMessageLabel("ВАШ СПИСОК ПЛАНОВ"), BorderLayout.NORTH);
        panel.add(createNewTaskButton(), BorderLayout.SOUTH);
        return panel;
    }
    //реализация интерфейса TaskView
    @Override
    public void renderTasks(List<Task> tasks) //тут мы проходимся по всем задачам и помечаем крестиком, если они выполнены
    {
        StringBuilder sb = new StringBuilder();
        for(Task t : tasks)
        {
            sb.append(t.getTitle()).append(t.isDone() ? "[✔]" : "[ ]").append("\n"); //тернарный оператор. Если выполнена, то крестик, иначе пустое пространство
        }
        centralArea.setText(sb.toString()); //добавляем в текстовое поле centralArea
    }

    @Override //выводим окошко с сообщением
    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public String promptForTaskTitle()
    {
        return JOptionPane.showInputDialog(this, "Введите задачу");
    }
}
