class Solution {
    private boolean[][] visited;
    int[] dx = {-1, 0,  0, 1};
    int[] dy = { 0, -1, 1, 0};
    
    public int maxAreaOfIsland(int[][] grid) {
        // check if all zeros.
        int nRows = grid.length;
        int nCols = grid[0].length;
        
        this.visited = new boolean[nRows][nCols];
        
        int maxArea = 0, currentArea = 0;
        
        for(int i=0; i<nRows; i++){
            for(int j=0; j<nCols; j++){
                if(grid[i][j] == 1){
                    if(this.visited[i][j] == false){
                        // this.visited[i][j] = true;
                        currentArea = bfs(grid, i, j, nRows, nCols);
                    }
                    maxArea = Math.max(maxArea, currentArea);
                }
            }
        }
        

        return maxArea;
    }

    private boolean isWithin(int x, int y, int nRows, int nCols){
        return ((x >= 0) && (x < nRows) && (y >= 0) && (y < nCols));
    }
    
    private int bfs(int[][] grid, int sourceX, int sourceY, int nRows, int nCols){
        Queue<int[]> queue = new LinkedList<>();
        
        // System.out.println("Calling for sourceX = " + sourceX + ", sourceY = " + sourceY);
        
        int cnt = 0;
        queue.add(new int[]{sourceX, sourceY});
        while(queue.isEmpty() == false){
            int[] node = queue.remove();
            int x = node[0];
            int y = node[1];
            
            // System.out.println("From queue, x = " + x + ", y = " + y);
            // if(isWithin(x, y, nRows, nCols) == false)
            //     continue;
            
            if(this.visited[x][y] == true)
                continue;
            
            this.visited[x][y] = true;
            cnt++;
            
            // movements.
            for(int i=0; i<dx.length; i++){
                int newX = x + dx[i];
                int newY = y + dy[i];
                if(isWithin(newX, newY, nRows, nCols) == true){
                    if(grid[newX][newY] == 1) // should be a '1'
                        queue.add(new int[]{newX, newY});
                }
            }
        }
        
        return cnt;
    }
    
}