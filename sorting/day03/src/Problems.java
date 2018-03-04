import java.util.LinkedList;

public class Problems {

    int temp_integer = 96; //for lower case

    static void sortNumsBetween100s(int[] A) {
        for (int i = 0; i < A.length; i++){
            A[i] = A[i] + 100;
        }
        RadixSort.radixSort(A, 5);
        for (int i = 0; i < A.length; i++){
            A[i] = A[i] - 100;
        }
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        LinkedList<String>[] L = new LinkedList[26];
        for (int i = 0; i < 26; i++)
            L[i] = new LinkedList<>();
        for (String str : A) {
            L[getNthCharacter(str, n)].addLast(str);
        }
        int j = 0; // index in A to place numbers
        for (LinkedList<String> list : L) {
            for (String val : list) {
                A[j] = val;
                j++;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        // Calculate the upper-bound for numbers in A
        int w = S[0].length();
        for (int i = 1; i < S.length; i++)
            w = (S[i].length() > w) ? S[0].length() : w;
        for (int i = 0; i < w; i++){
            countingSortByCharacter(S, i);
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }

}
