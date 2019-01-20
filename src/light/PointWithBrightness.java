package light;

import geometry.Point2D;

public class PointWithBrightness {

    private Point2D point;
    private double light;
    private int relativeBrightness; // Max == 255

    public PointWithBrightness(Point2D point, double light) {
        this.point = point;
        this.light = light;
    }

    public Point2D getPoint() {
        return point;
    }

    public int getRelativeBrightness() {
        return relativeBrightness;
    }

    public void setRelativeBrightness(double minLight, double maxBrightness) {
//        relativeBrightness = (int) ((light - minLight) / (maxBrightness - minLight) * 255);
        this.relativeBrightness = (int) (255 * light);
        if (this.relativeBrightness < 0) this.relativeBrightness = 0;
        if (this.relativeBrightness > 255) this.relativeBrightness = 255;
    }
}
