/* *****************************************************************************
 *  Name: JMian
 *  Date: 06 September 2019
 *  Description: RandomizedQueue.java, Assignment 2, Algorithms Part 1 Coursera
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
// import java.lang.IllegalArgumentException;   no need to import java.lang
// import java.lang.UnsupportedOperationException;   no need to import java.lang

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queueArray;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queueArray = (Item[]) new Object[1];
        n = 0;
    }

    // is the randomized queye empty?
    public boolean isEmpty() {
        return (n == 0);
    }

    // return the number of items on the randomized queye
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Please enter a valid argument");
        if (n == queueArray.length)
            resize(2 * queueArray.length);   // double the size of array when full
        queueArray[n++] = item;
    }

    // resize the array
    private void resize(int capacity) {
        Item[] resizingArray = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            resizingArray[i] = queueArray[i];
        queueArray = resizingArray;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is currently empty");
        int randomIndex = StdRandom.uniform(0, n);
        Item item = queueArray[randomIndex];
        // replace the chosen item with the last item in the array so there is no empty cell between [0,n)
        queueArray[randomIndex] = queueArray[--n];
        queueArray[n] = null;
        // halve size of array when array is one-quarter full
        if (n > 0 && n == queueArray.length/4)
            resize(queueArray.length/2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is currently empty");
        return queueArray[StdRandom.uniform(0, n)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedArrayIterator(n);
    }

    private class RandomizedArrayIterator implements Iterator<Item> {
        private int[] shuffledIndex;
        private int i;

        public RandomizedArrayIterator(int n) {
            shuffledIndex = StdRandom.permutation(n);
            i = n;
        }
        public boolean hasNext() {
            return (i > 0);
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("No more item in queue");
            Item item = queueArray[shuffledIndex[--i]];
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rdmQ = new RandomizedQueue<String>();
        System.out.println("rdmQ size: " + rdmQ.size());
        rdmQ.enqueue("aa");
        rdmQ.enqueue("bb");
        rdmQ.enqueue("cc");
        rdmQ.enqueue("dd");
        System.out.print("After four enqueues: ");
        for (String s : rdmQ) System.out.print(s + ", ");
        System.out.println("rdmQ size: " + rdmQ.size());
        System.out.print("sample item: " + rdmQ.sample() + ", After one sample: ");
        for (String s : rdmQ) System.out.print(s + ", ");
        System.out.println("rdmQ size: " + rdmQ.size());
        System.out.print("sample item: " + rdmQ.sample() + ", After one sample: ");
        for (String s : rdmQ) System.out.print(s + ", ");
        System.out.println("rdmQ size: " + rdmQ.size());
        rdmQ.dequeue();
        rdmQ.dequeue();
        System.out.print("After two dequeues: ");
        for (String s : rdmQ) System.out.print(s + ", ");
        System.out.println("rdmQ size: " + rdmQ.size());
        rdmQ.dequeue();
        rdmQ.dequeue();
        System.out.print("After two dequeues: ");
        for (String s : rdmQ) System.out.print(s + ", ");
        System.out.println("rdmQ size: " + rdmQ.size());
    }
}
