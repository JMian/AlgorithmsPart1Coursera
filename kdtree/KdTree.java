/* *****************************************************************************
 *  Name: JMian
 *  Date: 19 October 2019
 *  Description: KdTree.java, Assignment5, Algorithms Part 1 Coursera
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    private Node root;
    private int size;
    private class Node {
        private final Point2D point;
        private final RectHV rect;
        private Node left, right;

        public Node(Point2D point, RectHV rect) {
            this.point = point;
            this.rect = rect;
            this.left = null;
            this.right = null;
        }
    }

    // construct an empty set of points
    public KdTree() {
        root = null;
        size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        root = insert(root, p, 0, 0, 1, 1 , 0);
    }

    private Node insert(Node x, Point2D p, double xmin, double ymin, double xmax, double ymax, int level) {
        if (x == null) {
            size++;
            return new Node(p, new RectHV(xmin, ymin, xmax, ymax));
        }

        if (x.point.equals(p))
            return x;

        double diff = compare(x.point, p, level);

        if (level % 2 == 0) {
            if (diff < 0) {
                x.left = insert(x.left, p, x.rect.xmin(), x.rect.ymin(), x.point.x(), x.rect.ymax(), level+1);
            } else {
                x.right = insert(x.right, p, x.point.x(), x.rect.ymin(), x.rect.xmax(), x.rect.ymax(), level+1);
            }
        }
        else {
            if (diff < 0) {
                x.left = insert(x.left, p, x.rect.xmin(), x.rect.ymin(), x.rect.xmax(), x.point.y(), level+1);
            } else {
                x.right = insert(x.right, p, x.rect.xmin(), x.point.y(), x.rect.xmax(), x.rect.ymax(), level+1);
            }
        }
        return x;
    }

    private double compare(Point2D a, Point2D b, int level) {
        double diff;
        if (level % 2 == 0) {
            diff = b.x() - a.x();
            if (diff == 0)
                diff = b.y() - a.y();
        }
        else {
            diff = b.y() - a.y();
            if (diff == 0)
                diff = b.x() - a.x();
        }
        return diff;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        return contains(root, p, 0);
    }

    private boolean contains(Node x, Point2D p, int level) {
        if (x == null)
            return false;
        if (x.point.equals(p))
            return true;
        double diff = compare(x.point, p, level);
        if (diff < 0)
            return contains(x.left, p, level+1);
        else
            return contains(x.right, p, level+1);
    }

    // draw all points to standard draw
    public void draw() {
        draw(root, 0);
    }

    private void draw(Node x, int level) {
        if (x == null)
            return;
        if (level % 2 == 0) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x.point.x(), x.rect.ymin(), x.point.x(), x.rect.ymax());
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(x.rect.xmin(), x.point.y(), x.rect.xmax(), x.point.y());
        }
        draw(x.left, level+1);
        draw(x.right, level+1);
        StdDraw.setPenColor(StdDraw.BLACK);
        x.point.draw();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();
        ArrayList<Point2D> list = new ArrayList<>();
        range(root, rect, list);
        return list;
    }

    private void range(Node x, RectHV rect, ArrayList<Point2D> list) {
        if (x != null && rect.intersects(x.rect)) {
            if (rect.contains(x.point))
                list.add(x.point);
            range(x.left, rect, list);
            range(x.right, rect, list);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        if (isEmpty())
            return null;
        Point2D nearestP = root.point;
        nearestP = nearest(root, p, nearestP, 0);
        return nearestP;
    }

    private Point2D nearest(Node x, Point2D p, Point2D nearestP, int level) {
        if (x != null && x.rect.distanceSquaredTo(p) <= p.distanceSquaredTo(nearestP)) {
            if (x.point.distanceSquaredTo(p) < p.distanceSquaredTo(nearestP))
                nearestP = x.point;
            double diff = compare(x.point, p, level);
            if (diff < 0) {
                nearestP = nearest(x.left, p, nearestP, level+1);
                nearestP = nearest(x.right, p, nearestP, level+1);
            }
            else {
                nearestP = nearest(x.right, p, nearestP, level+1);
                nearestP = nearest(x.left, p, nearestP, level+1);
            }
        }
        return nearestP;
    }
}
