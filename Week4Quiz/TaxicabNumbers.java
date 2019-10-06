/* *****************************************************************************
 *  Name: JMian
 *  Date: 28 September 2019
 *  Description: TaxicabNumbers.java, Week4 Priority Queues, Algorithms Part 1 Coursera

 Taxicab numbers. A taxicab number is an integer that can be expressed as the sum of
 two cubes of positive integers in two different ways: a^3 + b^3 = c^3 + d^3. For example,
 1729 is the smallest taxicab number: 9^3 + 10^3 = 1^3 + 12^3. Design an algorithm to find
 all taxicab numbers with a, b, c, and d less than n.
 Version 1: Use time proportional to (n^2)logn and space proportional to n^2
 Version 2: Use time proportional to (n^2)logn and space proportional to n

 @https://algs4.cs.princeton.edu/24pq/Taxicab.java.html
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;

public class TaxicabNumbers implements Comparable<TaxicabNumbers>{
    private int i;
    private int j;
    private long cubedSum;   // i^3 + j^3

    public TaxicabNumbers(int i, int j) {
        this.i = i;
        this.j = j;
        this.cubedSum = (long) i*i*i + (long) j*j*j;
    }

    public String toString() {
        return i + "^3 + " + j + "^3";
    }

    public int compareTo(TaxicabNumbers that) {
        if (this.cubedSum > that.cubedSum)   return 1;
        else if (this.cubedSum < that.cubedSum)   return -1;
        else if (this.i > that.i)   return 1;
        else if (this.i < that.i)   return -1;
        else return 0;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        MinPQ<TaxicabNumbers> pq = new MinPQ<>();
        for (int i = 1; i <= n ; i++)
            pq.insert(new TaxicabNumbers(i, i));

        TaxicabNumbers prev = new TaxicabNumbers(0, 0);
        while (!pq.isEmpty()) {
            TaxicabNumbers curr = pq.delMin();
            if (prev.cubedSum == curr.cubedSum) {
                System.out.println(prev + " = " + curr + " = " + curr.cubedSum);
            }
            prev = curr;
            if (curr.j < n) {
                pq.insert(new TaxicabNumbers(curr.i, curr.j + 1));
            }
        }
    }
}
