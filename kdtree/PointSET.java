/* *****************************************************************************
 *  Name: JMian
 *  Date: 19 October 2019
 *  Description: PointSET.java, Assignment5, Algorithms Part 1 Coursera
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {
    private final TreeSet<Point2D> treeset;

    // construct an empty set of points
    public PointSET() {
        this.treeset = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return treeset.isEmpty();
    }

    // number of points in the set
    public int size() {
        return treeset.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        treeset.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        return treeset.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : treeset)
            p.draw();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();
        ArrayList<Point2D> containedPoints = new ArrayList<>();
        for (Point2D p : treeset)
            if (rect.contains(p))
                containedPoints.add(p);
        return containedPoints;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        if (isEmpty())
            return null;
        Point2D nearestNeighbour = treeset.first();
        for (Point2D point : treeset)
            if (p.distanceSquaredTo(point) < p.distanceSquaredTo(nearestNeighbour))
                nearestNeighbour = point;
        return nearestNeighbour;
    }

}
