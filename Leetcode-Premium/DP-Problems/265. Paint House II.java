class Solution {
    private static int MAX_LIMIT = 10000; // given costs[i][j] MAX = 20
    
    public int minCostII(int[][] costs) {
        // O(nk) runtime, O(k) space.
        int nHouses = costs.length;
        int k = costs[0].length; // k == nColors
        
        int lastIdx = nHouses - 1;
        int[] dp = new int[k];
        
        // Base case.
        int minVal = MAX_LIMIT;
        for(int color=0; color<dp.length; color++){
            dp[color] = costs[lastIdx][color];
            minVal = Math.min(dp[color], minVal);
        }
        
        // Edge case of only one house.
        if(nHouses == 1)
            return minVal;
        
        for(int idx=nHouses-2; idx>=0; idx--){ // O(k)
            // maintain a temp array
            int[] temp = dp.clone(); // copy of array.
            
            // for each color.
            
            // O(k) find these two.
            int min = MAX_LIMIT; //Integer.MAX_VALUE;
            int secondMin = MAX_LIMIT; //Integer.MAX_VALUE;
            int minIdx = 0;
            int secondMinIdx = 0;
            
            // O(k)
            for(int col=0; col<dp.length; col++){
                if(dp[col] < min){
                    secondMin = min; // update second minimums also
                    secondMinIdx = minIdx;
                    
                    min = dp[col];
                    minIdx = col;
                }
                if(dp[col] <= secondMin){
                    if(minIdx != col){
                        secondMin = dp[col];
                        secondMinIdx = col;
                    }
                }
            }
            // System.out.println("idx = " + idx + ", min = " + min + ", secondMin = " + secondMin + ", minIdx = " + minIdx + ", secondMinIdx = " + secondMinIdx);
            
            // O(k) temp array fillup.
            for(int col=0; col<dp.length; col++){
                if(col != minIdx){ // simply choose the minimum
                    temp[col] = min + costs[idx][col];
                }else{ // choose the second minimum
                    temp[col] = secondMin + costs[idx][col];
                }
            }
            
            // reassign dp to temp
            dp = temp;
            
            // printArr(dp); // debug printing
        }
        
        // return the minimum color.
        int minColorValue = Integer.MAX_VALUE;
        for(int col=0; col<dp.length; col++){
            minColorValue = Math.min(minColorValue, dp[col]);
        }
        
        return minColorValue;
        
    }
    
    private void printArr(int[] dp){
        for(int x: dp){
            System.out.print(x + " ");
        }
        System.out.println("");
    }
}