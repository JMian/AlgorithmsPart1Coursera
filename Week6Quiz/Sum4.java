/* *****************************************************************************
 *  Name: JMian
 *  Date: 22 October 2019
 *  Description: Sum4.java, Week6 Hash Tables, Algorithms Part 1 Coursera

 4-SUM. Given an array a[] of n integers, the 4-SUM problem is to determine if there
 exist distinct indices i, j, k, and l such that a[i] + a[j] = a[k] + a[l]. Design
 an algorithm for the 4-SUM problem that takes time proportional to n^2 (under
 suitable technical assumptions).
 **************************************************************************** */

import java.util.Arrays;
import java.util.HashMap;

public class Sum4 {

    public static void main(String[] args) {
        HashMap<Integer, int[]> pairs = new HashMap<>();
        int[] a = {2, 6, 1, 23, 9, 12, 7, 30, 3};
        System.out.println("input array: " + Arrays.toString(a));
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
                int curr = a[i] + a[j];
                int[] eqPair = pairs.get(curr);
                if (eqPair != null) {
                    System.out.println("a[" + eqPair[0] + "] + a[" + eqPair[1] + "] = a[" + i + "]" + " + a[" + j + "] : "
                                        + a[eqPair[0]] + " + " + a[eqPair[1]] + " = " + a[i] + " + " + a[j]
                                        + " = " + curr);
                }
                else {
                    int[] pair = new int[]{i, j};
                    pairs.put(curr, pair);
                }
            }
        }
    }
}
