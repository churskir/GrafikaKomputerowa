import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MainPanel extends JPanel {

    private ArrayList<geometry.Line2D> edges;

    public MainPanel(ArrayList<geometry.Line2D> edges) {
        this.edges = edges;
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (geometry.Line2D edge: this.edges) {
            g2d.draw(edge.toAwtLine2D());
        }
    }
}