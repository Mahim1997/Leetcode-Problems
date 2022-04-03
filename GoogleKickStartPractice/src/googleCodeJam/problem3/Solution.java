package googleCodeJam.problem3;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();
            int[] arr = new int[N];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = sc.nextInt();
            }

//            int ans = solveBruteForce(arr);
            int ans = solveOptimal(arr);
            System.out.println("Case #" + t + ": " + ans);
        }
    }

    private static int solveOptimal(int[] arr) {
        int n = arr.length;

        // Edge case
        if (n == 1) {
            return 1;
        }
        
        // sort the array
        Arrays.sort(arr);

        int left = 0, right = left + 1;
        int counter = 2; // left == 1, right == 2

        int maxWindow = 0;
        while ((left < n) && (right < n)) {
            boolean canExtendWindow = (arr[right] >= counter);
//            System.out.println("left = " + left + ", right = " + right + ", canExtendWindow = " + canExtendWindow);
            if (canExtendWindow) {
                counter++;
                right++;
            } else {
                counter--;  // decrement the count
                left++;    // slide the window
                // right stays where it is ?
                continue;
            }
            int currentWindow = right - left; // 'right' can't be taken now
            maxWindow = Math.max(maxWindow, currentWindow);
//            System.out.println("currentWindow = " + currentWindow);
        }
        return maxWindow;
    }

    private static int solveBruteForce(int[] arr) {
        // First sort
        Arrays.sort(arr);

        int counter;
        int maxCount = 0;
        for (int i = 0; i < arr.length; i++) {
            counter = 1; // 'from this position, start'
            for (int j = i; j < arr.length; j++) {
                if (counter > arr[j]) { // counter has exceeded current window
//                    counter = 1; // reset counter
                    break;
                }
                maxCount = Math.max(maxCount, counter);
                counter++;
            }
//            maxCount = Math.max(maxCount, counter);
        }
        return maxCount;
    }

}
