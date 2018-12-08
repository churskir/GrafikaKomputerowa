package light;

import geometry.Point;
import geometry.Vector;

import java.util.ArrayList;

public class Sphere {
    private Point center;
    private double radius;
    private ArrayList points;

    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
        this.points = new ArrayList<Point>();
        this.findPoints();
    }

    public Point getPoint(double a, double b) {
        return new Point(
                center.getX() + radius * Math.cos(a) * Math.cos(b),
                center.getY() + radius * Math.sin(b),
                center.getZ() + radius * Math.sin(a) * Math.cos(b)
        );
    }

    public void findPoints() {
        double step = Math.PI / 180;
        for (double a = -Math.PI; a < Math.PI; a += step) {
            for (double b = -Math.PI / 2; b <= Math.PI / 2; b += step) {
                points.add(getPoint(a, b));
            }
        }
    }

    public Point getCenter() {
        return center;
    }

    public ArrayList<Point> getPoints() {
        return this.points;
    }
}
