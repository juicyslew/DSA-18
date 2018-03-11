import java.util.HashMap;

public class Problems {

    public static int leastSum(int[] A) {
        int[] vals = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < A.length; i++){
            vals[A[i]] += 1;
        }
        int a1 = 0;
        int a2 = 0;
        int j = 0;
        int count = A.length -1;
        while (j < vals.length){
            while (vals[j] > 0){
                if (count % 2 == 1){
                    a1 += j * Math.pow(10, Math.floor(count/2));
                }else{
                    a2 += j * Math.pow(10, Math.floor(count/2));
                }
                vals[j] -= 1;
                count--;
            }
            j++;
        }
        return a1 + a2;
    }
}
