package _3_h_index;

import java.util.*;

public class Solution {

    private static int getHIndex(Map<Integer, Integer> counts) {        
        // List<Integer> keys = new ArrayList<>(counts.keySet());
        // keys.sort((x1, x2) -> (x2 - x1)); // descending order
        // int numKeys = keys.size();
        
        int countCuml = 0;
        int ans = Integer.MIN_VALUE;
        // for(int i=0; i<numKeys; i++){
        for(int num: counts.keySet()){
            // int num = keys.get(i);
            int count = counts.get(num);
            countCuml += count;
            
            if(num >= countCuml)
                ans = Math.max(ans, countCuml);
            
            if(countCuml >= num)
                ans = Math.max(ans, num);
            
        }
        
        return ans;
    }

    public static int[] getHIndexScore(int[] citationsPerPaper) {
        int[] hIndex = new int[citationsPerPaper.length];

//        Map<Integer, Integer> mapCounts = new HashMap<>();
        Map<Integer, Integer> mapCounts = new TreeMap<>(
            (x1, x2) -> (x2 - x1)
        );

        // TODO: Add logic to calculate h-index score for each paper
        for (int i = 0; i < hIndex.length; i++) {
            mapCounts.put(citationsPerPaper[i], mapCounts.getOrDefault(citationsPerPaper[i], 0) + 1);
            hIndex[i] = getHIndex(mapCounts);
        }

        return hIndex;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Get number of test cases in input
        int testCaseCount = scanner.nextInt();
        // Iterate through test cases
        for (int tc = 1; tc <= testCaseCount; ++tc) {
            // Get number of papers for this test case
            int paperCount = scanner.nextInt();
            // Get number of citations for each paper
            int[] citations = new int[paperCount];
            for (int p = 0; p < paperCount; ++p) {
                citations[p] = scanner.nextInt();
            }
            // Print h-index score after each paper in this test case
            System.out.print("Case #" + tc + ":");
            for (int score : getHIndexScore(citations)) {
                System.out.append(" ").print(score);
            }
            System.out.println();
        }
    }
}
