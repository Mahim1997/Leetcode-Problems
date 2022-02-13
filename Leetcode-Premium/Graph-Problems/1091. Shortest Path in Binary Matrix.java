class Solution {
    private boolean isWithin(int row, int col, int[][] grid){
        return (
            (row >= 0) &&
            (col >= 0) &&
            (row < grid.length) &&
            (col < grid[0].length)
        );
    }
    
    private boolean isDestination(int[] pos, int[][] grid){
        return ((pos[0] == (grid.length - 1)) && 
                (pos[1] == (grid[0].length - 1)));
    }
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        
        // edge cases
        if(grid[0][0] == 1)
            return -1;
        
        // left, right, down, up
        // left-down, left-up, right-down, right-up
        
        int[] dx = {-1, 1,  0, 0, -1, -1,  1, 1};
        int[] dy = { 0, 0, -1, 1, -1,  1, -1, 1};
        
        Queue<int[]> queue = new LinkedList<>();
        
        // initial source
        queue.add(new int[]{0, 0});
        // modify 'visited' to 2
        int VISITED = 2;
        grid[0][0] = VISITED;
        
        int distance = 0; 
        while(!queue.isEmpty()){
            
            int qSize = queue.size();
            distance++; // initial is a visited cell
            for(int i=0; i<qSize; i++){
                int[] v = queue.remove();
                
                // Check for GOAL node
                if(isDestination(v, grid)){
                    remodifyArray(grid);
                    return distance;
                }
                
                // children adding
                for(int j=0; j<dx.length; j++){
                    int delX = dx[j];
                    int delY = dy[j];
                    
                    int[] newMove = new int[2];
                    newMove[0] = v[0] + delX;
                    newMove[1] = v[1] + delY;
                    
                    // only those cells which are possible to add
                    if(isWithin(newMove[0], newMove[1], grid)
                      && (grid[newMove[0]][newMove[1]] == 0)){
                        
                        queue.add(newMove);
                        // System.out.println("Adding to queue (" + newMove[0] + "," + newMove[1] + "), distance = " + distance);
                        
                        grid[newMove[0]][newMove[1]] = VISITED;
                    }
                }
                
                
            }
            
            
        }
        
        if(grid[grid.length-1][grid[0].length-1] != VISITED)
            distance = -1;
        
        remodifyArray(grid);
        
        return distance;
    }
    
    private void remodifyArray(int[][] grid){
        // convert '2' back to '0'
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                if(grid[i][j] == 2){
                    grid[i][j] = 0;
                }
            }
        }
    }
}