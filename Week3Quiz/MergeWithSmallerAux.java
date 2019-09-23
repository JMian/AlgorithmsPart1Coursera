/* *****************************************************************************
 *  Name: JMian
 *  Date: 14 September 2019
 *  Description: MergeWithSmallerAux.java, Week3 Mergesort, Algorithms Part 1 Coursera

 Merging with smaller auxiliary array. Suppose that the subarray a[0] to a[n-1] is
 sorted and the subarray a[n] to a[2n-1] is sorted. How can you merge the two subarrays
 so that a[0] to a[2n-1] is sorted using an auxiliary array of length n (instead of
 2n)?
 **************************************************************************** */

import java.util.Arrays;

public class MergeWithSmallerAux {
    public void merge(Comparable[] a) {
        int n = a.length / 2;
        Comparable[] aux = new Comparable[n];
        for (int i = 0; i < n; i++)
            aux[i] = a[i];
        int i = 0;   // index for left half
        int j = n;   // index for right half
        for (int k = 0; k < 2*n; k++) {   // index for a
            if (i >= n)
                a[k] = a[j++];
            else if (j >= 2*n)
                a[k] = aux[i++];
            else if (aux[i].compareTo(a[j]) < 0)
                a[k] = aux[i++];
            else
                a[k] = a[j++];
        }
    }

    public static void main(String[] args) {
        Comparable[] array = {1, 2, 5, 7, 8, 0, 3, 4, 6, 9};
        System.out.println("Input array: " + Arrays.toString(array));
        MergeWithSmallerAux mergeArrays = new MergeWithSmallerAux();
        mergeArrays.merge(array);
        System.out.println("Output merged array: " + Arrays.toString(array));
    }
}
