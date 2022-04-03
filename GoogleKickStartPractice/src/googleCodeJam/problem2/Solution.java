package googleCodeJam.problem2;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {

    private static int[][] getInputs(Scanner sc) {
        int numRows = 3, numCols = 4;
        int[][] arr = new int[numRows][numCols]; // 3 printers, each has 4 attributes
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;

        T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            // read inputs
            int[][] inputs = getInputs(sc);

            int[] ans = solve(inputs);
            printAnswer(t, ans);
        }
    }

    private static int[] solve(int[][] inputs) {
        int numPrinters = inputs.length;        // 3 printers
        int numAttributes = inputs[0].length;   // 4 attributes

        int totalSum = 1000000; // 10^6
        int[] ans = new int[numAttributes];

        // get the min of each attribute
        for (int itrAttr = 0; itrAttr < numAttributes; itrAttr++) {
            ans[itrAttr] = Integer.MAX_VALUE;
            for (int itrPrinters = 0; itrPrinters < numPrinters; itrPrinters++) {
                ans[itrAttr] = Math.min(
                        ans[itrAttr],
                        inputs[itrPrinters][itrAttr]
                );
            }
        }

        // get the sum and check if it is >= 10^6 or not
        int sum = Arrays.stream(ans).sum();

        if (sum < totalSum) {
            return null;
        }

        // else we have to take those until sum == 10^6
        int remainingSum = totalSum;
        for (int i = 0; i < numAttributes; i++) {
            if (ans[i] < remainingSum) {
                // take the whole & decrement remainingSum
                remainingSum -= ans[i];
            } else {
                // take the remaining Sum, make subsequent attributes == 0 AND break
                ans[i] = remainingSum;
                for (int idx = i + 1; idx < numAttributes; idx++) {
                    ans[idx] = 0;
                }
                break;
            }
        }

        return ans;
    }

    private static void printAnswer(int t, int[] ans) {
        System.out.print("Case #" + t + ": ");
        if (ans == null) {
            System.out.print("IMPOSSIBLE");
        } else {
            for (int i = 0; i < ans.length; i++) {
                System.out.print(ans[i] + " ");
            }
        }
        System.out.println("");
    }

}
