import geometry.Cube;
import geometry.Face;
import geometry.Point;
import geometry.Vector;

import java.util.ArrayList;

public class Town {

    private static int leftEnd = 0;
    private static int rightEnd = 0;
    private static int gap = 200;
    private static Point start;

    private static ArrayList<Face> allFaces;

    public static ArrayList<Face> getFaces(Point startPoint) {
        start = startPoint;
        allFaces = new ArrayList<>();
        prepareBuildingOnRight(new Vector(100, 100, 300));
        prepareBuildingOnLeft(new Vector(100, 100, 500));
        rightEnd += 300;
        prepareBuildingOnRight(new Vector(100, 100, 500));
        return allFaces;
    }

    private static void prepareBuildingOnLeft(Vector vector) {
        double width = vector.x;
        double height = -1 * vector.y;
        double depth = vector.z;
        Cube building = new Cube(
                getLeftRelativePoint(),
                new Vector(width, height, depth)
        );
        leftEnd += depth;
        allFaces.addAll(building.getFaces());
    }

    private static void prepareBuildingOnRight(Vector vector) {
        double width = -1 * vector.x;
        double height = -1 * vector.y;
        double depth = vector.z;
        Cube building = new Cube(
                getRightRelativePoint(),
                new Vector(width, height, depth)
        );
        rightEnd += depth;
        allFaces.addAll(building.getFaces());
    }

    private static Point getLeftRelativePoint() {
        Point buildingStart = start.getCopy();
        buildingStart.move(-1 * gap, 0, leftEnd);
        return buildingStart;
    }

    private static Point getRightRelativePoint() {
        Point buildingStart = start.getCopy();
        buildingStart.move(gap, 0, rightEnd);
        return buildingStart;
    }
}
