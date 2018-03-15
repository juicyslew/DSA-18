public class TreeNode<T extends Comparable>{
    T key;
    TreeNode<T> leftChild, rightChild;
    boolean color;

    public static final boolean LEFT = false;
    public static final boolean RIGHT = true;

    public TreeNode(T key) {
        this(key, RedBlackTree.RED);
    }
    public TreeNode(T key, boolean color){
        this.key = key;
        this.leftChild = null;
        this.rightChild = null;
        this.color = color;
    }
    public boolean isLeaf() { return leftChild == null && rightChild == null; }

    public TreeNode<T> Child(boolean dir){ return (dir == RIGHT) ? rightChild : leftChild; }

    public void setChild(boolean dir, TreeNode<T> nchild){
        if (dir == RIGHT){
            rightChild = nchild;
        }else{
            leftChild = nchild;
        }
    }
}