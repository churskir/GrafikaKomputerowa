package geometry;

public class Vector {
    public double x;
    public double y;
    public double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Point a, Point b) {
        this.x =  a.getX() - b.getX();
        this.y =  a.getY() - b.getY();
        this.z =  a.getZ() - b.getZ();
    }

    public Vector normalize() {
        double len = this.getLength();
        return new Vector(
                this.x / len,
                this.y / len,
                this.z / len
        );
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public static double multiply(Vector a, Vector b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static double getAngle(Vector a, Vector b) {
        Vector aN = a.normalize();
        Vector bN = b.normalize();
//        System.out.println(aN.toString());
//        System.out.println(bN.toString());
        double dist = Vector.getDistance(aN, bN);
//        System.out.println(dist);
        return 2 * Math.asin(dist / 2);
    }

    public static double getDistance(Vector a, Vector b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) + Math.pow(a.z - b.z, 2));
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
