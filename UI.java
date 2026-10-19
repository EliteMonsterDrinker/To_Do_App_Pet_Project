import javax.swing.JTextArea;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JScrollPane;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class UI extends JFrame{
    private static final int PREFERED_X_DIMENSION= 920;
    private static final int PREFERED_Y_DIMENSION= 640;

    public static final Color SECONDARY_BACKGROUND_COLOR = new Color(0x11384D); //deep-blue
    public static final Color PRIMARY_BACKGROUND_COLOR = new Color(0x24292e); //github-dark blue

    private JTextArea centralArea;
    private JButton moveUpButton, moveDownButton, completeButton, deleteButton;

    public UI() //конструктор интерфейса, вызывает фабричные методы
    {
        configureFrame();
        add(createButtonPanel(), BorderLayout.WEST);
        add(createCenterPanel(), BorderLayout.CENTER);
    }


    private void configureFrame()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(PREFERED_X_DIMENSION, PREFERED_Y_DIMENSION);
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

    private JPanel createCenterPanel()
    {
        centralArea = createCentralArea();
        centralArea.setRows(10);
        centralArea.setColumns(30); //
        JScrollPane scrollpane = new JScrollPane(centralArea);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PRIMARY_BACKGROUND_COLOR);
        panel.add(scrollpane, BorderLayout.CENTER);
        panel.add(createMessageLabel("ВАШ СПИСОК ПЛАНОВ"), BorderLayout.NORTH);
        return panel;
    }

    public void addText(String text)
    {
        centralArea.append(text + "\n");
    }
}
