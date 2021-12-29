class Solution {
    public int minDistance(String word1, String word2) {
        // return bottomUp(word1, word2);
        return bottomUpConstantSpace(word1, word2);
    }
    
    private int bottomUpConstantSpace(String word1, String word2){
        // make sure, word1 is always the smaller one
        if(word1.length() > word2.length()){
            String temp = word1;
            word1 = word2;
            word2 = temp;
        }
        
        int len1 = word1.length();
        int len2 = word2.length();
        
        // base case, if both are empty strings.
        if((len1 == 0) && (len2 == 0))
            return 0;
        // base case, word1 is empty
        if(len1 == 0){
            return len2;
        }
        
        int nRows = len1 + 1; // word1 -> rows
        int nCols = len2 + 1; // word2 -> cols
        
        // maintain two rows
        int[] prevRow = new int[nCols];
        int[] currentRow = new int[nCols];
        
        // base case
        for(int col=0; col<nCols; col++){
            prevRow[col] = col;
        }
        
        
        for(int row=1; row<nRows; row++){
            currentRow[0] = row;

            for(int col=1; col<nCols; col++){
                // check if equal => words are at 0 indexed
                if(word1.charAt(row - 1) == word2.charAt(col - 1)){
                    currentRow[col] = prevRow[col - 1];
                }else{
                    currentRow[col] = Math.min(
                        Math.min(prevRow[col-1], prevRow[col]),
                        currentRow[col-1]) + 1;
                }
            }
            
            // swap back
            for(int col=0; col<nCols; col++){
                prevRow[col] = currentRow[col];
            }
        }
        
        return currentRow[nCols - 1];
    }
    
    private int bottomUp(String word1, String word2){
        int len1 = word1.length();
        int len2 = word2.length();
        
        int nRows = len1 + 1; // word1 -> rows
        int nCols = len2 + 1; // word2 -> cols
        
        int[][] dp = new int[nRows][nCols];
        
        for(int row=0; row<nRows; row++){
            dp[row][0] = row;
        }
        for(int col=0; col<nCols; col++){
            dp[0][col] = col;
        }
        
        for(int row=1; row<nRows; row++){
            for(int col=1; col<nCols; col++){
                // check if equal => words are at 0 indexed
                if(word1.charAt(row - 1) == word2.charAt(col - 1)){
                    dp[row][col] = dp[row - 1][col - 1];
                }else{
                    dp[row][col] = Math.min(
                        Math.min(dp[row-1][col-1], dp[row-1][col]),
                        dp[row][col-1]
                    ) + 1;
                }
            }
        }
        
        return dp[nRows-1][nCols-1];
    }
}