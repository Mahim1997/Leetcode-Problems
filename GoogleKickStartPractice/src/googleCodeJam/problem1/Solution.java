package googleCodeJam.problem1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // read inputs
        int T = sc.nextInt();
//        int T = Integer.parseInt(sc.nextLine().trim());
//        int t = 1;

        for (int t = 1; t <= T; t++) {
            int n = sc.nextInt();
            int[] deliciousness = new int[n];
            for (int i = 0; i < n; i++) {
                deliciousness[i] = sc.nextInt();
            }

            int ans = computeMaximumPayingCustomers(deliciousness);
            System.out.println("Case #" + t + ": " + ans);
        }
    }

    private static int computeMaximumPayingCustomers(int[] deliciousness) {
        int n = deliciousness.length;
        int numPeople = 0;

        int low = 0, high = n - 1;
        int maxSoFar = Integer.MIN_VALUE;

        while (low <= high) {
            int lowElement = deliciousness[low], highElement = deliciousness[high];
            int minElement;

            if (lowElement < highElement) {
                // increment low
                minElement = lowElement;
                low++;
            } else {
                // decrement high
                minElement = highElement;
                high--;
            }

            if (minElement >= maxSoFar) {
                maxSoFar = minElement;
                numPeople++;
            }
        }

        return numPeople;
    }
}
