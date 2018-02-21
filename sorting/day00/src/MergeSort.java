
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    private InsertionSort inssort = new InsertionSort();
    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: n log(n)
     * Worst-case runtime: n log(n)
     * Average-case runtime: n log(n)
     *
     * Space-complexity: n
     */
    @Override
    public int[] sort(int[] array) {
        int size = array.length / 2;
        int[] l1 = new int[size];
        int[] l2 = new int[array.length - size];
        for (int i = 0; i < size; i++){
            l1[i] = array[i];
        }
        for (int i = 0; i < array.length-size; i++){
            l2[i] = array[i+size];
        }
        if (l1.length > INSERTION_THRESHOLD) {
            l1 = sort(l1);
        }else{
            l1 = inssort.sort(l1);
        }

        if (l2.length > INSERTION_THRESHOLD){
            l2 = sort(l2);
        }else{
            l2 = inssort.sort(l2);
        }
        return merge(l1, l2);
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int ind1 = 0;
        int ind2 = 0;
        int[] res = new int[a.length+b.length];
        while(true){
            int resind = ind1+ind2;
            if (resind >= a.length + b.length){
                break;
            }
            if (ind1 >= a.length) {
                res[resind] = b[ind2];
                ind2++;
                continue;
            }
            if (ind2 >= b.length) {
                res[resind] = a[ind1];
                ind1++;
                continue;
            }

            if (a[ind1] > b[ind2]) {
                res[resind] = b[ind2];
                ind2++;
            }else{
                res[resind] = a[ind1];
                ind1++;
            }
        }
        return res;
    }

}
