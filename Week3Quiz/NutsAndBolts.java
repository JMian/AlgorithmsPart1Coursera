/* *****************************************************************************
 *  Name: JMian
 *  Date: 17 September 2019
 *  Description: NutsAndBolts.java, Week3 Quicksort, Algorithms Part 1 Coursera

 Nuts and bolts. A disorganized carpenter has a mixed pile of n nuts and n bolts.
 The goal is to find the corresponding pairs of nuts and bolts. Each nut fits exactly
 one bolt and each bolt fits exactly one nut. By fitting a nut and a bolt together,
 the carpenter can see which one is bigger (but the carpenter cannot compare two nuts
 or two bolts directly). Design an algorithm for the problem that uses at most proportional to
 nlogn compares (probabilistically).
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class NutsAndBolts {

    private static int partition(Comparable[] a, Comparable[] b, int lo, int hi) {
        int ia = lo - 1, ja = hi + 1;
        int ib = lo - 1, jb = hi + 1;
        int preInd = 0;
        int postInd;
        // first take b as pivot array
        while (true) {
            while (a[++ia].compareTo(b[lo]) <= 0) {
                if (ia == hi)   break;
                if (a[ia].compareTo(b[lo]) == 0)
                    preInd = ia;
            }
            while (b[lo].compareTo(a[--ja]) <= 0) {
                if (ja == lo || ia > ja)   break;
                if (b[lo].compareTo(a[ja]) == 0)
                    preInd = ja;
            }
            if (ia >= ja) break;
            exch(a, ia, ja);
        }
        if (preInd <= ja)   postInd = ja;
        else                postInd = ia;
        exch(a, preInd, postInd);
        // once postInd is found, take it as a pivot to sort the corresponding pivot in array b
        exch(b, lo, postInd);

        while (true) {
            while (b[++ib].compareTo(b[postInd]) <= 0) {
                if (ib == postInd) break;
            }
            while (b[postInd].compareTo(b[--jb]) <= 0) {
                if (jb == postInd) break;
            }
            if (ib >= jb) break;
            exch(b ,ib ,jb);
        }
        System.out.println("preInd " + preInd);
        System.out.println("postInd " + postInd);
        return postInd;
    }

    private static void sort(Comparable[] a, Comparable[] b, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, b, lo, hi);
        sort(a, b, lo, j-1);
        sort(a, b, j+1, hi);
    }

    public static void sort(Comparable[] a, Comparable[] b) {
        StdRandom.shuffle(a);
        System.out.println("Shuffled a " + Arrays.toString(a));
        StdRandom.shuffle(b);
        System.out.println("Shuffled b " + Arrays.toString(b));
        sort(a, b, 0, a.length - 1);
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        Comparable[] a = {1, 5, 8, 0, 3, 4, 6, 2, 7};
        Comparable[] b = {6, 7, 5, 0, 4, 2, 1, 3, 8};
        System.out.println("a before" + Arrays.toString(a));
        System.out.println("b before" + Arrays.toString(b));
        sort(a, b);
        System.out.println("a after" + Arrays.toString(a));
        System.out.println("b after" + Arrays.toString(b));

    }
}
