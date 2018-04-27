public class LongestIncreasingSubsequence {

    // Runtime: O(n^2)
    // Space: O(n)

    private static int LISRecurs(int[] A, int i, int[] DP){
        if (i == 0) return 0;
        if (DP[i] != -1) return DP[i];
        int best = 0;
        for (int j = 0; j < i; j++){
            if (i >= A.length || A[j] < A[i]) {
                int test = LISRecurs(A, j, DP) + 1;
                if (test > best){
                    best = test;
                }
            }
        }
        DP[i] = best;
        return best;
    }


    public static int LIS(int[] A) {
        int[] DP = new int[A.length + 1];
        for (int i = 0; i < DP.length; i++) {
            DP[i] = -1; // set a special empty value
        }
        return LISRecurs(A, A.length, DP);
    }


    // For Reference

    /*private static int coinsNeededRecurs(int i, int[] denominations, int[] DP) {
        // base case
        if (i == 0) return 0;
        // have we already solved this subproblem
        if (DP[i] != -1) return DP[i];
        // DP[i] = min(DP[j] + 1) for j in denominations
        int answer = Integer.MAX_VALUE;
        for (int j : denominations)
            if (j <= i) answer = Math.min(coinsNeededRecurs(i - j, denominations, DP) + 1, answer);
        // store our answer and return it
        DP[i] = answer;
        return answer;
    }

    // Given N = 30, and coinDenominations = [1, 10, 25], returns 3
    public static int minCoinsNeeded(int N, int[] coinDenominations) {
        int[] DP = new int[N + 1];
        for (int i = 0; i < DP.length; i++) {
            DP[i] = -1; // set a special empty value
        }
        return coinsNeededRecurs(N, coinDenominations, DP);
    }*/
}