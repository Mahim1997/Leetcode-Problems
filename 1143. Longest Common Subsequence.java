class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        
        int[][] dp = new int[n + 1][m + 1];
        
        // no base cases.
        
        // initially all should be zeros.
        for(int []arr: dp){
            for(int x: arr){
                x = 0;
            }
        }
        
        // 
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                char c1 = text1.charAt(i - 1); // offfsetting by 1
                char c2 = text2.charAt(j - 1);
                
                int value_put = 0;
                if(c1 == c2){
                    // same condition.
                    value_put = Math.max(dp[i - 1][j - 1] + 1,
                        Math.max(dp[i - 1][j],
                    dp[i][j - 1])
                    );
                }
                else{
                    // different condition.
                    value_put = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                
                dp[i][j] = value_put;
            }
        }
        
        return dp[n][m];
        
    }
}