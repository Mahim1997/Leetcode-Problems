class Solution {
    private int fib_dp(int n){
        if(n <= 1)
            return n;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    
	public int fib(int n){
        if(n <= 1)
            return n;
        
        int f_nMinus2 = 0; // f_0 = 0
        int f_nMinus1 = 1; // f_1 = 1
        int f_n = 2;
        
        for(int i=2; i<=n; i++){
            f_n = f_nMinus1 + f_nMinus2;
            f_nMinus2 = f_nMinus1;
            f_nMinus1 = f_n;
        }
        
        return f_n;
	}
}