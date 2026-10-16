import javax.swing.SwingUtilities;
public class Activator implements Runnable{
    @Override
    public void run()
    {
        UI ui = new UI();
        ui.setVisible(true);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Activator());
    }
}
