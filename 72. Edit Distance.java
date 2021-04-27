class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.length() == 0)
            return word2.length();
        if(word2.length() == 0)
            return word1.length();
        
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        
        dp[0][0] = 0;
        
        // row wise
        for(int i=1; i<dp.length; i++){
            dp[i][0] = dp[i-1][0] + 1;
        }
        
        // col wise
        for(int j=1; j<dp[0].length; j++){
            dp[0][j] = dp[0][j-1] + 1;
        }
        
        if(dp.length == 1)
            return dp[0][dp[0].length-1];
        if(dp[0].length==1)
            return dp[dp.length-1][0];
        
        // dp.
        int subPenalty = 0;
        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    subPenalty = 0;
                }else{
                    subPenalty = 1;
                }
                dp[i][j] = Math.min(dp[i-1][j-1]+subPenalty, Math.min(dp[i-1][j]+1, dp[i][j-1]+1));
            }
        }
        
        return dp[dp.length-1][dp[0].length-1];
        
    }
}