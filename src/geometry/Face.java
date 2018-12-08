package geometry;

import java.util.ArrayList;

public class Face {

    private ArrayList<Line> edges;
    private ArrayList<Point> vertices = new ArrayList<>();
    private double weight;

    /* Assuming Face has four edges:
     * - a-b
     * - b-c
     * - c-d
     * - d-a
    */
    public Face(
            Point a,
            Point b,
            Point c,
            Point d
    ) {
       this.vertices.add(a);
       this.vertices.add(b);
       this.vertices.add(c);
       this.vertices.add(d);

       this.edges = new ArrayList<>();
       this.edges.add(new Line(a, b));
       this.edges.add(new Line(b, c));
       this.edges.add(new Line(c, d));
       this.edges.add(new Line(d, a));
    }

    public ArrayList<Point> getVertices() {
        return vertices;
    }

    public void updateWeight(Point point) {
//        this.weight = getAverageVerticesDistance(point);
//        this.weight = getMinimalVerticesDistance(point);
        this.weight = getMiddleDistance(point);
    }

    public double getWeight() {
        return weight;
    }

    public void move(Vector vector) {
        for (Point point: this.vertices) {
            point.move(vector);
        }
    }

    public void rotate(double angle, Axis axis) {
        for (Point vertex: this.vertices)
        switch (axis) {
            case X:
                vertex.rotateX(angle);
                break;
            case Y:
                vertex.rotateY(angle);
                break;
            case Z:
                vertex.rotateZ(angle);
                break;
            default:
                break;
        }
    }

    private double getAverageVerticesDistance(Point point) {
        double sum = 0;
        for (Point vert: this.vertices) {
            sum += point.getDistance(vert);
        }
        return sum / this.vertices.size();
    }

    private double getMinimalVerticesDistance(Point point) {
        double min1 = this.vertices.get(0).getDistance(point);
        double min2 = this.vertices.get(0).getDistance(point);
        if (min1 > min2) {
            double tmp = min1;
            min1 = min2;
            min2 = tmp;
        }
        double dist;
        for (int i = 2; i < this.vertices.size(); i++) {
            dist = this.vertices.get(i).getDistance(point);
            if (dist < min1) {
                min2 = min1;
                min1 = dist;
            } else if (dist < min2)
                min2 = dist;
        }
        return min1 + min2;
    }

    private double getMiddleDistance(Point point) {
        double avgX = 0;
        double avgY = 0;
        double avgZ = 0;
        for (Point vertex: this.vertices) {
            avgX += vertex.getX();
            avgY += vertex.getY();
            avgZ += vertex.getZ();
        }
        avgX /= this.vertices.size();
        avgY /= this.vertices.size();
        avgZ /= this.vertices.size();
        return point.getDistance(new Point(avgX, avgY, avgZ));
    }
}
