class Solution {
	private int _3variables(int n){
	        if(n == 1)
            return 1;
        
        int f_nMinus1 = 1;
        int f_nMinus2 = 1;
        int f_n = 1;
        
        for(int i=2; i<=n; ++i){
            f_n = f_nMinus1 + f_nMinus2;
            
            f_nMinus2 = f_nMinus1;
            f_nMinus1 = f_n;
        }
        
        return f_n;
	}
	
    public int climbStairs(int n) {
        // f(n) =   f(n-1) // using 1 jump
        //          + f(n-2) // using 2 jumps
        
        // memoization + recursion
        
        /*  Recursion: f(n) = f(n-1) + f(n-2)
            Base case: f(0) = 1, f(1) = 1
        */
        if(n == 1)
            return 1;
        
        int[] memo = new int[n+1];
        for(int i=0; i<memo.length; i++)
            memo[i] = 0;
        memo[0] = 1; // f(0)
        memo[1] = 1; // f(1)
        return f(n, memo);
    }
    private int f(int n, int[] memo){
        if(memo[n] != 0)
            return memo[n];
        
        // recursive.
        if(n == 0 || n == 1)
            return memo[n];
        int ans = f(n-1, memo) + f(n-2, memo);
        memo[n] = ans;
        return ans;
    }
}