public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        int child1 = 2*i+1;
        int child2 = 2*i+2;
        int correctind = -1;

        if (child1 >= size){
            return;
        }
        if (child2 >= size){
            if (heap[i] < heap[child1]){
                correctind = child1;
            }else{
                return;
            }
        }else{
            if (heap[i] < heap[child1] && heap[i] < heap[child2]){
                if (heap[child1] > heap[child2]){
                    correctind = child1;
                }else {
                    correctind = child2;
                }
            }else if(heap[i] < heap[child1]){
                correctind = child1;
            }else if(heap[i] < heap[child2]){
                correctind = child2;
            }else{
                return;
            }
        }
        int tmp = heap[i];
        heap[i] = heap[correctind];
        heap[correctind] = tmp;
        sink(correctind);
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i= (this.size-1)/ 2 ; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime: n
     * Worst-case runtime: n Log(n)
     * Average-case runtime: n Log(n)
     *
     * Space-complexity: 1
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);

        for (int i=size-1; i>0; i--) {
            System.out.println(i);
            int tmp = heap[0];
            heap[0] = heap[i];
            heap[i] = tmp;
            size--;
            sink(0);
        }

        return heap;
    }
}
