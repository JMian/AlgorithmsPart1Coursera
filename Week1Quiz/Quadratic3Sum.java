/* *****************************************************************************
 *  Name: JMian
 *  Date: 04 September 2019
 *  Description: Quadratic3Sum.java, Week1 Analysis of Algorithms Quiz01, Algorithms Part 1 Coursera

 3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time
 proportional to nˆ2 in the worst case. You may assume that you can sort the n integers
 in time proportional to nˆ2 or better.
 **************************************************************************** */
import java.util.Arrays;

public class Quadratic3Sum {
    private int[] input;
    private int n;

    public Quadratic3Sum(int[] input) {
        this.input = input;
        n = input.length;
    }

    public int countCombinations() {
        Arrays.sort(input);
        int count = 0;
        for (int i = 0; i < n-2; i++) {
            int a = input[i];
            int bIndex = i + 1;
            int cIndex = n - 1;
            while (bIndex < cIndex) {
                int b = input[bIndex];
                int c = input[cIndex];
                int sum = a + b + c;
                if (sum == 0) {
                    count++;
                    bIndex++;
                    cIndex--;
                    System.out.println(a + "," + b + "," + c);
                }
                else if (sum < 0)
                    bIndex++;
                else
                    cIndex--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {30, -40, -20, -10, 40, 0, 10, 5};
        System.out.println("Input: " + Arrays.toString(arr));
        Quadratic3Sum q3sum = new Quadratic3Sum(arr);
        int count = q3sum.countCombinations();
        System.out.println(count + " combinations of integers from the input array"
                            + " return a value of 0");
    }
}
