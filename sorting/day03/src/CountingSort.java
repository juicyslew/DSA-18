import java.util.ArrayList;

public class CountingSort {

    /**
     * Use counting sort to sort positive integer array A.
     * Runtime: O(n + k)
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int max = 0;
        for (int i : A){
            if (i > max){
                max = i;
            }
        }
        int[] countingarr = new int[max+1];
        for (int i = 0; i <= max; i++){
            countingarr[i] = 0;
        }
        for (int i : A) {
            countingarr[i] = countingarr[i] + 1;
        }

        int ind = 0;
        int head = 0;
        while(head <= max){
            if (countingarr[head] > 0) {
                countingarr[head] = countingarr[head] - 1;
                A[ind] = head;
                ind++;
            }else{
                head += 1;
            }
        }
    }

}
