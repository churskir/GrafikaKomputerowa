package geometry;

import java.util.ArrayList;

public class Face {

    private ArrayList<Line> edges;

    public Face(ArrayList<Line> edges) {
        assert(edges.size() == 4);
        this.edges = edges;
    }

    public Face(
            Line edge1,
            Line edge2,
            Line edge3,
            Line edge4
    ) {
        this.edges = new ArrayList<>();
        this.edges.add(edge1);
        this.edges.add(edge2);
        this.edges.add(edge3);
        this.edges.add(edge4);
    }
}
