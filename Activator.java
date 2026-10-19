import javax.swing.SwingUtilities;
public class Activator implements Runnable{
    @Override
    public void run()
    {
        UI ui = new UI();
        ui.setVisible(true);
        ui.addText("тестовая запись 1");
        ui.addText("тестовая запись 2");
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Activator());
    }
}
