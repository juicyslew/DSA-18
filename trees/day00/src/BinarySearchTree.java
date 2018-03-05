import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    /**
     * Add a node to the BST. Internally calls insert to recursively find the new node's place
     */
    public boolean add(T key) {
        if (find(root, key) != null) return false;
        root = insert(root, key);
        size++;
        return true;
    }

    public void addAll(T[] keys) {
        for (T k : keys)
            add(k);
    }

    public List<T> inOrderTraversal() {
        if (root == null){
            return new ArrayList<T>();
        }
        return inOrderRecurse(root);
    }
    //O(N)
    public List<T> inOrderRecurse(TreeNode<T> pointer){
        List<T> retlist = new ArrayList<T>();
        if (pointer.hasLeftChild()){
            retlist = inOrderRecurse(pointer.leftChild);
        }
        retlist.add(pointer.key);
        if (pointer.hasRightChild()){
            retlist.addAll(inOrderRecurse(pointer.rightChild));
        }

        return retlist;
    }

    /**
     * Deletes a node from the BST using the following logic:
     * 1. If the node has a left child, replace it with its predecessor
     * 2. Else if it has a right child, replace it with its successor
     * 3. If it has no children, simply its parent's pointer to it
     */
    public boolean delete(T key) {
        TreeNode<T> toDelete = find(root, key);
        if (toDelete == null) {
            System.out.println("Key does not exist");
            return false;
        }
        TreeNode<T> deleted = delete(toDelete);
        if (toDelete == root) {
            root = deleted;
        }
        size--;
        return true;
    }

    private TreeNode<T> delete(TreeNode<T> n) {
        // Recursive base case
        if (n == null) return null;

        TreeNode<T> replacement;

        if (n.isLeaf())
            // Case 1: no children
            replacement = null;
        else if (n.hasRightChild() != n.hasLeftChild())
            // Case 2: one child
            replacement = (n.hasRightChild()) ? n.rightChild : n.leftChild; // replacement is the non-null child
        else {
            // Case 3: two children
            replacement = findPredecessor(n);
            replacement.moveChildrenFrom(n);
            if (replacement.isRightChild()){
                replacement.parent.rightChild = null;
            }else{
                replacement.parent.leftChild = null;
            }

        }

        // Put the replacement in its correct place, and set the parent.
        n.replaceWith(replacement);
        return replacement;
    }

    public T findPredecessor(T key) {
        TreeNode<T> n = find(root, key);
        if (n != null) {
            TreeNode<T> predecessor = findPredecessor(n);
            if (predecessor != null)
                return predecessor.key;
        }
        return null;
    }

    public T findSuccessor(T key) {
        TreeNode<T> n = find(root, key);
        if (n != null) {
            TreeNode<T> successor = findSuccessor(n);
            if (successor != null)
                return successor.key;
        }
        return null;
    }
    //O(N)
    private TreeNode<T> findPredecessor(TreeNode<T> n) {
        if (n.hasLeftChild()){
            n = n.leftChild;
            while(n.hasRightChild()){
                n = n.rightChild;
            }
            return n;
        }
        while (n.isLeftChild()) {
            n = n.parent;
        }
        if (n.isRightChild()) {
            return n.parent;
        }else{
            return null;
        }
    }
    //O(N)
    private TreeNode<T> findSuccessor(TreeNode<T> n) {
        if (n.hasRightChild()){
            n = n.rightChild;
            while(n.hasLeftChild()) {
                n = n.leftChild;
            }
            return n;
        }
        if (n.isLeftChild()){
            return n.parent;
        }else{
            return successorRecurseUp(n);
        }
    }
    private TreeNode<T> successorRecurseUp(TreeNode<T> n){
        if(n.isRoot()) {
            return null;
        }else if (n.isLeftChild()){
            return n.parent;
        }else{
            return successorRecurseUp(n.parent);
        }
    }

    /**
     * Returns a node with the given key in the BST, or null if it doesn't exist.
     */
    private TreeNode<T> find(TreeNode<T> currentNode, T key) {
        if (currentNode == null)
            return null;
        int cmp = key.compareTo(currentNode.key);
        if (cmp < 0)
            return find(currentNode.leftChild, key);
        else if (cmp > 0)
            return find(currentNode.rightChild, key);
        return currentNode;
    }

    /**
     * Recursively insert a new node into the BST
     */
    private TreeNode<T> insert(TreeNode<T> node, T key) {
        if (node == null) return new TreeNode<>(key);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.leftChild = insert(node.leftChild, key);
            node.leftChild.parent = node;
        } else {
            node.rightChild = insert(node.rightChild, key);
            node.rightChild.parent = node;
        }
        return node;
    }
}
