import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            //HashMap<Integer, Integer> costmap = new HashMap<>();
            IndexPQ<Integer> costqueue = new IndexPQ<>(G.numberOfV());
            for (int i : G.vertices) {
                costqueue.insert(i, Integer.MAX_VALUE);
            }

            Set<Integer> visited = new HashSet<>();

            int cost = 0;

            int first_node = costqueue.delMin();
            visited.add(first_node);
            for (Edge edge : G.edges(first_node)){
                //if (edge.other(first_node) < );
                try{
                    costqueue.decreaseKey(edge.other(first_node), edge.weight());
                }catch (Exception e){ }
            }

            while (!costqueue.isEmpty()){
                cost += costqueue.peekMin();
                int curr_node = costqueue.delMin();
                for (Edge edge : G.edges(curr_node)){
                    try{
                        costqueue.decreaseKey(edge.other(curr_node), edge.weight());
                    }catch(Exception e){ }
                }
            }


            return cost;
        }
    }

