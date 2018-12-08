package light;

import geometry.Point;

public class Source {

    private Point location;

    public Source(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }
}
