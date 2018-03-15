import java.util.NoSuchElementException;


public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    public static final boolean LEFT = false;
    public static final boolean RIGHT = true;

    public boolean blackSwing = false;
    public boolean AlreadyReplaced = false;

    private boolean isRed(TreeNode x) {
        return x != null && x.color == RED;
    }

    private boolean isBlack(TreeNode x) {
        return x != null && x.color == BLACK;
    }

    // ====================================
    //            Insertion Code
    // ====================================


    public boolean add(T key) {
        super.add(key);
        root.color = BLACK;
        return true;
    }


    // make a left-leaning link lean to the right
    TreeNode<T> rotateRight(TreeNode<T> h) {
        if (h == null) {
            return h;
        }
        TreeNode<T> lc = h.leftChild;
        if (lc == null){
            return h;
        }
        boolean hcol = isRed(h);
        h.color = isRed(lc);
        lc.color = hcol;

        h.leftChild = lc.rightChild;
        lc.rightChild = h;
        return lc;
    }

    // make a right-leaning link lean to the left
    TreeNode<T> rotateLeft(TreeNode<T> h) {
        if (h == null) {
            return h;
        }
        TreeNode<T> rc = h.rightChild;
        if (rc == null){
            return h;
        }
        boolean hcol = isRed(h);
        h.color = isRed(rc);
        rc.color = hcol;

        h.rightChild = rc.leftChild;
        rc.leftChild = h;
        return rc;
    }

    // flip the colors of a TreeNode and its two children
    TreeNode<T> flipColors(TreeNode<T> h) {
        h.color = !isRed(h);

        if(h.leftChild != null){
            h.leftChild.color = !isRed(h.leftChild);
        }

        if(h.rightChild != null){
            h.rightChild.color = !isRed(h.rightChild);
        }
        return h;
    }


    /**
     * fix three cases:
     *   1. h.right is red
     *   2. h.left is red, and h.left.left is red
     *   3. h.left and h.right are red
     * return balanced node
     */
    private TreeNode<T> balance(TreeNode<T> h) {

        if (isRed(h.leftChild)){
            if(isRed(h.rightChild)){
                h = flipColors(h);
                return h;
            }
            if (isRed(h.leftChild.leftChild)){
                h = flipColors(rotateRight(h));
                return h;
            }
        }

        if(!isRed(h.leftChild) && isRed(h.rightChild) ){
            h = rotateLeft(h);
            return h;
        }

        return h;
    }


    /**
     * Recursively insert a new node into the BST
     * Runtime: TODO
     */
    @Override
    TreeNode<T> insert(TreeNode<T> h, T key) {
        h = super.insert(h, key);
        h = balance(h);
        return h;
    }


    // ====================================
    //            Deletion Code
    // ====================================


    /**
     * Removes the specified key from the tree
     * (if the key is in this tree).
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean delete(T key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return false;

        // if both children of root are black, set root to red
        if (!isRed(root.leftChild) && !isRed(root.rightChild))
            root.color = RED;
        blackSwing = false;
        AlreadyReplaced = false;
        //root =
        delete(root, key, false);
        blackSwing = false;
        AlreadyReplaced = false;
        size--;
        if (!isEmpty()) root.color = BLACK;
        return true;
    }

    // delete the key-value pair with the given key rooted at h
    TreeNode<T> delete(TreeNode<T> h, T key, boolean found) {
        //ERROR CHECK
        if (h == null) {
            System.out.println("something went wrong");
            return null;
        }
        int cmp = key.compareTo(h.key);

        boolean searchdir = LEFT;

        TreeNode<T> replacer;

        //FIND
        if(found){
            if (!h.isLeaf()) {
                replacer = delete(h.leftChild, key, true);
            }else{
                return NodeCopy(h);
            }
        }else if (cmp < 0) {
            // GO LEFT
            replacer = delete(h.leftChild, key, false);
            searchdir = LEFT;
        } else if (cmp > 0) {
            // GO RIGHT
            replacer = delete(h.rightChild, key, false);
            searchdir = RIGHT;
        } else if (!h.isLeaf()) {
            // IF FOUND
            // NO RIGHT CHILD MEANS I AM EITHER AT LEAF OR ONE LINK AWAY FROM LEAF
            if (h.rightChild == null) {
                TreeNode<T> tmp = NodeCopy(h.leftChild);
                h.leftChild = null;
                if (h.key == root.key){
                    Replace(h, tmp);
                    h = tmp;
                    root = h;
                }
                AlreadyReplaced = true;
                return tmp;
            }else{
                //OTHERWISE CALL THE FUNCTION AGAIN WITH FOUND == TRUE
                replacer = delete(h.rightChild, key, true);
                searchdir = RIGHT;
            }
            //node = delete(h.rightChild, key, true);
        }else{
            if (h.key == root.key){
                root = null;
            }
            return NodeCopy(h);
        }



        // RETURNING BACK UP, SEES KEY, DELETES AND REPLACES
        if (replacer.key != key) {
            if (searchdir == RIGHT) {
                if (h.rightChild.key == key) {
                    Replace(h.rightChild, replacer);
                    h.rightChild = replacer;
                }
            } else {
                if (h.leftChild.key == key) {
                    Replace(h.leftChild, replacer);
                    h.leftChild = replacer;
                }
            }
            if (h.key == root.key && h.key == key){
                Replace(h, replacer);
                h = replacer;
                root = h;
            }
        }

        TreeNode<T> pchild = h.Child(searchdir);
        TreeNode<T> schild = h.Child(!searchdir);

        /*//Remove InfiniLoops
        if (pchild == h && pchild == replacer){
            h.setChild(searchdir, null);
        }*/

        // DELETION BALANCING CASES

        if (pchild.key == replacer.key && !AlreadyReplaced) {
            //ONLY PERFORM THESE IF THIS IS THE REPLACER (the last node that was looked at going downwards)
            AlreadyReplaced = true;
            if (isRed(pchild)) {
                // CASE 1 : RED LEAF
                h.setChild(searchdir, null);
            } else {
                //CASE 2 : BLACK LEAF
                blackSwing = true;
                h.setChild(searchdir, null);
            }
        }

        if (blackSwing) {
            // Note: this doesn't require being replacer, as long as blackSwing has started
            if (isRed(h)) {
                blackSwing = false;
            }
            // SUB-CASES

            if (pchild.key == h.leftChild.key) {
                // SUB 1 & 2: RIGHT CHILD, BLACK & RED PARENT
                TreeNode<T> tmp = rotateLeft(h);
                //Gotta check for root case
                if (h.key == root.key) {
                    root = tmp;
                }
                h.color = isBlack(h) ? RED : BLACK;
            } else {
                if (isBlack(schild)) {
                    // SUB 3 : LEFT CHILD, BLACK SIBLING
                    h.leftChild.color = RED;
                    // If I was red, make me black, but still end the Swing


                    h.color = BLACK;
                } else {
                    // SUB 4 : LEFT CHILD, RED SIBLING
                    h.leftChild.color = BLACK;
                    // Note: it is impossible for my color to be red, because if it were, there would've been a double red before I got here.
                    TreeNode<T> tmp = rotateRight(h);
                    if (h.key == root.key) {
                        root = tmp;
                    }
                }

            }

        }
        return replacer;
    }



    // the smallest key in subtree rooted at x; null if no such key
    /*private TreeNode<T> min(TreeNode<T> x) {
        if (x.leftChild == null) return x;
        else return min(x.leftChild);
    }

    // delete the key-value pair with the minimum key rooted at h
    TreeNode<T> deleteMin(TreeNode<T> h) {
        if (h.leftChild == null) return h.rightChild;
        h.leftChild = deleteMin(h.leftChild);
        return h;
    }

    TreeNode<T> findDownwardSuccessor(TreeNode<T> h){
        if (h.leftChild == null && h.rightChild == null){
            //THIS IS A LEAF, THEREFORE USE THIS TO REPLACE AND THEN BALANCE
            return h;
        }
        if (h.rightChild == null){
            System.out.println("Something Went Wrong");
            //THIS CODE SHOULD NEVER ACTUALLY RUN, BECAUSE IT SHOULD NEVER BE A DOWNWARD SUCCESSOR
            return h;
        }
        //RETURN MIN NODE
        return min(h.rightChild);
    }*/
    /*TreeNode<T> deleteRecurse(TreeNode<T> h) {
        // BASE CASE
        if (h.leftChild == null && h.rightChild == null && isBlack(h)){
            //THIS IS A LEAF, THEREFORE USE THIS TO REPLACE AND THEN ROTATE
            return h;
        }

        TreeNode<T> hmin = min(h);
        if (hmin != h) {
            deleteRecurse(hmin);
        }

        // TODO DELETE NEXT
        if (h.rightChild != null){
            TreeNode<T> replacer = deleteMin(h.rightChild);
            if(isRed(replacer)){
                deleteMin()
                Replace(h, replacer);
                //But wait you have to resolve it's children
            }else{

            }

        }else if(h.leftChild != null) {


        }

        // RETURN LAST

        return h;
    }*/
    /*TreeNode<T> deleteLeaf(TreeNode<T> h, TreeNode<T> hmin){

    }*/

    //Doesn't work because it doesn't switch the parent's left or right child correctly.
    void Replace(TreeNode<T> deleted, TreeNode<T> replacer){
        replacer.rightChild = deleted.rightChild;
        replacer.leftChild = deleted.leftChild;
        replacer.color = deleted.color;
        //AlreadyReplaced = true;
    }

    TreeNode<T> NodeCopy(TreeNode<T> toCopy){
        TreeNode<T> newnode = new TreeNode<T>(toCopy.key, toCopy.color);
        newnode.leftChild = toCopy.leftChild;
        newnode.rightChild = toCopy.rightChild;
        return newnode;
    }



    // ====================================
    //          LLRB Verification
    // ====================================


    // TODO: understand how the following functions can be used to verify a valid LLRB

    public boolean is23() {
        return is23(root);
    }

    // return true if this LLRB is a valid 2-3 tree
    private boolean is23(TreeNode<T> n) {
        if (n == null) return true;
        if (isRed(n.rightChild)) return false;
        if (isRed(n.leftChild) && isRed(n.leftChild.leftChild)) return false;
        return is23(n.rightChild) && is23(n.leftChild);
    }

    public boolean isBalanced() {
        return isBalanced(root) != -1;
    }

    // return -1 if the tree is not balanced. Otherwise, return the black-height of the tree
    private int isBalanced(TreeNode<T> n) {
        if (n == null) return 0;
        int lBalanced = isBalanced(n.leftChild);
        int rBalanced = isBalanced(n.rightChild);
        if (lBalanced == -1 || rBalanced == -1) return -1;
        if (isBlack(n.leftChild)) lBalanced++;
        if (isBlack(n.rightChild)) rBalanced++;
        if (lBalanced != rBalanced) return -1;
        return lBalanced;
    }

}