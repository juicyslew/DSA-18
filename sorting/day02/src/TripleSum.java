import java.util.HashMap;
import java.util.Map;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        int tmpsum = 0;
        HashMap<Integer, Integer> frequency = new HashMap<>();
        for (int j = 0; j < arr.length; j++){
            int count = frequency.getOrDefault(arr[j], 0) + 1;
            frequency.put(arr[j], count);
        }

        int triplets = 0;

        for (int i = 0; i < arr.length; i++){
            frequency.put(arr[i], frequency.get(arr[i]) - 1);
            tmpsum = sum - arr[i];
            for (Map.Entry<Integer, Integer> entry : frequency.entrySet()){
                if (entry.getKey() == (float)tmpsum/2){
                    int freq1 = frequency.get(entry.getKey());
                    triplets += freq1*(freq1-1);
                }else if (entry.getKey() < (float) tmpsum/2){
                    int freq1 = frequency.get(entry.getKey());
                    int freq2 = frequency.getOrDefault(tmpsum - entry.getKey(), 0);
                    triplets += freq1 * freq2;
                }
            }
        }
        return triplets;
    }
}
