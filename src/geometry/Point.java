package geometry;

public class Point {
    private double x;
    private double y;
    private double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void move(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void rotateX(double angle) {
        y = Math.cos(angle) * y - Math.sin(angle) * z;
        z = Math.sin(angle) * y + Math.cos(angle) * z;
    }

    public void rotateY(double angle) {
        x = Math.cos(angle) * x + Math.sin(angle) * z;
        z = -1 * Math.sin(angle) * x + Math.cos(angle) * z;
    }

    public void rotateZ(double angle) {
        x = Math.cos(angle) * x - Math.sin(angle) * y;
        y = Math.sin(angle) * x - Math.cos(angle) * y;
    }

    public Point getCopy() {
        return new Point(x, y, z);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
