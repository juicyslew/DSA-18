import static java.lang.Math.floor;

public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {
        float multiplier = .5f;
        float colpoint = nums.length*multiplier;
        int r = 0;
        while (true){
            int dir = peakOneD((int)floor(colpoint), nums);
            if (dir == 0){
                return (int)floor(colpoint);
            }else{
                multiplier *= .5f;
                colpoint += nums.length * multiplier * dir;
            }
        }
    }

    public static int[] findTwoDPeak(int[][] nums) {
        int xlo = 0;
        int ylo = 0;
        int xhi = nums[0].length;
        int yhi = nums.length;
        int[] answer = findTwoDPeak(nums, xlo, ylo, xhi, yhi);
        //flip the answer because the code answers check in the other format

        return new int[]{answer[1], answer[0]};
    }

    public static int[] findTwoDPeak(int[][] nums, int xlo, int ylo, int xhi, int yhi){
        int xmid = (int) Math.floor((xhi+xlo)/2.0f);
        int ymid = (int) Math.floor((yhi+ylo)/2.0f);
        if ((xhi - xlo) == 1 && (yhi - ylo) == 1){

        }
        if ((xhi - xlo) > (yhi - ylo)){
            int ytest = maxYIndex(xmid, ylo, yhi, nums);
            int xdir = peakX(xmid, ytest, nums);
            if (xdir == 0){
                return new int[]{xmid, ytest};
            }else if (xdir == -1){
                return findTwoDPeak(nums, xlo, ylo, xmid, yhi);
            }else{
                return findTwoDPeak(nums, xmid+1, ylo, xhi, yhi);
            }
        }else{
            int xtest = maxXIndex(ymid, xlo, xhi, nums);
            int ydir = peakY(xtest, ymid, nums);
            if (ydir == 0){
                return new int[]{xtest, ymid};
            }else if (ydir == -1){
                return findTwoDPeak(nums, xlo, ylo, xhi, ymid);
            }else{
                return findTwoDPeak(nums, xlo, ymid+1, xhi, yhi);
            }
        }
        //return null;
    }

}
