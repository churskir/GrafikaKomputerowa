import geometry.*;
import geometry.Point;
import geometry.Vector;
import light.PhongsModel;
import light.PointWithBrightness;
import light.Source;
import light.Sphere;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;

public class Camera {
    private Point location;
    private Viewport viewport = new Viewport(5);
    private PhongsModel phongsModel = new PhongsModel();
    private ArrayList<PointWithBrightness> pointsWithBrightness = new ArrayList<>();

    public Camera(Point location) {
        this.location = location;
    }

    public ArrayList<Line2D> getView(ArrayList<Line> objects) {
        ArrayList result = new ArrayList<Line2D>();
        for (Line line: objects) {
            if (pointIsNotBehindCamera(line.getA()) && pointIsNotBehindCamera(line.getB()))
                result.add(this.transform3Dto2D(line));
        }
        return result;
    }

    public ArrayList<Polygon> getViewFaces(ArrayList<Face> faces) {
        ArrayList<Face> sortedFaces = this.sortFaces(faces);
        ArrayList<Polygon> result = new ArrayList<>();
        for (Face face: sortedFaces) {
            if (faceIsNotBehindCamera(face))
                result.add(this.transform3Dto2D(face));
        }
        return result;
    }

    public ArrayList<Point2D> getViewPoint(ArrayList<Point> points) {
        ArrayList<Point2D> result = new ArrayList<>();
        for (Point point: points) {
            if (pointIsNotBehindCamera(point))
                result.add(this.getPointProjection(point));
        }
        return result;
    }

    public void zoomIn() {
        this.viewport.setD(this.viewport.getD() + 1);
    }

    public void zoomOut() {
        int d = viewport.getD();
        if (d > 0)
            this.viewport.setD(d - 1);
    }

    public Point getLocation() {
        return location;
    }

    public ArrayList<PointWithBrightness> getPointsWithBrightness() {
        return pointsWithBrightness;
    }

    public int getLight(Point point, Point sphereCenter, Source source, boolean x) {
        Vector N = new Vector(point, sphereCenter).normalize();
        Vector L = new Vector(point, source.getLocation()).normalize();
        double b = Vector.getAngle(N, L);
        double bAndA = Vector.getAngle(N, new Vector(this.location, new Point(0,0,0)));
        double a = bAndA - b;

        Vector V = new Vector(point, this.location);
        Vector R = Vector.sum(
                    Vector.multiply(
                            N,
                            -2 * Vector.multiply(V, N)
                    ),
                    V);
        if (x) System.out.println(a * 180);
//        return this.phongsModel.getValue(L, N, V, R, a, x);
        return this.phongsModel.getValue(L, N, a, x);
    }

    public void countLight(Sphere sphere, Source source) {
        int maxLight = 1;
        int minLight = 1;
        int light;
        int i = 0;
        for (Point point: sphere.getPoints()) {
            light = getLight(point, sphere.getCenter(), source, i % 1000 == 0);
            this.pointsWithBrightness.add(
                    new PointWithBrightness(
                            this.getPointProjection(point),
                            light
                    )
            );
            if (light > maxLight)
                maxLight = light;
            if (light < minLight)
                minLight = light;

        }
        System.out.println(maxLight - minLight);
        for (PointWithBrightness point: this.pointsWithBrightness)
            point.setRelativeBrightness(minLight, maxLight);
    }

    public Point2D getPointProjection(Point point) {
        return new Point2D(viewport.getPointWithLine(new Line(point, location)));
    }

    private Line2D transform3Dto2D(Line line) {
        Point2D aProjection = this.getPointProjection(line.getA());
        Point2D bProjection = this.getPointProjection(line.getB());
        return new Line2D(
                aProjection,
                bProjection
        );
    }

    private Polygon transform3Dto2D(Face face) {
        int numVertices = 4;
        int[] xCords = new int[numVertices];
        int[] yCords = new int[numVertices];
        Point vert;
        Point2D projection;
        for (int i = 0 ; i < face.getVertices().size(); i++) {
            vert = face.getVertices().get(i);
            projection = this.getPointProjection(vert);
            xCords[i] = (int)projection.getX();
            yCords[i] = (int)projection.getY();

        }
        return new Polygon(xCords, yCords, numVertices);
    }


    private boolean pointIsNotBehindCamera(Point point) {
        return point.getZ() > this.location.getZ();
    }

    private boolean faceIsNotBehindCamera(Face face) {
        for (Point point: face.getVertices()) {
            if (!pointIsNotBehindCamera(point)) {
                return false;
            }
        }
        return true;
    }

    // Sorted decreasing
    private ArrayList<Face> sortFaces(ArrayList<Face> faces) {
        if (faces.isEmpty())
            return faces;
        ArrayList<Face> sortedFaces = new ArrayList<>();
        for (Face face: faces) {
            face.updateWeight(this.location);
        }
        sortedFaces.add(faces.get(0));
        Face face;
        int j;
        for (int i = 1; i < faces.size(); i++) {
            face = faces.get(i);
            j = 0;
            while (j < sortedFaces.size() && sortedFaces.get(j).getWeight() > face.getWeight()) {
                j++;
            }
            if (j == sortedFaces.size())
                sortedFaces.add(face);
            else
                sortedFaces.add(j, face);
        }
        return sortedFaces;
    }
}
