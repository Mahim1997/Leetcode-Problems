/* Python simple solution
import numpy as np
class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        if n==1 or m==1:
            return 1
        dp = np.ones((m, n))
        dp[0, :] = 1  # rows fill
        dp[:, 0] = 1 # cols fill
        
        for i in range(1, m): # normal case
            for j in range(1, n):
                # Cell(i, j) = LEFT_CELL + TOP_CELL
                dp[i, j] = dp[i-1, j] + dp[i, j-1]
                
        return int(dp[m-1, n-1])
*/

class Solution {
	// Using 1D array instead of 2D array.
    public int uniquePaths(int m, int n) {
        if(n==1 || m==1){ // only one row OR col movement.
            return 1;
        }
        int[] arr = new int[n]; // columns shaped
        
        // fill up with 1 initially.
        Arrays.fill(arr, 1);
        
        // now loop nested.
        for(int row=1; row<m; row++){
            
            for(int col=1; col<n; col++){ // 0th column will not be updated
                arr[col] = arr[col-1] + arr[col];
            }
            
        }

        return arr[n-1];
    }
	
	
	// Using 2D DP table
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        if(n==1 || m==1){ // only one row OR col movement.
            return 1;
        }
        
        // Row fillup
        for(int i=0; i<m; i++){
            dp[i][0] = 1;
        }
        
        //Col fillup
        for(int j=0; j<n; j++){
            dp[0][j] = 1;    
        }   
        
        // Normal.
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        return dp[m-1][n-1];
    }
}