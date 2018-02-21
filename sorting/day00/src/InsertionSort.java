
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * TODO
     * Best-case runtime: n
     * Worst-case runtime: n^2
     * Average-case runtime: n^2
     *
     * Space-complexity: 1
     */
    @Override
    public int[] sort(int[] array) {
        int sortind = 1;
        int head = 1;
        while(true){
            if (sortind == 0){
                head++;
                sortind = head;
            }
            //System.out.println("sortind: " + sortind);
            //System.out.println("head: " + head);
            if (head >= array.length){
                break;
            }
            if (array[sortind] < array[sortind -1]){
                int savenum = array[sortind];
                array[sortind] = array[sortind-1];
                array[sortind-1] = savenum;
                sortind--;
            }else{
                head++;
                sortind = head;
            }
        }
        return array;
    }
}
