import java.util.HashMap;

public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        int maxlen = 0;
        int maxval = 0;
        HashMap<Integer, Integer> mapping = new HashMap<>();
        int counter = 0;
        mapping.put(0, -1);
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 1){
                counter += 1;
            }else{
                counter -= 1;
            }
            if(mapping.containsKey(counter)){
                int tmp = i - mapping.get(counter);
                if (tmp > maxlen){
                    maxval = counter;
                    maxlen = tmp;
                }
            }else{
                mapping.put(counter, i);
            }
        }
        int tmp = mapping.get(maxval);
        System.out.println(tmp + " | " +  (tmp+maxlen));
        return new int[]{tmp+1, tmp+maxlen};
    }
}
