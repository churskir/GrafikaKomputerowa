package light;

import geometry.Point2D;

public class PointWithBrightness {

    private Point2D point;
    private int light;
    private int relativeBrightness; // Max == 255

    public PointWithBrightness(Point2D point, int light) {
        this.point = point;
        this.light = light;
    }

    public Point2D getPoint() {
        return point;
    }

    public int getRelativeBrightness() {
        return relativeBrightness;
    }

    public void setRelativeBrightness(int minLight, int maxBrightness) {
        relativeBrightness = (int) ((light - minLight) / (double) (maxBrightness - minLight) * 255);
    }
}
