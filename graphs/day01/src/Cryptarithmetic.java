import java.util.*;

public class Cryptarithmetic {

    // Do not modify this function (though feel free to use it)
    public static boolean validSolution(String S1, String S2, String S3, Map<Character, Integer> assignments) {
        return (stringToInt(S1, assignments) + stringToInt(S2, assignments) == stringToInt(S3, assignments))
                && assignments.get(S1.charAt(0)) != 0
                && assignments.get(S2.charAt(0)) != 0
                && assignments.get(S3.charAt(0)) != 0;
    }


    private static Iterable<Integer> randomOrder() {
        List<Integer> l = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(l);
        return l;
    }

    private static int stringToInt(String s, Map<Character, Integer> assignments) {
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            i *= 10;
            i += assignments.get(s.charAt(j));
        }
        return i;
    }

    private static boolean backtrack(String S1, String S2, String Sol, Map<Character, Integer> crypto, LinkedList<Character> charlist){

        if (charlist.isEmpty()){
            return validSolution(S1, S2, Sol, crypto);
        }
        //Iterable<Integer> myOrder = randomOrder();
        for (int i : randomOrder()){
            char mychar = charlist.pop();
            crypto.put(mychar, i);
            if(backtrack(S1, S2, Sol, crypto, charlist)){
                //charlist.push(mychar); //doesn't really matter here
                return true;
            }
            charlist.push(mychar);
        }
        return false;
    }


    public static Map<Character, Integer> solvePuzzle(String S1, String S2, String S3) {
        Map<Character, Integer> crypto = new HashMap<>();
        for (char s : S1.toCharArray()){
            crypto.put(s, -1);
        }
        for (char s : S2.toCharArray()){
            crypto.put(s, -1);
        }
        for (char s : S3.toCharArray()){
            crypto.put(s, -1);
        }
        LinkedList<Character> charlist = new LinkedList<Character>(crypto.keySet());
        if (backtrack(S1, S2, S3, crypto, charlist)){
            return crypto;
        }else{
            throw new AssertionError("There is no solution");
        }
    }
}
