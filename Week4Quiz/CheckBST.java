/* *****************************************************************************
 *  Name: JMian
 *  Date: 04 October 2019
 *  Description: CheckBST.java, Week4 Elementary Symbol Tables, Algorithms Part 1 Coursera

 Check if a binary tree is a BST. Given a binary tree where each Node contains a key,
 determine whether it is a binary search tree. Use extra space proportional to the height of the tree.

 Hint: design a recursive function isBST(Node x, Keymin, Keymax that determines whether x is
 the root of a binary search tree with all keys between min and max.
 **************************************************************************** */

public class CheckBST<Key extends Comparable<Key>> extends BinaryTree<Key> {

   /*  Incorrect implementation, because this is based on that the parameter is a BST

    public boolean isBST(BST2 bst) {
        return isBST(bst.root);
    }

    private boolean isBST(Node x) {
        if (x.left != null) {
            if (!(x.left.key.compareTo(x.key) <= 0))
                return false;
            return isBST(x.left);
        }
        if (x.right != null) {
            if (!(x.key.compareTo(x.right.key) <= 0))
                return false;
            return isBST(x.right);
        }
        return true;
    }

    */

    public boolean isBST(Node x, Comparable<Key> min, Comparable<Key> max) {
        if (x == null)
            return true;
        if (x.key.compareTo(min) < 0 || x.key.compareTo(max) > 0)
            return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
   }

    public static void main(String[] args) {
        Node<Integer> n1 = new Node<>(1);
        Node<Integer> n2 = new Node<>(4);
        Node<Integer> n3 = new Node<>(2);
        Node<Integer> n4 = new Node<>(5);

        BinaryTree<Integer> tree1 = new BinaryTree<>(3); //        3
        tree1.add(tree1.root, n1, "left"); //                    1/ \
        tree1.add(tree1.root, n2, "right"); //                       4
        tree1.add(n2, n3, "left"); //                              2/ \
        tree1.add(n2, n4, "right"); //                                 5
        CheckBST<Integer> check1 = new CheckBST<>();
        System.out.println("tree 1: " + check1.isBST(tree1.root, 1, 5));

        // have to re-create the nodes or else these nodes will have links/relations
        // among themselves as indicated above when building tree1
        n1 = new Node<>(1);
        n2 = new Node<>(4);
        n3 = new Node<>(2);
        n4 = new Node<>(5);

        BinaryTree<Integer> tree2 = new BinaryTree<>(3); //        3
        tree2.add(tree2.root, n3, "left"); //                     / \
        tree2.add(tree2.root, n2, "right"); //                   2   4
        tree2.add(n3, n1, "left"); //                           / \ / \
        tree2.add(n2, n4, "right"); //                         1       5
        CheckBST<Integer> check2 = new CheckBST<>();
        System.out.println("tree 2: " + check2.isBST(tree2.root, 1, 5));

    }
}
