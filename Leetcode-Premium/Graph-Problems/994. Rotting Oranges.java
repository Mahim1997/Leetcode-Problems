class Solution {
    static int BLANK = 0;
    static int FRESH = 1;
    static int ROTTEN = 2;
    
    public int orangesRotting(int[][] grid) {
        
        int minutes = 0;
        int numFresh = 0;
        
        Queue<int[]> queue = new LinkedList<>();
        
        // add all existing rotten positions into the queue
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                int orange = grid[i][j];
                if(orange == ROTTEN){
                    queue.add(new int[]{i, j});
                }
                else if(orange == FRESH){
                    numFresh++;
                }
            }
        }
        
        // edge case, no fresh oranges
        if(numFresh == 0)
            return 0;
    
        // LEFT, RIGHT, UP, DOWN
        int[] dx = {-1, 1, 0,  0};
        int[] dy = { 0, 0, 1, -1};
        
        while(!queue.isEmpty()){
            // System.out.println("numFresh = " + numFresh);
            
            minutes++;
            int qSize = queue.size();
            
            for(int i=0; i<qSize; i++){
                int[] v = queue.remove();
                
                // mark as 'visited'
                // if(grid[v[0]][v[1]] == FRESH){
                //     // numFresh--;
                //     grid[v[0]][v[1]] = ROTTEN;
                // }
                
                for(int j=0; j<dx.length; j++){
                    int[] newMove = new int[2];
                    newMove[0] = v[0] + dx[j];
                    newMove[1] = v[1] + dy[j];
                    
                    // add children i.e. only 'fresh' to queue
                    if((isWithinBounds(newMove, grid)) &&
                       (grid[newMove[0]][newMove[1]] == FRESH)){
                        queue.add(newMove);
                        
                        // mark as visited
                        grid[newMove[0]][newMove[1]] = ROTTEN;
                        numFresh--;
                        
                        // System.out.println("Adding to queue, (" + newMove[0] + "," + newMove[1] + "), minutes = " + minutes + ", numFresh = " + numFresh);
                        
                    }
                }
                
            }
        }
        
        // minutes will be incremented by 1
        return (numFresh > 0) ? -1 : (minutes - 1);
    }
    
    private boolean isWithinBounds(int[] move, int[][] grid){
        return (
            (move[0] >= 0) && 
            (move[1] >= 0) &&
            (move[0] < grid.length) &&
            (move[1] < grid[0].length)
        );
    }
}