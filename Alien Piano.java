import java.util.*;

public class Solution {

    private void solve() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] result = new int[T];

        for (int i = 0; i < T; i++) {
            int K = sc.nextInt();
            int[] song = new int[K];
            for (int j = 0; j < K; j++) {
                song[j] = sc.nextInt();
            }
            result[i] = countMinRuleBreaks(song);
        }
        for (int t = 0; t < T; t++) {
            System.out.println("Case #" + (t + 1) + ": " + result[t]);
        }
    }

    private int countMinRuleBreaks(int[] song) {
        int K = song.length;
        int[][] dp = new int[K][4];
        // dp[i][j] is the min number of rule breaks to encode song[0...i] such that
        // song[i] is converted to note j on the alien piano.
        // notes on the alien piano: 0 < 1 < 2 < 3
        for (int i = 1; i < K; i++) {
            if (song[i - 1] == song[i]) {
                dp[i] = dp[i - 1];
            }
            else if (song[i] > song[i - 1]) {
                dp[i][1] = dp[i - 1][0];
                dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]);
                dp[i][3] = min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]);
                dp[i][0] = 1 + min(dp[i - 1]);
            }
            else {
                dp[i][0] = min(dp[i - 1][1], dp[i - 1][2], dp[i - 1][3]);
                dp[i][1] = min(dp[i - 1][2], dp[i - 1][3]);
                dp[i][2] = dp[i - 1][3];
                dp[i][3] = 1 + min(dp[i - 1]);
            }
        }
        return min(dp[K - 1]);
    }

    public static void main(String[] args) {
        new Solution().solve();
    }

    public static int min(int... rest) {
        int result = rest[0];
        for (int i = 1; i < rest.length; i++) {
            result = Math.min(result, rest[i]);
        }
        return result;
    }