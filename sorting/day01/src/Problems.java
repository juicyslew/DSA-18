import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
            System.out.println(out[i]);
        }
        System.out.println(" ");
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        PriorityQueue<Integer> maxq = maxPQ();
        PriorityQueue<Integer> minq = minPQ();
        int i = 0;
        while(i < inputStream.length){
            //add in value
            maxq.offer(inputStream[i]);
            // equalize queue lengths
            if (i > 1) {
                //maxq.offer(minq.poll());
                minq.offer(maxq.poll());
            }
            System.out.println(maxq.size() + ", " + minq.size());
            if (maxq.size() > minq.size()+1) {
                minq.offer(maxq.poll());
            }

            //return median
            if (maxq.size() == minq.size()){
                runningMedian[i] = (minq.peek() + maxq.peek())/2.0;
            }else{
                runningMedian[i] = maxq.peek();
            }

            System.out.println(runningMedian[i]);
            i++;
        }
        System.out.println(" ");

        return runningMedian;
    }

}
