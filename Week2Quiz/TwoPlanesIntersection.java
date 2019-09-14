/* *****************************************************************************
 *  Name: JMian
 *  Date: 10 September 2019
 *  Description: TwoPlanesIntersection.java, Week2 Elementary Sorts, Algorithms Part 1 Coursera

 Intersection of two sets. Given two arrays a[] and b[], each containing n distinct
 2D points in the plane, design a subquadratic algorithm to count the number of points
 that are contained both in array a[] and array b[].
 **************************************************************************** */

import edu.princeton.cs.algs4.Shell;
import java.util.Arrays;

public class TwoPlanesIntersection {

    class Point implements Comparable<Point> {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point that) {
            if (this.x < that.x) return -1;
            if (this.x > that.x) return 1;
            if (this.y < that.y) return -1;
            if (this.y > that.y) return 1;
            return 0;
        }
    }

    public int countIntersection(Point[] a, Point[] b) {
        Shell.sort(a);
        Shell.sort(b);
        int i = 0;
        int j = 0;
        int count = 0;
        int n = a.length;   // a and b have the same number of elements (length)
        while (i < n && j < n) {
            if (a[i].compareTo(b[j]) == 0) {
                count++;
                System.out.println(a[i].x + "," + a[i].y);
                i++;
                j++;
            }
            else if (a[i].compareTo(b[j]) > 0)
                j++;
            else
                i++;
        }
        return count;
    }

    public static void main(String[] args) {
        TwoPlanesIntersection planes = new TwoPlanesIntersection();
        TwoPlanesIntersection.Point ap1 = planes.new Point(3, 8);
        TwoPlanesIntersection.Point ap2 = planes.new Point(0, 0);
        TwoPlanesIntersection.Point ap3 = planes.new Point(7, 6);
        TwoPlanesIntersection.Point ap4 = planes.new Point(3, 9);
        TwoPlanesIntersection.Point ap5 = planes.new Point(6, 7);

        TwoPlanesIntersection.Point bp1 = planes.new Point(-3, 2);
        TwoPlanesIntersection.Point bp2 = planes.new Point(3, 9);
        TwoPlanesIntersection.Point bp3 = planes.new Point(7, 6);
        TwoPlanesIntersection.Point bp4 = planes.new Point(6, 7);
        TwoPlanesIntersection.Point bp5 = planes.new Point(3, 8);

        TwoPlanesIntersection.Point[] planesA = {ap1, ap2, ap3, ap4, ap5};
        TwoPlanesIntersection.Point[] planesB = {bp1, bp2, bp3, bp4, bp5};

        int length = planesA.length;
        System.out.print("plane A's points: ");
        for (int i = 0; i < length; i++)
            System.out.print((int)planesA[i].x + "," + (int)planesA[i].y + "; ");
        System.out.println();
        System.out.print("plane B's points: ");
        for (int i = 0; i < length; i++)
            System.out.print((int)planesB[i].x + "," + (int)planesB[i].y + "; ");
        System.out.println();

        int count = planes.countIntersection(planesA, planesB);
        System.out.println("There are " + count + " identical points ");
    }
}
