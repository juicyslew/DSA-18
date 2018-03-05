import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        QuickSort quickSort = new QuickSort();
        int[] vals = new int[values.size()];
        for (int i = 0; i < vals.length; i++){
            vals[i] = values.get(i);
        }
        vals = quickSort.sort(vals);
        BinarySearchTree<Integer> bse = new BinarySearchTree<>();

        CreateMinBSE(bse, vals, 0, vals.length);

        return bse;
    }

    public static void CreateMinBSE(BinarySearchTree<Integer> tree, int [] vals, int lo, int hi){
        if ((hi - lo) == 0){
            return;
        }else if((hi-lo)<= 2){
            tree.add(vals[lo]);
            lo++;
            CreateMinBSE(tree, vals, lo, hi);
        }else{
            int newind = (int) Math.floor((hi + lo) / 2);
            tree.add(vals[newind]);
            CreateMinBSE(tree, vals, lo, newind);
            CreateMinBSE(tree, vals, newind+1, hi);
        }
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
