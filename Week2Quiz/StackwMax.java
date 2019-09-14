/* *****************************************************************************
 *  Name: JMian
 *  Date: 07 September 2019
 *  Description: StackwMax.java, Week2 Stacks and Queues, Algorithms Part 1 Coursera

 Stack with max. Create a data structure that efficiently supports the stack
 operations (push and pop) and also a return-the-maximum operation. Assume the
 elements are real numbers so that you can compare them.
 **************************************************************************** */

import java.util.NoSuchElementException;
import java.util.Iterator;

public class StackwMax {
    private Double[] mainStack;
    private Double[] maxStack;   // to record the maximum value in the stack associated with each item
    private int n;

    public StackwMax() {
        mainStack = new Double[1];
        maxStack = new Double[1];
    }

    public boolean isEmpty() {
        return (n == 0);
    }

    public int size() {
        return n;
    }

    public void push(Double item) {
        if (n == mainStack.length)
            resize(2 * mainStack.length);   // Item the size of array when full
        mainStack[n] = item;
        if (n == 0)   // if there is now only one item in the stack
            maxStack[n] = item;
        else
            if (item.compareTo(maxStack[n-1]) > 0)   // if the new item is bigger in value then the current maximum
                maxStack[n] = item;
            else
                maxStack[n] = maxStack[n-1];
        n++;
    }
    // resize the array
    private void resize(int capacity) {
        Double[] resizingArray1 = new Double[capacity];
        Double[] resizingArray2 = new Double[capacity];
        for (int i = 0; i < n; i++) {
            resizingArray1[i] = mainStack[i];
            resizingArray2[i] = maxStack[i];
        }
        mainStack = resizingArray1;
        maxStack = resizingArray2;
    }

    // remove and return the most recently added item
    public Double pop() {
        if (isEmpty())
            throw new NoSuchElementException("Stack is currently empty");
        Double item = mainStack[--n];
        mainStack[n] = null;
        maxStack[n] = null;
        // halve size of array when array is one-quarter full
        if (n > 0 && n == mainStack.length/4) {
            resize(mainStack.length / 2);
            resize(maxStack.length / 2);
        }
        return item;
    }

    public Double peek() {
        if (isEmpty())
            throw new NoSuchElementException("Stack is currently empty");
        return (mainStack[n-1]);
    }

    public Double getMaximum() {
        if (isEmpty())
            throw new NoSuchElementException("Stack is currently empty");
        return (maxStack[n-1]);
    }

    public Iterator<Double> iterator() {
        return new StackIterator(n);
    }

    private class StackIterator implements Iterator<Double> {
        private int[] shuffledIndex;
        private int i;

        public StackIterator(int n) {
            i = n;
        }

        public boolean hasNext() {
            return (i > 0);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Double next() {
            if (!hasNext())
                throw new NoSuchElementException("No more item in stacke");
            Double item = mainStack[--i];
            return item;
        }
    }

    public static void main(String[] args) {
        StackwMax stack = new StackwMax();
        System.out.println("size: " + stack.size());
        stack.push(3.5);
        stack.push(-2.1);
        stack.push(5.9);
        stack.push(4.0);
        Iterator<Double> iter = stack.iterator();
        while (iter.hasNext())
            System.out.print(iter.next() + ", ");
        System.out.print("size: " + stack.size() + ", ");
        System.out.print("Maximum: " + stack.getMaximum());
        System.out.println();
        stack.pop();
        iter = stack.iterator();
        while (iter.hasNext())
            System.out.print(iter.next() + ", ");
        System.out.print("size: " + stack.size() + ", ");
        System.out.print("Maximum: " + stack.getMaximum());
        System.out.println();
        stack.pop();
        iter = stack.iterator();
        while (iter.hasNext())
            System.out.print(iter.next() + ", ");
        System.out.print("size: " + stack.size() + ", ");
        System.out.print("Maximum: " + stack.getMaximum());
        System.out.println();
        stack.pop();
        iter = stack.iterator();
        while (iter.hasNext())
            System.out.print(iter.next() + ", ");
        System.out.print("size: " + stack.size() + ", ");
        System.out.print("Maximum: " + stack.getMaximum());
        System.out.println();
        stack.push(4.3);
        iter = stack.iterator();
        while (iter.hasNext())
            System.out.print(iter.next() + ", ");
        System.out.print("size: " + stack.size() + ", ");
        System.out.print("Maximum: " + stack.getMaximum());
        System.out.println();
    }
}
