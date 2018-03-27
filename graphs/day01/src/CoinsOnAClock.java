import java.util.*;

public class CoinsOnAClock {

    private static void backtrack(int spot, char[] curr, HashMap<Character, Integer> counts, List<char[]> coinArray) {
        boolean success = true;
        if (curr[spot] == 'x') {
            for (char cointype : counts.keySet()) {
                if (counts.get(cointype) > 0) {
                    counts.replace(cointype, counts.get(cointype) - 1);
                    curr[spot] = (cointype);

                    int coinval = coinvalFinder(cointype);

                    spot += coinval;
                    spot = Math.floorMod(spot, curr.length);
                    backtrack(spot, curr, counts, coinArray);
                    spot -= coinval;
                    spot = Math.floorMod(spot, curr.length);
                    curr[spot] = 'x';
                    counts.replace(cointype, counts.get(cointype) + 1);
                }
            }
        }else{
            for (char cointype : counts.keySet()) {
                if (counts.get(cointype) > 0){
                    success = false;
                    break;
                }
            }
            if (success){
                coinArray.add(curr.clone());
            }
        }
            /*curr.addLast(u);
            unused.remove(u);
            backtrack(curr, unused, subsets);
            unused.add(u);
            curr.removeLast();
        }*/
    }
    public static int coinvalFinder(char id){
        switch(id){
            case 'p': return 1;
            case 'n': return 5;
            case 'd': return 10;
        }
        throw new AssertionError("Invalid Input to coinvalFinder");
    }

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {

        List<char[]> result = new ArrayList<>();
        char[] start = new char[hoursInDay];
        HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
        for (int i = 0; i < hoursInDay; i++){
            start[i] = 'x';
        }
        counts.put('p', pennies);
        counts.put('n', nickels);
        counts.put('d', dimes); //new int[]{pennies, nickels, dimes};
        backtrack(0, start, counts, result);

        return result;
    }
}
