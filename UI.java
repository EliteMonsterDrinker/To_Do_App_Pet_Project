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

    public static final int SECONDARY_BACKGROUND_COLOR = 0x11384D; //deep-blue
    public static final int PRIMARY_BACKGROUND_COLOR = 0x24292e; //github-dark blue

    private JLabel messageLabel;
    private JTextArea centralArea;

    private void placeSideButtons(JPanel buttonPanel)
    {
        JButton MoveUpTask = new JButton("↑");
        JButton MoveDownTask = new JButton("↓");
        JButton completeTasks = new JButton("✔");
        JButton deleteTasks = new JButton("Х");

        buttonPanel.add(MoveUpTask);
        buttonPanel.add(MoveDownTask);
        buttonPanel.add(completeTasks);
        buttonPanel.add(deleteTasks);
    }

    UI() {
        this.setTitle("выбор действия");
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(PRIMARY_BACKGROUND_COLOR));
        this.setLayout(new BorderLayout());

        centralArea = new JTextArea();
        centralArea.setEditable(false);
        centralArea.setLineWrap(true);
        centralArea.setWrapStyleWord(true);
        centralArea.setBackground(new Color(PRIMARY_BACKGROUND_COLOR));
        centralArea.setForeground(Color.WHITE);
        centralArea.setFont(new Font("Monospaced", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(centralArea);

        messageLabel = new JLabel("ВАШ СПИСОК ПЛАНОВ:");
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setOpaque(true);
        messageLabel.setBackground(new Color(SECONDARY_BACKGROUND_COLOR));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(PRIMARY_BACKGROUND_COLOR));
        centerPanel.add(messageLabel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(SECONDARY_BACKGROUND_COLOR));
        placeSideButtons(buttonPanel);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.WEST);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(PREFERED_X_DIMENSION, PREFERED_Y_DIMENSION);
    }
}
