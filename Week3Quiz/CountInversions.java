/* *****************************************************************************
 *  Name: JMian
 *  Date: 14 September 2019
 *  Description: CountInversions.java, Week3 Mergesort, Algorithms Part 1 Coursera

 Counting inversions. An inversion in an array a[] is a pair of entries a[i] and a[j]
 such that i < j but a[i] > a[j]. Given an array, design a linearithmic algorithm to
 count the number of inversions.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;

public class CountInversions {
    private static int count = 0;

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)      a[k] = aux[j++];
            else if (j > hi)  a[k] = aux[i++];
            else if (aux[i].compareTo(aux[j]) > 0) {
                a[k] = aux[j++];
                count += mid + 1 - i;
            }
            else   a[k] = aux[i++];
        }
    }

    private static void sort (Comparable[]a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static int countInversions(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        sort(a, aux, 0, n-1);
        return count;
    }

    public static void main(String[] args) {
        Comparable[] a = {0, 1, 2, 3, 9, 5, 6, 4, 7, 8};
        System.out.print("for " + Arrays.toString(a) + ": ");
        System.out.println("There are " + countInversions(a) + " inversions");
    }
}
