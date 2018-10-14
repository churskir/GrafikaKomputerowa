import geometry.Line2D;

import javax.swing.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    public MainFrame(ArrayList<Line2D> linesToView) {
        super("Rysowanie");
        JPanel panel = new MainPanel(linesToView);

        add(panel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
