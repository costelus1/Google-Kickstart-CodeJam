import java.util.*;

public class Solution {

    private void solve() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] result = new int[T];

        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            int K = sc.nextInt();
            List<Interval> intervals = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                int left = sc.nextInt();
                int right = sc.nextInt();
                intervals.add(new Interval(left, right));
            }
            result[i] = countDeploys(intervals, K);
        }
        for (int t = 0; t < T; t++) {
            System.out.println("Case #" + (t + 1) + ": " + result[t]);
        }
    }

    private int countDeploys(List<Interval> intervals, int K) {
        Collections.sort(intervals);
        // how many deploys we need to cover the first interval
        int count = (int) Math.ceil( (double)(intervals.get(0)._right - intervals.get(0)._left) / K);
        // the time until the robot currently deployed is active
        int lastActiveMoment = intervals.get(0)._left + K * count;

        for (Interval i: intervals.subList(1, intervals.size() )) {
            if (i._right > lastActiveMoment) {
                int start = Math.max(i._left, lastActiveMoment);
                int deploys = (int) Math.ceil( (double)(i._right - start) / K);
                count += deploys;
                lastActiveMoment = start + K * deploys;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        new Solution().solve();
    }

    private static class Interval implements Comparable<Interval> {
        private final int _left;
        private final int _right;

        public Interval(int left, int right) {
            _left = left;
            _right = right;
        }

        @Override
        public int compareTo (Interval other) {
            return _left - other._left;
        }
    }
}