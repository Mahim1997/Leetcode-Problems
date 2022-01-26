class Solution {
    private int[] stones;
    private int numStonesOriginal;
    
    private int[][] cache;
    private int[][] sumPair;
    
    private int UNASSIGNED = -1000000;
    
    private void initialize(){
        this.cache = new int[this.numStonesOriginal][this.numStonesOriginal];
        for(int[] arr: this.cache){
            Arrays.fill(arr, UNASSIGNED);
        }
        
        
        // initialize sumPair
        this.sumPair = new int[this.numStonesOriginal][this.numStonesOriginal];
        for(int i=0; i<this.numStonesOriginal; i++){
            // originally will have the stones only.
            this.sumPair[i][i] = this.stones[i];
            
            for(int j=i+1; j<this.numStonesOriginal; j++){
                this.sumPair[i][j] = this.sumPair[i][j-1] + this.stones[j];
            }
        }
    }
    
    private boolean isAliceMove(int len){
        return ((this.numStonesOriginal%2) == (len%2));
    }
    
    public int stoneGameVII(int[] stones) {
        this.stones = stones;
        this.numStonesOriginal = stones.length;
        
        // initialize cache and other data structures
        this.initialize();
        
        // 2 <= n <= 1000, no need to worry about edge cases
        return this.dfs(0, this.numStonesOriginal-1);
    }
    
    // dfs -> calculates the difference between ALICE & BOB's score
    // minimax -> maximize for ALICE, minimize for BOB
    private int dfs(int first, int last){
        // base case
        if(first == last)
            return 0;
            // return this.stones[first];
        
        // check in cache
        if(this.cache[first][last] != UNASSIGNED){
            return this.cache[first][last];
        }
        
        
        int ans;
        
        // recursion, first remove, last remove, two choices
        int len = last - first + 1;
        
        
        // for left, removing left, means sum will be [left+1, right]
        int leftVal = this.sumPair[first+1][last];
        int rightVal = this.sumPair[first][last-1];
        
        if(this.isAliceMove(len)){
            // Alice's move, maximize
            ans = Math.max(
                leftVal + dfs(first+1, last),
                rightVal + dfs(first, last-1)
            );
        }
        else{
            // Bob's move, minimize
            ans = Math.min(
                -leftVal + dfs(first+1, last),
                -rightVal + dfs(first, last-1)
            );
        }
        
        
        // update cache & return
        this.cache[first][last] = ans;
        
        return ans;
    }
}