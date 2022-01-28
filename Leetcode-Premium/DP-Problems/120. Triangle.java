class Solution {
    private int[][] dp;
    private int numRows;
    private int numColsMax;
    
    private int UNASSIGNED = -10000;
    private List<List<Integer>> triangles;
    
    private void initialize(){
        this.dp = new int[numRows][numColsMax];
        for(int[] arr: this.dp){
            Arrays.fill(arr, UNASSIGNED);
        }
    }
    
    public int minimumTotal(List<List<Integer>> triangles) {
        this.numRows = triangles.size();
        this.numColsMax = triangles.get(this.numRows - 1).size();
        
        // initialize
        this.triangles = triangles;
        
        return bottomUpOptimizedSpace();
        // this.initialize();
        
        // return dfs(0, 0);
    }
    
    private int bottomUpOptimizedSpace(){
        int[] currentRow = new int[this.numColsMax];
        // initially this is equal to the last list.
        int lastRowIdx = this.numRows - 1;
        List<Integer> lastRow = this.triangles.get(lastRowIdx);
        int numElements = lastRow.size();
        
        
        int min = Integer.MAX_VALUE;
        for(int i=0; i<numElements; i++){
            currentRow[i] = lastRow.get(i);
            min = Math.min(min, currentRow[i]);
        }
        
        if(this.numRows == 1){
            return min;
        }
        
        for(int row=lastRowIdx-1; row>=0; row--){
            List<Integer> currentList = this.triangles.get(row);
            int size = currentList.size();
            for(int col=0; col<size; col++){
                int element = currentList.get(col);
                currentRow[col] = element + 
                    Math.min(currentRow[col], currentRow[col+1]);
            }
        }
        return currentRow[0];
    }
    
    private int dfs(int row, int col){
        // Base cases
        if(row == this.numRows){
            return 0;
        }
        
        if(this.dp[row][col] != UNASSIGNED){
            return this.dp[row][col];
        }
        
        int ans;
        
        List<Integer> currentRow = this.triangles.get(row);
        int currentElement = currentRow.get(col);
        ans = Math.min(
            dfs(row+1, col),
            dfs(row+1, col+1)
        );
        ans += currentElement;
        
        this.dp[row][col] = ans;
        return ans;
    }
}