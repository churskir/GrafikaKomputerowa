package geometry;

import java.util.ArrayList;

public class Cube {
    private ArrayList edges;

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

    public ArrayList getEdges() {
        return edges;
    }
}
