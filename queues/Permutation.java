/* *****************************************************************************
 *  Name: JMian
 *  Date: 06 September 2019
 *  Description: Permutation.java, Assignment 2, Algorithms Part 1 Coursera
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        if (args.length != 1) {   // check if there is only one command-line argument
            System.out.println("Proper Usage: java program k");
        }
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomQ = new RandomizedQueue<String>();
        int count = 0;   // to record the number of strings read
        // use the Reservoir Sampling technique
        while (!StdIn.isEmpty())
            if (count < k) {
                randomQ.enqueue(StdIn.readString());
                count++;
            }
            else {
                String s = StdIn.readString();
                count++;
                int j = StdRandom.uniform(count);
                if (j < k) {   // if the randomly generated number is small than k
                    randomQ.dequeue();   // remove one element from queue array
                    randomQ.enqueue(s);   // add this current string into the queue array
                }
            }
        for (int i = 0; i < k; i++)
            System.out.println(randomQ.dequeue());
    }
}
