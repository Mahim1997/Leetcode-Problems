class Solution {
    
    private int numMoves;
    // private Map<Integer, Integer> cache;
    private int[][] cache;
    private int UNASSIGNED = -1000;
    
    private void initializeCache(){
        // 0 -> ALICE, 1 -> BOB
        this.cache = new int[numMoves + 1][2]; // -1 -> END
        for(int[] arr: this.cache){
            Arrays.fill(arr, UNASSIGNED);
        }
    }
    
    public boolean winnerSquareGame(int numMoves) {
        this.numMoves = numMoves;
        this.initializeCache();
        int whoseMove = dfs(this.numMoves, 0); // currently is ALICE's movement
        
        return (whoseMove == 0); // return true if ALICE wins
    }
    
    private int dfs(int idx, int player){
        // System.out.println("idx = " + idx + ", player = " + player);
        if(idx < 0)
            return -1;
        
        if(idx == 0){ // this player has LOST
            return 1 - player;
        }
        
        if(this.cache[idx][player] != UNASSIGNED)
            return this.cache[idx][player];
        
        int answer = 1 - player;
        for(int i=1; i*i<=idx; i++){
            int squareNum = i*i;
            int winningPlayer = dfs(idx-squareNum, 1-player);
            
            if(winningPlayer == player){
                // OPTIMAL -> Always this player wants to win.
                answer = player;
            }else if(winningPlayer == -1){
                continue;
            }
        }
        this.cache[idx][player] = answer;
        return answer; // BOB WINS
    }
}