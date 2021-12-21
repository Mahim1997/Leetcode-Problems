class Solution {
    public int numWays(int n, int k) {
        if(n == 1)
            return k;
        if(n == 2)
            return k*k;
        
        int dp_i_minus_2 = k;
        int dp_i_minus_1 = k*k;
        int dp_i = dp_i_minus_1;
        
        /*
            dp(i) = 
                    Different Color i, i-1
                    dp(i - 1) * (k - 1)
                    
                    Same Color i, i-1
                ==> Different Color i-1, i-2
                    dp(i - 2) * (k - 1)
                
            Overall, dp(i) = (k - 1)[dp(i - 1) + dp(i - 2)]
        */
        
        for(int i=3; i<=n; i++){
            dp_i = dp_i_minus_1 + dp_i_minus_2;
            dp_i = (k - 1)*dp_i;
            
            dp_i_minus_2 = dp_i_minus_1;
            dp_i_minus_1 = dp_i;
        }
        
        return dp_i;
    }
}