/* *****************************************************************************
 *  Name: JMian
 *  Date: 19 September 2019
 *  Description: DecimalDominants.java, Week3 Quicksort, Algorithms Part 1 Coursera

 Given an array with n keys, design an algorithm to find all values that occur more than
 n/10 times. The expected running time of your algorithm should be linear.

 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import java.util.ArrayList;
import java.util.Arrays;

public class DecimalDominants {
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (a[++i].compareTo(a[lo]) < 0)
                if (i == hi) break;

            while (a[lo].compareTo(a[--j]) < 0)
                if (j == lo) break;

            if (i >= j)   break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // the k here is the kth smallest key
    private static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a ,lo, hi);
            if (j < k)          lo = j + 1;
            else if (j > k)     hi = j - 1;
            else                return a[k];
        }
        return a[k];
    }

    // the k parameter is from the n/k, 10 in this case
    public static ArrayList<Comparable> findDecimalDominant(Comparable[] a, int k) {
        int n = a.length;
        int times = n / k;
        Comparable value;
        Comparable prevalue = null;
        ArrayList<Comparable> valuesDominant= new ArrayList<>();
        for (int i = times-1; i < n; i += times ) {   // this step takes n/k times
            value = select(a, i);   // takes linear time complexity, quickselect
            if (value.equals(prevalue))   // check if the chosen value spans more than one stop
                continue;
            int count = 0;
            for (int j = 0; j < n; j++) {  // takes n times. O(n)
                if (a[j].equals(value)) {
                    count++;
                }
            }
            if (count > times)
                valuesDominant.add(value);
            prevalue = value;
        }
        return valuesDominant;
    }

    public static void main(String[] args) {
        Comparable[] a = {5, 2, 7, 3, 2, 7, 5, 5, 3, 2, 10, 2, 7, 5};
        System.out.println("input array: " + Arrays.toString(a));
        int k = 4;
        int n = a.length;
        System.out.println("elements occur more than n/k = " + n + "/" + k + " = " + n/k + " times: ");
        System.out.println(findDecimalDominant(a, k));
        Arrays.sort(a);
        System.out.println("sorted input arrat: " + Arrays.toString(a));
    }
}
