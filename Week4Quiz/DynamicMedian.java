/* *****************************************************************************
 *  Name: JMian
 *  Date: 25 September 2019
 *  Description: DynamicMedian.java, Week4 Priority Queues, Algorithms Part 1 Coursera

 Dynamic median. Design a data type that supports insert in logarithmic time,
 find-the-median in constant time, and remove-the-median in logarithmic time.
 If the number of keys in the data type is even, find/remove the lower median.
 **************************************************************************** */

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

import java.util.Arrays;
import java.util.Iterator;

public class DynamicMedian {
    private MaxPQ<Integer> maxPQ;   // to store all elements smaller than or equal to the median
    private MinPQ<Integer> minPQ;   // to store all elements greater than or equal to the median

    public DynamicMedian() {
        maxPQ = new MaxPQ<>();
        minPQ = new MinPQ<>();
    }

    public void insert(Integer key) {
        int size = maxPQ.size() + minPQ.size();
        if (size == 0 || size == 1)
            maxPQ.insert(key);
        else if (key > minPQ.min())
            minPQ.insert(key);
        else
            maxPQ.insert(key);

        balance();
    }

    // at all times, minPQ and maxPQ should have equal size
    // or if odd size, the extra key is always stored in maxPQ
    private void balance() {
        if (minPQ.size() > maxPQ.size())
            maxPQ.insert(minPQ.delMin());
        else if (maxPQ.size() - minPQ.size() > 1)
            minPQ.insert(maxPQ.delMax());
    }

    // if odd size, the extra key (median) is always stored in maxPQ,
    // and if even size, we want to obtain the lower median (that's from maxPQ)
    public Integer findMedian() {
        return maxPQ.max();
    }

    public Integer removeMedian() {
        int median = maxPQ.delMax();
        balance();
        return median;
    }

    public static void main(String[] args) {


    }
}
