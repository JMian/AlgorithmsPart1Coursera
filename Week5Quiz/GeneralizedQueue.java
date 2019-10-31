/* *****************************************************************************
 *  Name: JMian
 *  Date: 16 October 2019
 *  Description: GeneralizedQueue.java, Week5 Balanced Search Trees, Algorithms Part 1 Coursera

 Design a generalized queue data type that supports all of the following operations
 in logarithmic time (or better) in the worst case.
 - Create an empty data structure.
 - Append an item to the end of the queue.
 - Remove an item from the front of the queue.
 - Return the ith item in the queue.
 - Remove the ith item from the queue.

 Hint: create a red-black BST where the keys are integers and the values are the
 items such that the ith largest integer key in the red-black BST corresponds to
 the ith item in the queue.
 **************************************************************************** */

import edu.princeton.cs.algs4.RedBlackBST;

public class GeneralizedQueue<Item> {
    private int count;
    private RedBlackBST<Integer, Item>  rbBST;

    public GeneralizedQueue() {
        count = 0;
        rbBST = new RedBlackBST<>();
    }

    public void append(Item item) {
        rbBST.put(count++, item);
    }

    public void removeFront() {
        rbBST.deleteMin();   // the earlier item added has smaller count index
    }

    public Item get(int i) {
        return rbBST.get(rbBST.select(i));
    }

    public void remove(int i) {
        rbBST.delete(rbBST.select(i));
    }

    public static void main(String[] args) {
        GeneralizedQueue<String> gq = new GeneralizedQueue<>();
        gq.append("!!!");
        gq.append("@@@");
        gq.append("$$$");
        gq.append("%%%");
        gq.append("^^^");
        System.out.println(gq.get(2));
        gq.remove(2);
        System.out.println(gq.get(2));
        System.out.println(gq.get(0));
        gq.removeFront();
        System.out.println(gq.get(2));
        System.out.println(gq.get(0));
        gq.append("...");
        System.out.println(gq.get(2));
        System.out.println(gq.get(0));
        System.out.println(gq.get(3));
    }
}
