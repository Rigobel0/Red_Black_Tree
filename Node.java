public class Node {
    public int value;
    public Node left;
    public Node right;
    public Node parent;
    public boolean red;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.red = true;
    }
}