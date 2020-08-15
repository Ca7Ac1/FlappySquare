import javax.swing.JFrame;
import java.awt.EventQueue;

public class Main {
    
    public Main() {
        JFrame frame = new JFrame();

        frame.add(new Game());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Main main = new Main();
            }
        });;
    }
}