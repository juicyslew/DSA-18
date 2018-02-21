import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        ArrayList<HashMap<Float, Integer>-> Dists = new ArrayList<HashMap<Float, Integer>>(points.length);

        for (int i = 0; i < points.length; i++){
            //float[] hashfloat = new float[points.length-1];
            HashMap<Float, Integer> tmp = new HashMap<>();
            for (int j = 0; j < points.length; j++){
                if (i == j){
                    continue;
                }
                float key = dist(points[i], points[j]);
                Integer val = tmp.get(key);
                if (val == null){
                    val = 0;
                }
                tmp.put(key, val+1);
            }
            Dists.add(tmp);
        }
        int boomerangs = 0;
        for (HashMap<Float, Integer> map : Dists) {
            for (Integer num : map.values()){
                boomerangs += num*(num-1);
            }
        }
        return boomerangs;
    }

    public static float dist(int[] a, int[] b){
        return (float)Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }
}

