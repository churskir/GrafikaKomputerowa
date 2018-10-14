package geometry;

public class Line2D {
    private Point2D a;
    private Point2D b;

    public Line2D(Point2D a, Point2D b) {
        this.a = a;
        this.b = b;
    }

    public java.awt.geom.Line2D toAwtLine2D() {
        return new java.awt.geom.Line2D.Double(
             this.a.getX(),
             this.a.getY(),
             this.b.getX(),
             this.b.getY()
        );
    }

    @Override
    public String toString() {
        return "{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
