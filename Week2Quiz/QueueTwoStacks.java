/* *****************************************************************************
 *  Name: JMian
 *  Date: 07 September 2019
 *  Description: QueueTwoStacks.java, Week2 Stacks and Queues, Algorithms Part 1 Coursera

 Queue with two stacks. Implement a queue with two stacks so that each queue
 operations takes a constant amortized number of stack operations.
 **************************************************************************** */

import java.util.Stack;
import java.util.NoSuchElementException;

public class QueueTwoStacks<Item> {
    private Stack<Item> inStack = new Stack<Item>();
    private Stack<Item> outStack = new Stack<Item>();

    public boolean isEmpty() {
        return (inStack.empty() && outStack.empty());
    }

    public int size() {
        return (inStack.size() + outStack.size());
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Please enter a valid argument");
        inStack.push(item);
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is currently empty");
        if (outStack.empty()) {
            while (!inStack.empty())
                outStack.push(inStack.pop());
        }
        Item item = outStack.pop();
        return item;
    }

    public static void main(String[] args) {
        QueueTwoStacks<String> queue = new QueueTwoStacks<String>();
        System.out.println("size: " + queue.size());
        queue.enqueue("aa");
        queue.enqueue("bb");
        queue.enqueue("cc");
        queue.enqueue("dd");
        System.out.print("size: " + queue.size() + " > ");
        while (!queue.isEmpty())
            System.out.print(queue.dequeue() + ", ");
        System.out.println();
        queue.enqueue("aa");
        queue.enqueue("bb");
        queue.enqueue("cc");
        queue.enqueue("dd");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.print("size: " + queue.size() + " > ");
        while (!queue.isEmpty())
            System.out.print(queue.dequeue() + ", ");
        System.out.println();
        queue.enqueue("aa");
        queue.enqueue("bb");
        queue.enqueue("cc");
        queue.enqueue("dd");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("ee");
        queue.enqueue("ff");
        System.out.print("size: " + queue.size() + " > ");
        while (!queue.isEmpty())
            System.out.print(queue.dequeue() + ", ");
        System.out.println();

    }
}
