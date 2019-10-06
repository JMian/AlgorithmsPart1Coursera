/*
from https://stackoverflow.com/questions/20731833/constructing-a-binary-tree-in-java
@ user11057
 */

public class BinaryTree<Key extends Comparable<Key>>  {
    public Node root;

    public BinaryTree() {
    }

    public BinaryTree(Key data)
    {
        root = new Node(data);
    }

    public void add(Node parent, Node child, String orientation)
    {
        if (orientation.equals("left"))
        {
            parent.setLeft(child);
        }
        else
        {
            parent.setRight(child);
        }
    }

}
class Node<Key extends Comparable<Key>>  {
    public Key key;
    public Node left;
    public Node right;

    Node (Key key) {
        this.key = key;
        right = null;
        left = null;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Key getKey() {
        return key;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getLeft() {
        return left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getRight() {
        return right;
    }
}
