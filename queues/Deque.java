/* *****************************************************************************
 *  Name: JMian
 *  Date: 06 September 2019
 *  Description: Deque.java, Assignment 2, Algorithms Part 1 Coursera
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;
// import java.lang.IllegalArgumentException;   no need to import java.lang
// import java.lang.UnsupportedOperationException;   no need to import java.lang

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;   // beginning of queue
    private Node<Item> last;   // end of queue
    private int n;   // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    // construct as empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (n == 0);
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Please enter a valid argument");
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        n++;
        if (n == 1)
            last = first;
        else
            oldFirst.prev = first;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Please enter a valid argument");
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        n++;
        if (n == 1)
            first = last;
        else
            oldLast.next = last;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is currently empty");
        Item item = first.item;
        first = first.next;
        n--;
        if (n == 0)
            last = null;
        else {
            first.prev = null;
            if (n == 1)
                last = first;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is currently empty");
        Item item = last.item;
        last = last.prev;
        n--;
        if (n == 0)
            first = null;
        else {
            last.next = null;
            if (n == 1)
                first = last;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("No more item in queue");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        System.out.println("deque size: " + deque.size());
        deque.addFirst("11");
        deque.addFirst("22");
        deque.addLast("aa");
        deque.addFirst("33");
        deque.addLast("bb");
        deque.addFirst("44");
        for (String s : deque) System.out.print(s + ", ");
        System.out.println("deque size: " + deque.size());
        deque.removeLast();
        deque.removeFirst();
        deque.removeLast();
        deque.removeLast();
        for (String s : deque) System.out.print(s + ", ");
        System.out.println("deque size: " + deque.size());
        deque.addLast("cc");
        deque.addFirst("55");
        for (String s : deque) System.out.print(s + ", ");
        System.out.println("deque size: " + deque.size());
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        for (String s : deque) System.out.print(s + ", ");
        System.out.println("deque size: " + deque.size());
        deque.addLast("dd");
        deque.addLast("ee");
        deque.addFirst("66");
        for (String s : deque) System.out.print(s + ", ");
        System.out.println("deque size: " + deque.size());
    }


}
