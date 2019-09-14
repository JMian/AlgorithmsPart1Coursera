/* *****************************************************************************
 *  Name: JMian
 *  Date: 10 September 2019
 *  Description: DutchNationalFlag.java, Week2 Elementary Sorts, Algorithms Part 1 Coursera

 Dutch national flag. Given an array of n buckets, each containing a red, white, or blue
 pebble, sort them by color. The allowed operations are:
 - swap(i,j): swap the pebble in bucket i with the pebble in bucket j
 = color(I): determine the color of the pebble in bucket i
 The performance requirements are as follows:
 - At most n calls to color()
 - At most n calls to swap();
 - Constant extra space
 **************************************************************************** */

import java.util.Arrays;

public class DutchNationalFlag {
    private static final int RED = 0;
    private static final int WHITE = 1;
    private static final int BLUE = 2;
    private int n;
    private int[] buckets;

    public DutchNationalFlag(int[] buckets) {
        n = buckets.length;
        this.buckets = buckets;
    }

    public void sort() {
        int lower = 0;
        int current = 0;
        int upper = n - 1;

        while (current <= upper) {
            switch (color(current)) {
                case RED:
                    if (current != lower)
                        swap(current, lower);
                    lower++;
                    current++;
                    break;
                case WHITE:
                    current++;
                    break;
                case BLUE:
                    swap(current, upper);
                    upper--;
                    break;
            }
        }
    }

    public int color(int i) {
        return buckets[i];
    }
    public void swap(int i, int j) {
        int temp = buckets[j];
        buckets[j] = buckets[i];
        buckets[i] = temp;
    }

    public static void main(String[] args) {
        int[] flagPebbles = {BLUE, WHITE, WHITE, BLUE, RED, RED, BLUE, WHITE, RED, BLUE, WHITE};
        System.out.println("initial buckets: ");
        System.out.println(Arrays.toString(flagPebbles));
        DutchNationalFlag flag = new DutchNationalFlag(flagPebbles);
        flag.sort();
        System.out.println("after sorting: ");
        System.out.println(Arrays.toString(flagPebbles));
    }
}
