import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CryparithmeticTest {

    @Test
    public void one() {
        // One solution is 9910 + 197 = 10107
        System.out.println("Test 1\n");
        Map<Character, Integer> m = Cryptarithmetic.solvePuzzle("FIVE", "SIX", "SEVEN");
        assertTrue(Cryptarithmetic.validSolution("FIVE", "SIX", "SEVEN", m));
        for (char c: m.keySet()){
            String value = m.get(c).toString();
            System.out.println(c + " " + value);
        }
        System.out.println("\n\n");
    }

    @Test
    public void two() {
        System.out.println("Test 2\n");
        Map<Character, Integer> m = Cryptarithmetic.solvePuzzle("SEND", "MORE", "MONEY");
        assertTrue(Cryptarithmetic.validSolution("SEND", "MORE", "MONEY", m));
        for (char c: m.keySet()){
            String value = m.get(c).toString();
            System.out.println(c + " " + value);
        }
        System.out.println("\n\n");
    }

    @Test
    public void three() {
        System.out.println("Test 3\n");
        // This test might be pretty slow, depending on how you wrote your code.
        // One solution is 34498 + 998833 = 1033331
        Map<Character, Integer> m = Cryptarithmetic.solvePuzzle("BLACK", "COFFEE", "THEBEST");
        assertTrue(Cryptarithmetic.validSolution("BLACK", "COFFEE", "THEBEST", m));
        for (char c: m.keySet()){
            String value = m.get(c).toString();
            System.out.println(c + " " + value);
        }
        System.out.println("\n\n");
    }

    @Test
    public void four() {
        // This test will be reealllly slow, unless you did some optimizations (as mentioned in the README)
        // With optimizations, this should run instantly
        // One solution is 24766987 + 1849677 = 26616664
        Map<Character, Integer> m = Cryptarithmetic.solvePuzzle("BENJAMIN", "ZIEMANN", "COOLDUDE");
        assertTrue(Cryptarithmetic.validSolution("BENJAMIN", "ZIEMANN", "COOLDUDE", m));
    }
}
