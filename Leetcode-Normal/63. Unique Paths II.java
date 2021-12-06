class Solution {
	
	// Using grid itself
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1){ // can't have first position as obstacle ?
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if(obstacleGrid[m-1][n-1] == 1)
            return 0; // not possible to end up on obstacle
    
        
        boolean obstacle_found = false;
        
        obstacleGrid[0][0] = 1; // safe to assume initial won't be obstacle
        // Rows fillup [COL = 0]
        for(int i=1; i<m; i++){
            if(obstacleGrid[i][0] == 1){
                obstacle_found = true;
            }
            if(obstacle_found){
                obstacleGrid[i][0] = 0;
            }else{
                obstacleGrid[i][0] = 1;
            }
        }
        
        // Cols fillup [ROW = 0]
        obstacle_found = false;
        for(int j=1; j<n; j++){
            if(obstacleGrid[0][j] == 1){
                obstacle_found = true;
            }
            if(obstacle_found){
                obstacleGrid[0][j] = 0;
            }else{
                obstacleGrid[0][j] = 1;
            }
        }

        // Normal checking.
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(obstacleGrid[i][j] == 1){ // obstacle is found, make it 0
                    obstacleGrid[i][j] = 0;
                }else{
                    obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
                }
            }
        }
        
        return obstacleGrid[m-1][n-1];
        
    }
	
	
	// Using 2D array
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if(obstacleGrid[m-1][n-1] == 1)
            return 0; // not possible to end up on obstacle
    
        // dp table obstacle's path will be 0 ?
        int[][] dp = new int[m][n];
        
        boolean obstacle_found = false;
        
        // Rows fillup
        for(int i=0; i<m; i++){
            if(obstacleGrid[i][0] == 1){
                obstacle_found = true;
            }
            if(obstacle_found){
                dp[i][0] = 0;
            }else{
                dp[i][0] = 1;
            }
        }
        
        // Cols fillup
        obstacle_found = false;
        for(int j=0; j<n; j++){
            if(obstacleGrid[0][j] == 1){
                obstacle_found = true;
            }
            if(obstacle_found){
                dp[0][j] = 0;
            }else{
                dp[0][j] = 1;
            }
        }
        
        // Normal case.
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(obstacleGrid[i][j] == 1){ // obstacle is found
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        
        return dp[m-1][n-1];
        
    }
}