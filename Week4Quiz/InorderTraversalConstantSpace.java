/* *****************************************************************************
 *  Name: JMian
 *  Date: 04 October 2019
 *  Description: InorderTraversalConstantSpace.java, Week4 Elementary Symbol Tables, Algorithms Part 1 Coursera

 Design an algorithm to perform an inorder traversal of a binary search tree
 using only a constant amount of extra space.
 *
 * Morris Traversal
 Hint: you may modify the BST during the traversal provided you restore it upon completion.
 **************************************************************************** */

public class InorderTraversalConstantSpace<Key extends Comparable<Key>, Value> extends BST2<Key, Value>{

    public void inorderTraverse(BST2 bst) {
        inorderTraverse(bst.root);
    }

    private void inorderTraverse(Node root) {
        if (root == null)
            return;
        Node pre;
        Node current = root;
        while (current != null) {
            if (current.left == null) {
                System.out.print(current.key + " ");
                current = current.right;
            }
            else {
                pre = current.left;
                while (pre.right != null && pre.right != current) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = current;
                    current = current.left;
                }
                else {
                    System.out.print(current.key + " ");
                    pre.right = null;
                    current = current.right;
                }
            }
        }
    }
    public static void main(String[] args) {
        BST2<Character, Integer> bst = new BST2<>();
        bst.put('S', 0);
        bst.put('E', 1);
        bst.put('X', 2);
        bst.put('A', 3);
        bst.put('C', 4);
        bst.put('R', 5);
        bst.put('H', 6);
        bst.put('M', 7);
        bst.put('L', 8);
        bst.put('P', 9);
        InorderTraversalConstantSpace<Character, Integer> traversal = new InorderTraversalConstantSpace<>();
        traversal.inorderTraverse(bst);
        System.out.println();

    }
}
