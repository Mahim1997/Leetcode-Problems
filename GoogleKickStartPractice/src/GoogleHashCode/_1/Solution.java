/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GoogleHashCode._1;

import java.util.Scanner;

/**
 *
 * @author MAHIM
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = sc.nextInt();
            }

            int ans = getCostIterations(arr);
            System.out.println("Case #" + t + ": " + ans);
        }

    }

    private static int getCostIterations(int[] arr) {
        int n = arr.length;
        int cost = 0;
        for (int i = 0; i <= (n - 2); i++) {
            // j = index of min value between i .... len(L)
            int j = getMinIndex(arr, i, n);

            reverse(arr, i, j);
            cost += (j - i + 1);
        }

        return cost;
    }

    private static int getMinIndex(int[] arr, int idx, int n) {
        int minIdx = idx;
        int minEl = arr[minIdx];

        for (int i = idx; i < n; i++) {
            if (arr[i] < minEl) {
                minEl = arr[i];
                minIdx = i;
            }
        }

        return minIdx;
    }

    private static void reverse(int[] arr, int idx1, int idx2) {
        int p1 = idx1, p2 = idx2;
        while (p1 < p2) {
            // swap
            int temp = arr[p1];
            arr[p1] = arr[p2];
            arr[p2] = temp;

            // move
            p1++;
            p2--;
        }
    }
}
