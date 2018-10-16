import geometry.*;

import java.util.ArrayList;
import java.util.List;

public class Camera {
    private Point location;
    private Viewport viewport = new Viewport(5);

    public Camera(Point location) {
        this.location = location;
    }

    public ArrayList<Line2D> getView(ArrayList<Line> objects) {
        ArrayList result = new ArrayList<Line2D>();
        for (Line line: objects) {
            result.add(this.transform3Dto2D(line));
        }
        return result;
    }

    public void zoomIn() {
        this.viewport.setD(this.viewport.getD() + 1);
    }

    public void zoomOut() {
        int d = viewport.getD();
        if (d > 0)
            this.viewport.setD(d - 1);
    }

    public Point getLocation() {
        return location;
    }

    private Line2D transform3Dto2D(Line line) {
        Point aProjection = viewport.getPointWithLine(new Line(line.getA(), location));
        Point bProjection = viewport.getPointWithLine(new Line(line.getB(), location));
        return new Line2D(
                new Point2D(aProjection),
                new Point2D(bProjection)
        );
    }
}
