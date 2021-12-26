class Solution {
    public int minCost(int[][] costs) {
        // return bottomUp(costs);
        // return bottomUpConstantSpace(costs);
        return bottomUpConstantSpaceOnlyVariables(costs);
    }
    
    private int bottomUpConstantSpaceOnlyVariables(int[][] costs){
        int nHouses = costs.length;
        
        int red, blue, green;
        int min = Integer.MAX_VALUE;
        
        red = costs[costs.length - 1][0];
        blue = costs[costs.length - 1][1];
        green = costs[costs.length - 1][2];
        
        if(nHouses == 1){
            return Math.min(Math.min(red, blue), green);
        }
        int tempRed, tempBlue, tempGreen;
        
        for(int idx=nHouses-2; idx>=0; --idx){
            tempRed = costs[idx][0] + Math.min(blue, green);
            tempBlue = costs[idx][1] + Math.min(red, green);
            tempGreen = costs[idx][2] + Math.min(red, blue);
        
            // reassign
            red = tempRed;
            blue = tempBlue;
            green = tempGreen;
        }
        
        return Math.min(Math.min(red, blue), green);
    }
    
    private int bottomUpConstantSpace(int[][] costs){
        int nHouses = costs.length;
        int lastIdx = nHouses - 1;
        
        // SINGLE ARRAY, instead of 2D array.
        int[] dp = new int[3]; // red blue green
        
        int min = Integer.MAX_VALUE;
        for(int i=0; i<3; i++){ // last index is actually just the cost.
            dp[i] = costs[lastIdx][i];
            min = Math.min(dp[i], min);
        }
        if(nHouses == 1){
            return min;
        }
        
        for(int idx=nHouses-2; idx>=0; idx--){
            // current idx == red, take min of next idx's blue and green
            int red = costs[idx][0] + Math.min(dp[1], dp[2]);
            // current's blue, next idx's red and green
            int blue = costs[idx][1] + Math.min(dp[0], dp[2]);
            // current's green, next idx's red and blue
            int green = costs[idx][2] + Math.min(dp[0], dp[1]);
            
            // replace into dp' array
            dp[0] = red;
            dp[1] = blue;
            dp[2] = green;
        }
        
        return Math.min(Math.min(dp[0], dp[1]), dp[2]);
    }
    
    private int bottomUp(int[][] costs){
        int nHouses = costs.length;
        int[][] dp = new int[nHouses][3];
        
        // last index ...
        int lastIdx = nHouses - 1;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<3; i++){ // last index is actually just the cost.
            dp[lastIdx][i] = costs[lastIdx][i];
            min = Math.min(dp[lastIdx][i], min);
        }
        
        // fill up from last index.
        if(nHouses == 1){
            return min;
        }
        for(int idx=nHouses-2; idx>=0; idx--){
            // current idx == red, take min of next idx's blue and green
            dp[idx][0] = costs[idx][0] + Math.min(dp[idx + 1][1], dp[idx + 1][2]);
            // current's blue, next idx's red and green
            dp[idx][1] = costs[idx][1] + Math.min(dp[idx + 1][0], dp[idx + 1][2]);
            // current's green, next idx's red and blue
            dp[idx][2] = costs[idx][2] + Math.min(dp[idx + 1][0], dp[idx + 1][1]);
        }
        
        return Math.min(
            Math.min(dp[0][0], dp[0][1]), dp[0][2]
        );
    }
}