/* *****************************************************************************
 *  Name: JMian
 *  Date: 14 September 2019
 *  Description: Points.java, Assignment 3, Algorithms Part 1 Coursera
 **************************************************************************** */

import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    // constructs the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw() {
        StdDraw.point(x, y);
    }

    // draws the line segment from this point to that point
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // return a string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y > that.y) return 1;
        if (this.y < that.y) return -1;
        if (this.x > that.x) return 1;
        if (this.x < that.x) return -1;
        return 0;
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        if (this.y == that.y && this.x == that.x)   // same position
            return Double.NEGATIVE_INFINITY;
        if (this.x == that.x)   // if vertical
            return Double.POSITIVE_INFINITY;
        if (this.y == that.y)   // if horizontal
            return +0.0;
         return (double) (that.y - this.y) / (that.x - this.x);
    }

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {
        return new SlopeOrder();
    }

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point p, Point q) {
            if (slopeTo(p) > slopeTo(q)) return 1;
            if (slopeTo(p) < slopeTo(q)) return -1;
            return 0;
        }
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(3, 3);
        Point p3 = new Point(-3, -3);
        Point p4 = new Point(6, 4);
        Point p5 = new Point(2, 2);
        Point p6 = new Point(2, 8);
        Point[] points = {p1, p2, p3, p4, p5, p6};
        System.out.println("Points: " + Arrays.deepToString(points));
        System.out.println("Slope p1, p2: " + p1.slopeTo(p2));
        System.out.println("Slope p1, p3: " + p1.slopeTo(p3));
        System.out.println("Slope p1, p4: " + p1.slopeTo(p4));
        System.out.println("Slope p1, p5: " + p1.slopeTo(p5));
        System.out.println("Slope p1, p6: " + p1.slopeTo(p6));
        System.out.println("Compare p1 to p2: " + p1.compareTo(p2));
        System.out.println("Compare p1 to p5: " + p1.compareTo(p5));
        System.out.println("Compare p1 to p3: " + p1.compareTo(p3));
        Arrays.sort(points);
        System.out.println("Sorted Points: " + Arrays.deepToString(points));
    }
}
