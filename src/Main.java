import geometry.Cube;
import geometry.Line;
import geometry.Line2D;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Camera camera = new Camera();

        ArrayList<Line> edges = new Cube().getEdges();

        ArrayList<Line2D> linesToView = camera.getView(edges);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame(linesToView);
            }
        });
    }
}
