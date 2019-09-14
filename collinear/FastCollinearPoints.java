/* *****************************************************************************
 *  Name: JMian
 *  Date: 14 September 2019
 *  Description: FastCollinearPoints.java, Assignment 3, Algorithms Part 1 Coursera
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("Invalid argument");
        checkNullPoint(points);
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        checkDuplicates(sortedPoints);
        lineSegments = new ArrayList<LineSegment>();
        int n = points.length;
        for (int i = 0; i < n - 3; i++) {
            Point p = sortedPoints[i];
            Point[] pointsBySlope = sortedPoints.clone();
            Arrays.sort(pointsBySlope, p.slopeOrder());
            int x = 1;   // the index of the following point after current point
            while (x <= n - 3) {
                ArrayList<Point> collinearPoints = new ArrayList<>();
                int count = 0;
                double currentSlope = p.slopeTo(pointsBySlope[x]);
                do {
                    collinearPoints.add(pointsBySlope[x++]);
                    count++;
                } while (x < n && p.slopeTo(pointsBySlope[x]) == currentSlope);
                // if there is at least 4 points having the same slope to p
                if (collinearPoints.size() >= 3 && p.compareTo(collinearPoints.get(0)) < 0) {
                    Point max = collinearPoints.get(count-1);
                    lineSegments.add(new LineSegment(p, max));
                }
            }
        }
    }

    private void checkNullPoint(Point[] points) {
        int n = points.length;
        for (int i = 0; i < n; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("Null point(s) exists");
        }
    }

    private void checkDuplicates(Point[] sortedPoints) {
        int n = sortedPoints.length;
        for (int i = 0; i < n - 1; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i+1]) == 0)
                throw new IllegalArgumentException("Duplicate(s) exists");
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In("input8.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        Arrays.sort(points);
        System.out.println(Arrays.toString(points));
        Point[] pointsBySlope = points.clone();
        Arrays.sort(pointsBySlope, points[0].slopeOrder());
        System.out.println(Arrays.toString(pointsBySlope));
        double[] slopes = new double[points.length];
        for (int i = 0; i < points.length; i++)
            slopes[i] = points[0].slopeTo(points[i]);
        Arrays.sort(slopes);
        System.out.println(Arrays.toString(slopes));

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            System.out.println(segment);
            segment.draw();
        }
        StdDraw.show();
        System.out.println("Number of line segments: " + collinear.numberOfSegments());
    }
}
