package geometry;

public class Plane {
    private int a; // x
    private int b; // y
    private int c; // z
    private int d; // free

    public Plane(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Point getPointWithLine(Line line) {
        double free = d;
        free += a * line.getxEquation().getFreeVariable();
        free += b * line.getyEquation().getFreeVariable();
        free += c * line.getzEquation().getFreeVariable();

        double boundary = 0;
        boundary += a * line.getxEquation().getBoundVariable();
        boundary += b * line.getyEquation().getBoundVariable();
        boundary += c * line.getzEquation().getBoundVariable();

        if (free == 0 || boundary == 0)
            return line.getPointOn(0.);
        double t = free / boundary;

        return line.getPointOn(t);
    }
}
