import java.util.ArrayList;
import java.util.List;

/*public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * Delete a key from the tree rooted at the given node.
     *//*
    *//*@Override
    TreeNode<T> delete(TreeNode<T> n, T key) {
        n = super.delete(n, key);
        if (n != null) {
            //return balance(root);
            // update the height of the tree using the height of the left and right child
            // return balance(n)
        }
        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     *//*
    *//*@Override
    TreeNode<T> insert(TreeNode<T> n, T key) {
        n = super.insert(n, key);
        System.out.println(n);
        System.out.println(n.leftChild);
        System.out.println(n.rightChild);
        if (n != null) {
            if (root == null){
                root = n;
                return n;
            }
            System.out.println(n);
            return balance(root);
            //return balance(n);
            // update the height of the tree using the height of the left and right child
            // return balance(n)
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     *//*
    @Override
    TreeNode<T> deleteMin(TreeNode<T> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(TreeNode<T> n) {
        if (n == null){
            return -1;
        }else {
            return n.height;
        }
    }

    // Remember that this function also updates the height of all the subsequent nodes the recursive function looks at.
    private int Updateheight(TreeNode<T> n) {
        if (n == null){
            return -1;
        }else {
            n.height = Math.max(height(n.leftChild), height(n.rightChild));
            return n.height;
        }
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree. Return the head of the new subtree
    TreeNode<T> balance(TreeNode<T> n) {
        //base case
        if (n.isLeaf()){
            return n;
        }

        //do balancing further down first, save new children
        System.out.println(n);
        System.out.println(n.rightChild);
        System.out.println(n.leftChild);
        n.leftChild = balance(n.leftChild);
        n.rightChild = balance(n.rightChild);


        TreeNode<T> retnode;
        // Four Cases
        if (balanceFactor(n) >= 2){
            if (balanceFactor(n.rightChild) >= 0){
                //Right Right
                retnode = rotateLeft(n);
            }else{
                //Right Left
                n.rightChild = rotateRight(n.rightChild);
                retnode = rotateLeft(n);
            }
        }else if (balanceFactor(n) <= -2){
            if (balanceFactor(n.leftChild) <= 0){
                //Left Left
                retnode = rotateRight(n);
            }else{
                //Left Right
                n.leftChild = rotateLeft(n.leftChild);
                retnode = rotateRight(n);
            }
        }else{
            retnode = n;
        }

        //check if the root changed
        if (root == n){
            root = retnode;
        }
        return retnode;
    }

    *//**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     *//*
    private int balanceFactor(TreeNode<T> n) {
        return height(n.rightChild) - height(n.leftChild);
    }

    *//**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     *//*
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        TreeNode<T> lchild = n.leftChild;
        if (lchild == null){
            return n;
        }

        n.leftChild = lchild.rightChild;
        lchild.rightChild = n;
        Updateheight(n);
        Updateheight(lchild);
        return lchild;
    }

    *//**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     *//*
    private TreeNode<T> rotateLeft(TreeNode<T> n) {
        TreeNode<T> rchild = n.rightChild;
        if (rchild == null){
            return n;
        }

        n.rightChild = rchild.leftChild;
        rchild.leftChild = n;
        Updateheight(n);
        Updateheight(rchild);
        return rchild;
    }
}*/

public class AVLTree<T extends Comparable<T>> {
    TreeNode<T> root;
    protected int size;

    public int size() {
        return size;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

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
        List<T> list = new ArrayList<>();
        inOrderTraversal(root, list);
        return list;
    }

    public void inOrderTraversal(TreeNode<T> node, List<T> list) {
        if (node != null) {
            inOrderTraversal(node.leftChild, list);
            list.add(node.key);
            inOrderTraversal(node.rightChild, list);
        }
    }

    public boolean delete(T key) {
        TreeNode<T> toDelete = find(root, key);
        if (toDelete == null) {
            System.out.println("Key does not exist");
            return false;
        }
        TreeNode<T> deleted = delete(root, key);
        if (toDelete == root) {
            root = deleted;
        }
        size--;
        return true;
    }

    TreeNode<T> delete(TreeNode<T> n, T key) {
        int cmp = key.compareTo(n.key);
        TreeNode<T> retval;
        if (cmp < 0) {
            n.leftChild = delete(n.leftChild, key);
            retval = n;
        } else if (cmp > 0) {
            n.rightChild = delete(n.rightChild, key);
            retval = n;
        } else {
            if (n.leftChild == null) {
                retval = n.rightChild;
            } else if (n.rightChild == null) {
                retval =  n.leftChild;
            } else {
                TreeNode<T> tmp = n;
                n = min(tmp.rightChild);
                //deleteMin(tmp.rightChild);

                //lol but why?
                n.rightChild = deleteMin(tmp.rightChild); //tmp.rightChild;
                n.leftChild = tmp.leftChild;
                retval = n;
            }
        }
        Updateheight(retval);
        return balance(retval);
    }

    /**
     * Returns the node with the smallest key in the subtree.
     */
    private TreeNode<T> min(TreeNode<T> x) {
        if (x.leftChild == null) return x;
        return min(x.leftChild);
    }

    /**
     * Removes the smallest key and associated value from the given subtree.
     */
    TreeNode<T> deleteMin(TreeNode<T> node){
        TreeNode<T> n = deleteMinRecurse(node);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }
    TreeNode<T> deleteMinRecurse(TreeNode<T> n) {
        if (n.leftChild == null) return n.rightChild;
        n.leftChild = deleteMin(n.leftChild);
        return n;
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
    TreeNode<T> Recurseinsert(TreeNode<T> node, T key) {
        if (node == null) {
            TreeNode<T> newnode = new TreeNode<>(key);
            Updateheight(newnode);
            return newnode;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.leftChild = Recurseinsert(node.leftChild, key);
        } else {
            node.rightChild = Recurseinsert(node.rightChild, key);
        }
        Updateheight(node);

        return balance(node);
    }


    TreeNode<T> insert(TreeNode<T> node, T key) {
        TreeNode<T> n = Recurseinsert(node, key);
        if (n != null) {
            return n;
            //return balance(n);
            // update the height of the tree using the height of the left and right child
            // return balance(n)
        }
        return null;
    }

    private int height(TreeNode<T> n) {
        if (n == null){
            return -1;
        }else {
            return n.height;
        }
    }

    // Remember that this function also updates the height of all the subsequent nodes the recursive function looks at.
    private int Updateheight(TreeNode<T> n) {
        if (n == null){
            return -1;
        }else {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return n.height;
        }
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree. Return the head of the new subtree
    TreeNode<T> balance(TreeNode<T> n) {
        //base case
        if (n == null || n.isLeaf()){
            return n;
        }

        //do balancing further down first, save new children
        //n.leftChild = balance(n.leftChild);
        //n.rightChild = balance(n.rightChild);


        TreeNode<T> retnode;
        // Four Cases
        if (balanceFactor(n) >= 2){
            if (balanceFactor(n.rightChild) >= 0){
                //Right Right
                retnode = rotateLeft(n);
            }else{
                //Right Left
                n.rightChild = rotateRight(n.rightChild);
                retnode = rotateLeft(n);
            }
        }else if (balanceFactor(n) <= -2){
            if (balanceFactor(n.leftChild) <= 0){
                //Left Left
                retnode = rotateRight(n);
            }else{
                //Left Right
                n.leftChild = rotateLeft(n.leftChild);
                retnode = rotateRight(n);
            }
        }else{
            retnode = n;
        }

        //check if the root changed
        if (root == n){
            root = retnode;
        }
        return retnode;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(TreeNode<T> n) {
        return height(n.rightChild) - height(n.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        TreeNode<T> lchild = n.leftChild;
        if (lchild == null){
            return n;
        }

        n.leftChild = lchild.rightChild;
        lchild.rightChild = n;
        Updateheight(n);
        Updateheight(lchild);
        return lchild;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {
        TreeNode<T> rchild = n.rightChild;
        if (rchild == null){
            return n;
        }

        n.rightChild = rchild.leftChild;
        rchild.leftChild = n;
        Updateheight(n);
        Updateheight(rchild);
        return rchild;
    }
}
