/* *****************************************************************************
 *  Name: JMian
 *  Date: 27 August 2018
 *  Description: SuccessorWithDelete.java, Week1 Union-Find Quiz01, Algorithms Part 1 Coursera
Given a set of n integers S={0,1,...,n-1} and a sequence of requests of the following form:
- Remove x from S
- Find the successor of x: the smallest y in S such that y >= x.
design a data type so that all operations (except construction) take logarithmic
time or better in the worst case.
**************************************************************************** */

public class SuccessorWithDelete {
    private int n;   // the number of elements
    private boolean[] removed;   // to record if an element is removed
    private WQUFindLargest uf;   // modified WQUF to find the largest element in connected component

    public SuccessorWithDelete(int n) {
        this.n = n;
        removed = new boolean[n];
        for (int i = 0; i < n; i++)
            removed[i] = false;   // initially all elements are not removed
        uf = new WQUFindLargest(n);
    }

    // to remove x from set S
    public void remove(int x) {
        removed[x] = true;
        if (x > 0 && removed[x-1])   // if element in position x-1 was removed, union with it
            uf.union(x, x-1);
        if (x < n-1 && removed[x+1])   // if element in position x+1 was removed, union with it
            uf.union(x, x+1);
    }

    public int getSuccessor(int x) {
        if (removed[x]) {   // if x has been removed
            // uf.find(x) finds the largest element in a continuous series of removed elements
            // uf.find(x) + 1 will get the not-yet-removed element one position larger
            // than this largest element
            int successor = uf.find(x) + 1;
            if (successor >= n) {
                System.out.println("either " + x + " is the largest element or"
                                    +" all the elements larger than " + x + " have been removed!");
                return -1;
            }
            return successor;
        }
        else {   // if x has not yet been removed
            System.out.println(x + " has not yet been removed!");
            return x;
        }
    }

    // for testing
    public static void main(String[] args) {
        int n = 8;
        SuccessorWithDelete element = new SuccessorWithDelete(n);
        element.remove(2);
        System.out.println("successor of 2 in {0,1,3,4,5,6,7} is " + element.getSuccessor(2));
        System.out.println("successor of 1 in {0,1,3,4,5,6,7} is " + element.getSuccessor(1));
        element.remove(5);
        System.out.println("successor of 5 in {0,1,3,4,6,7} is " + element.getSuccessor(5));
        System.out.println("successor of 3 in {0,1,3,4,6,7} is " + element.getSuccessor(3));
        System.out.println("successor of 2 in {0,1,3,4,6,7} is " + element.getSuccessor(2));
        element.remove(3);
        System.out.println("successor of 2 in {0,1,4,6,7} is " + element.getSuccessor(2));
        element.remove(4);
        System.out.println("successor of 2 in {0,1,6,7} is " + element.getSuccessor(2));
        System.out.println("successor of 3 in {0,1,6,7} is " + element.getSuccessor(3));
        element.remove(6);
        System.out.println("successor of 6 in {0,1,7} is " + element.getSuccessor(6));
        element.remove(7);
        System.out.println("successor of 6 in {0,1} is " + element.getSuccessor(6));
        System.out.println("successor of 3 in {0,1} is " + element.getSuccessor(3));
    }
}
