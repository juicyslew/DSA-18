public class DiceRollSum {

    // Runtime: O(n*6)
    // Space: O(n)

    private static int diceRollRecurs(int i, int[] DP){
        if (i == 0) return 1;

        if (DP[i] != -1) return DP[i];

        int add = 0;
        for (int sub = 1; sub <= 6; sub++){
            if (i - sub >= 0){
                add += diceRollRecurs(i-sub,DP);
            }
        }
        DP[i] = add;
        return add;
    }

    public static int diceRollSum(int N) {
        int[] DP = new int[N+1];
        for(int i = 0; i < DP.length; i++){
            DP[i] = -1;
        }

        return diceRollRecurs(N, DP);
    }






}
