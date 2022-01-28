class Solution {
    private String s1;
    private int len1;
    
    private String s2;
    private int len2;
    
    private String dest;
    private int lenDest;
    
    private int[][] cache;
    
    private int UNASSIGNED = -10000;
    private int TRUE = 1;
    private int FALSE = 2;
    
    private void initialize(String s1, String s2, String s3){
        this.s1 = s1;
        this.s2 = s2;
        this.dest = s3;
        
        this.len1 = this.s1.length();
        this.len2 = this.s2.length();
        this.lenDest = this.dest.length();
        
        this.cache = new int[this.len1][this.len2];
        // for(int[][] _2D: this.cache){
        for(int[] _1D: this.cache){
            Arrays.fill(_1D, UNASSIGNED);
        }
        // }
    }
    
    public boolean isInterleave(String s1, String s2, String s3) {
        // initialize data structures
        this.initialize(s1, s2, s3);
        
        // Edge cases
        if(s1.isEmpty()){
            return s2.equals(s3);
        }
        if(s2.isEmpty()){
            return s1.equals(s3);
        }
        if((this.len1 + this.len2) != this.lenDest){
            return false;
        }
        
        // return bottomUP();
        return bottomUPSpaceOptimized();
        
        // return (dfs(0, 0) == TRUE) ? true : false; // prefix-wise OR suffix-wise ??
    }
    
    private boolean bottomUPSpaceOptimized(){
        int lastRow = this.len1;
        int lastCol = this.len2;
        
        boolean[] currentArr = new boolean[this.len2 + 1];
        boolean[] nextArr = new boolean[this.len2 + 1];
        
        nextArr[lastCol] = true;
        for(int col=lastCol-1; col>=0; col--){
            int idxDest = col + lastRow;
            if(this.s2.charAt(col) == this.dest.charAt(idxDest)){
                // dp[lastRow][col] = dp[lastRow][col+1];
                nextArr[col] = nextArr[col + 1];
            }else{
                // dp[lastRow][col] = false;
                nextArr[col] = false;
            }
        }
        
        for(int row=lastRow-1; row>=0; row--){
            // set the last column of currentArr
            int idxDest = lastCol + row;
            if(this.s1.charAt(row) == this.dest.charAt(idxDest)){
                currentArr[lastCol] = nextArr[lastCol];
            }else{
                currentArr[lastCol] = false;
            }
            
            // iterate over all columns
            for(int col=lastCol-1; col>=0; col--){

                boolean ansIdx1 = false;
                boolean ansIdx2 = false;
                
                idxDest = row + col;
                if(this.s1.charAt(row) == this.dest.charAt(idxDest)){
                    // ansIdx1 = dp[row+1][col];
                    ansIdx1 = nextArr[col];
                }
                if(this.s2.charAt(col) == this.dest.charAt(idxDest)){
                    // ansIdx2 = dp[row][col+1];
                    ansIdx2 = currentArr[col+1];
                }
                
                boolean ans = ansIdx1 | ansIdx2;
                // dp[row][col] = ans;
                currentArr[col] = ans;
                
            }
            
            // interchange arrays
            for(int col=lastCol-1; col>=0; col--){
                nextArr[col] = currentArr[col];
            }
        }
        
        return currentArr[0];
    }
    
    
    private boolean bottomUP(){
        boolean[][] dp = new boolean[this.len1+1][this.len2+1];
    
        // row -> s1, col -> s2
        int lastRow = this.len1;
        int lastCol = this.len2;

        // Base cases.
        dp[lastRow][lastCol] = true;
    
        for(int row=lastRow-1; row>=0; row--){
            int idxDest = row + lastCol;
            if(this.s1.charAt(row) == this.dest.charAt(idxDest)){
                dp[row][lastCol] = dp[row+1][lastCol];
            }else{
                dp[row][lastCol] = false;
            }
        }
        
        for(int col=lastCol-1; col>=0; col--){
            int idxDest = col + lastRow;
            if(this.s2.charAt(col) == this.dest.charAt(idxDest)){
                dp[lastRow][col] = dp[lastRow][col+1];
            }else{
                dp[lastRow][col] = false;
            }
        }
    
        // iteration.
        for(int row=lastRow-1; row>=0; row--){
            for(int col=lastCol-1; col>=0; col--){
                boolean ansIdx1 = false;
                boolean ansIdx2 = false;
                
                int idxDest = row + col;
                if(this.s1.charAt(row) == this.dest.charAt(idxDest)){
                    ansIdx1 = dp[row+1][col];
                }
                if(this.s2.charAt(col) == this.dest.charAt(idxDest)){
                    ansIdx2 = dp[row][col+1];
                }
                
                boolean ans = ansIdx1 | ansIdx2;
                dp[row][col] = ans;
            }
        }
        
        return dp[0][0];
    }

    private int dfs(int idx1, int idx2){
        // System.out.println("CALL FOR idx1 = " + idx1 + ", idx2 = " + idx2);
        
        // Base cases
        int idxDest = idx1 + idx2;
        if(idx1 == this.len1){
            return (this.s2.substring(idx2, this.len2)
                .equals(
                    this.dest.substring(idxDest, this.lenDest)
                )) ? TRUE : FALSE;
        }
        if(idx2 == this.len2){
            return (this.s1.substring(idx1, this.len1)
                .equals(
                    this.dest.substring(idxDest, this.lenDest)
                )) ? TRUE : FALSE;
        }
        
        // check cache
        if(this.cache[idx1][idx2] != UNASSIGNED){
            return this.cache[idx1][idx2];
        }
        
        // recursion
        int ans = FALSE;
        
        int ans1 = FALSE, ans2 = FALSE;
        if(this.s1.charAt(idx1) == this.dest.charAt(idxDest)){
            ans1 = dfs(idx1+1, idx2);
        }
        if(this.s2.charAt(idx2) == this.dest.charAt(idxDest)){
            ans2 = dfs(idx1, idx2+1);
        }
        if((ans1 == TRUE) || (ans2 == TRUE)){
            ans = TRUE;
        }
        
        this.cache[idx1][idx2] = ans;
        return ans;
    }

}








