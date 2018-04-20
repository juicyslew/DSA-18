import java.util.*;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        Arrays.sort(occupied);
        PriorityQueue<Integer> SpaceRegions = new PriorityQueue<>();

        int prev = occupied[0];
        for (int i : occupied){
            if (Math.abs(i - prev) <= 1){
            }else{
                SpaceRegions.add(i-prev-1);
            }
            prev = i;
        }
        int takenspaces = occupied.length;

        while (SpaceRegions.size() > (M-1)){//(int i = 0; i < SpaceRegions.size() - (M-1); i++){
            takenspaces += SpaceRegions.poll();
        }

        return takenspaces;
    }
}