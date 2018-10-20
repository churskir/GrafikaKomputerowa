package geometry;

import java.util.ArrayList;
import java.util.HashMap;

public class Cube {
    private ArrayList edges = new ArrayList();
    private HashMap<String, Point> apexes = new HashMap();

    public Cube(ArrayList edges) {
        assert(edges.size() == 12);
        this.edges = edges;
    }

    public Cube(double defaultStart, double defaultSize) {
        defaultSize += defaultStart;
        this.edges = new ArrayList();
        edges.add(new Line(new Point(defaultStart, defaultStart, defaultStart), new Point(defaultSize , defaultStart, defaultStart)));
        edges.add(new Line(new Point(defaultStart, defaultStart, defaultStart), new Point(defaultStart, defaultSize , defaultStart)));
        edges.add(new Line(new Point(defaultSize , defaultStart, defaultStart), new Point(defaultSize, defaultSize ,defaultStart)));
        edges.add(new Line(new Point(defaultStart, defaultSize, defaultStart), new Point(defaultSize ,defaultSize, defaultStart)));

        edges.add(new Line(new Point(defaultStart, defaultStart, defaultSize), new Point(defaultSize, defaultStart, defaultSize)));
        edges.add(new Line(new Point(defaultStart, defaultStart, defaultSize), new Point(defaultStart ,defaultSize, defaultSize)));
        edges.add(new Line(new Point(defaultSize, defaultStart, defaultSize), new Point(defaultSize, defaultSize, defaultSize)));
        edges.add(new Line(new Point(defaultStart, defaultSize, defaultSize), new Point(defaultSize, defaultSize, defaultSize)));

        edges.add(new Line(new Point(defaultStart, defaultStart, defaultStart), new Point(defaultStart, defaultStart, defaultSize)));
        edges.add(new Line(new Point(defaultSize, defaultStart, defaultStart), new Point(defaultSize, defaultStart, defaultSize)));
        edges.add(new Line(new Point(defaultStart, defaultSize, defaultStart), new Point(defaultStart, defaultSize, defaultSize)));
        edges.add(new Line(new Point(defaultSize, defaultSize, defaultStart), new Point(defaultSize, defaultSize, defaultSize)));
    }

    public Cube(Point start, Vector dimensions) {
        prepareApexes(start, dimensions);
        prepareFrontEdges();
        prepareBackEdges();
        prepareSideEdges();
    }

    public ArrayList getEdges() {
        return edges;
    }

    private void prepareApexes(Point start, Vector dimensions) {
        this.apexes.put("frontLeftBottom", new Point(start.getX(), start.getY(), start.getZ()));
        this.apexes.put("frontRightBottom", new Point(start.getX() + dimensions.x, start.getY(), start.getZ()));
        this.apexes.put( "frontRightTop", new Point(start.getX() + dimensions.x, start.getY() + dimensions.y, start.getZ()));
        this.apexes.put("frontLeftTop", new Point(start.getX(), start.getY() + dimensions.y, start.getZ()));

        this.apexes.put("backLeftBottom", new Point(start.getX(), start.getY(), start.getZ() + dimensions.z));
        this.apexes.put("backRightBottom", new Point(start.getX() + dimensions.x, start.getY(), start.getZ() + dimensions.z));
        this.apexes.put("backRightTop", new Point(start.getX() + dimensions.x, start.getY() + dimensions.y, start.getZ() + dimensions.z));
        this.apexes.put("backLeftTop", new Point(start.getX(), start.getY() + dimensions.y, start.getZ() + dimensions.z));
    }

    private void prepareFrontEdges() {
        this.edges.add(new Line(this.apexes.get("frontLeftBottom"), this.apexes.get("frontRightBottom")));
        this.edges.add(new Line(this.apexes.get("frontRightBottom"), this.apexes.get("frontRightTop")));
        this.edges.add(new Line(this.apexes.get("frontRightTop"), this.apexes.get("frontLeftTop")));
        this.edges.add(new Line(this.apexes.get("frontLeftTop"), this.apexes.get("frontLeftBottom")));
    }

    private void prepareBackEdges() {
        this.edges.add(new Line(this.apexes.get("backLeftBottom"), this.apexes.get("backRightBottom")));
        this.edges.add(new Line(this.apexes.get("backRightBottom"), this.apexes.get("backRightTop")));
        this.edges.add(new Line(this.apexes.get("backRightTop"), this.apexes.get("backLeftTop")));
        this.edges.add(new Line(this.apexes.get("backLeftTop"), this.apexes.get("backLeftBottom")));
    }

    private void prepareSideEdges() {
        this.edges.add(new Line(this.apexes.get("frontLeftBottom"), this.apexes.get("backLeftBottom")));
        this.edges.add(new Line(this.apexes.get("frontRightBottom"), this.apexes.get("backRightBottom")));
        this.edges.add(new Line(this.apexes.get("frontRightTop"), this.apexes.get("backRightTop")));
        this.edges.add(new Line(this.apexes.get("frontLeftTop"), this.apexes.get("backLeftTop")));
    }
}
