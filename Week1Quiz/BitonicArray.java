/* *****************************************************************************
 *  Name: JMian
 *  Date: 04 September 2019
 *  Description: BitonicArray.java, Week1 Analysis of Algorithms Quiz02, Algorithms Part 1 Coursera

 Search in a bitonic array. An array is bitonic if it is comprised of an increasing
 sequence of integers followed immediately by a decreasing sequence of integers.
 Write a program that, given a bitonic array of n distinct integer values, determines
 whether a given integer is in the array.
 - Standard version: Use ~3lgn compares in the worst case.
 - Signing bonus: Use ~2lgn compares in the worst case (and prove that no algorithm
 can guarantee to perform fewer than ~2lgn compares in the worst case).
 **************************************************************************** */
import java.util.Arrays;

public class BitonicArray {
    private int[] biArray;

    public BitonicArray(int[] array) {
        biArray = array;
    }

    public int bitonicSearch(int key) {
        int n = biArray.length;
        int head = 0; // initialize the binary search's head at the start of array
        int end = n - 1; // initialize the binary search's end at the end of array
        // check the key supposing the array is in ascending order (left side)
        while (head <= end) {
            int mid = head + (end - head)/2;
            if (key < biArray[mid])
                end = mid - 1;
            else if (key > biArray[mid])
                head = mid + 1;
            else
                return mid;
        }
        // if the key is not found in the previous method then
        // check the key supposing the array is in descending order (right side)
        head = 0;
        end = n - 1;
        while (head <= end) {
            int mid = head + (end - head)/2;
            if (key < biArray[mid])
                head = mid + 1;
            else if (key > biArray[mid])
                end = mid - 1;
            else
                return mid;
        }
        return -1;    // if the key is not found up until now, it is not in the array
    }

    public static void main(String[] args) {
        int[] array = {-43, -27, -14, 0, 3, 12, 35, 49, 19, 11, -3, -8};
        System.out.println("Input: " + Arrays.toString(array));
        BitonicArray biArray = new BitonicArray(array);
        System.out.println("Key '3' is at index " + biArray.bitonicSearch(3));
        System.out.println("Key '-14' is at index " + biArray.bitonicSearch(-14));
        System.out.println("Key '-3' is at index " + biArray.bitonicSearch(-3));
        System.out.println("Key '-8' is at index " + biArray.bitonicSearch(-8));

        int[] array2 = {-27, -14, 0, 3, 12, 35, 49, 19, 11, -3, -8};
        System.out.println("Input: " + Arrays.toString(array2));
        BitonicArray biArray2 = new BitonicArray(array2);
        System.out.println("Key '3' is at index " + biArray2.bitonicSearch(3));
        System.out.println("Key '-14' is at index " + biArray2.bitonicSearch(-14));
        System.out.println("Key '-3' is at index " + biArray2.bitonicSearch(-3));
        System.out.println("Key '-8' is at index " + biArray2.bitonicSearch(-8));
    }
}
