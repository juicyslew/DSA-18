import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        LinkedList<Integer> ConstructionZone = new LinkedList<>();
        List<List<Integer>> permutations = new LinkedList<>();
        Set<Integer> BuildingBlocks = new HashSet<Integer>(A);
        backtrack(ConstructionZone, BuildingBlocks, permutations);
        return permutations;
    }

}
