import geometry.Cube;
import geometry.Line;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements KeyListener {

    private ArrayList<geometry.Line2D> linesToView;
    private ArrayList<Line> edges;
    private Camera camera = new Camera();

    public MainPanel() {
        setPreferredSize(new Dimension(400, 400));
        setFocusable(true);
        addKeyListener(this);

        this.edges = new Cube(20, 50).getEdges();
        this.edges.addAll(new Cube(30, 20).getEdges());
        this.linesToView = camera.getView(edges);
    }

    public void keyPressed(KeyEvent event) {
    }

    public void keyTyped(KeyEvent event) {
//        char keyTyped = event.getKeyChar();
//        int keyCode = event.getKeyCode();
//        boolean shiftDown = event.isShiftDown();
//        System.out.println(keyTyped);
//        System.out.println(keyCode);
//        System.out.println(shiftDown);
        this.interpreteKey(event.getKeyChar());
        this.linesToView = this.camera.getView(this.edges);
        this.repaint();
    }

    public void keyReleased(KeyEvent event) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (geometry.Line2D line: this.linesToView) {
            g2d.draw(line.toAwtLine2D());
        }
    }

    private void interpreteKey(char key) {
        System.out.println(key);
        switch (key) {
            case 'w':
                moveLines(0, 1, 0);
                break;
            case 's':
                moveLines(0, -1, 0);
                break;
            case 'a':
                moveLines(1, 0, 0);
                break;
            case 'd':
                moveLines(-1, 0, 0);
                break;
            case 'q':
                moveLines(0, 0, 1);
                break;
            case 'e':
                moveLines(0, 0, -1);
                break;
            case '+':
                this.camera.zoomIn();
                break;
            case '-':
                this.camera.zoomOut();
                break;
            default:
                break;
        }
    }

    private void moveLines(int x, int y, int z) {
        for (Line line: this.edges) {
            line.move(x, y, z);
        }
    }
}