package geometry;

public class Line {
    private Equation xEquation;
    private Equation yEquation;
    private Equation zEquation;

    private Point a;
    private Point b;

    public Line(Point a, Point b) {
        double diffX = a.getX() - b.getX();
        double diffY = a.getY() - b.getY();
        double diffZ = a.getZ() - b.getZ();

        this.xEquation = new Equation(a.getX(), diffX);
        this.yEquation = new Equation(a.getY(), diffY);
        this.zEquation = new Equation(a.getZ(), diffZ);

        this.a = a;
        this.b = b;
    }

    public Equation getxEquation() {
        return xEquation;
    }

    public Equation getyEquation() {
        return yEquation;
    }

    public Equation getzEquation() {
        return zEquation;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getPointOn(Double t) {
        return new Point(
          xEquation.getValue(t),
          yEquation.getValue(t),
          zEquation.getValue(t)
        );
    }

    /*
    * Moves
    *   x points in X axis
    *   y points in Y axis
    *   z points in Z axis
    * */
    public void move(double x, double y, double z) {
        this.a.move(x, y, z);
        this.b.move(x, y, z);
    }

    public void rotate(double angle, Axis axis) {
        switch (axis) {
            case X:
                a.rotateX(angle);
                b.rotateX(angle);
                break;
            case Y:
                a.rotateY(angle);
                b.rotateY(angle);
                break;
            case Z:
                a.rotateZ(angle);
                b.rotateZ(angle);
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "Line{" +
                "xEquation=" + xEquation +
                ", yEquation=" + yEquation +
                ", zEquation=" + zEquation +
                ", a=" + a +
                ", b=" + b +
                '}';
    }
}
