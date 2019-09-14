/* *****************************************************************************
 *  Name: JMian
 *  Date: 10 September 2019
 *  Description: PermutedPkanes.java, Week2 Elementary Sorts, Algorithms Part 1 Coursera

 Permutation. Given two integer arrays of size n, design a subquadratic algorithm to
 determine whether one is a permutation of the other. That is, do they contain exactly
 the same entries but, possibly, in a different order.
 **************************************************************************** */

import edu.princeton.cs.algs4.Shell;
import java.util.Arrays;

public class PermutedArrays {

    public boolean isPermuted(Integer[] array1, Integer[] array2) {
        int length = array1.length;
        Shell.sort(array1);
        Shell.sort(array2);
        for (int i = 0; i < length; i++) {
            if (!array1[i].equals(array2[i]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PermutedArrays arrays = new PermutedArrays();
        Integer[] array1 = {3, 0, 4, 9, 11, -1};
        Integer[] array2 = {4, -1, 11, 3, 0, 9};
        Integer[] array3 = {4, -4, 11, 3, 0, 9};
        System.out.println("array1: " + Arrays.toString(array1));
        System.out.println("array2: " + Arrays.toString(array2));
        System.out.println("array3: " + Arrays.toString(array3));
        System.out.println("Are array1 and array 2 identical? " + arrays.isPermuted(array1, array2));
        System.out.println("Are array1 and array 3 identical? " + arrays.isPermuted(array1, array3));
        System.out.println("Are array2 and array 3 identical? " + arrays.isPermuted(array2, array3));
    }
}
