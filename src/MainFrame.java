import geometry.Line2D;

import javax.swing.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Rysowanie");
        JPanel panel = new MainPanel();

        add(panel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
