class Solution {
    private static int[][] movements = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    
    private static int LAND = 1;
    
    // API call
    public int maxAreaOfIsland(int[][] grid) {
        // standard bfs
        int ROWS = grid.length;
        int COLS = grid[0].length;
        boolean[][] visited = new boolean[ROWS][COLS];
        
        int maxArea = 0;
        for(int r=0; r<ROWS; r++) {
            for(int c=0; c<COLS; c++) {
                if(!visited[r][c] && (grid[r][c] == LAND)) {
                    int currentArea = dfs(
                        grid, visited, r, c
                    );
                    maxArea = Math.max(maxArea, currentArea);
                }
            }
        }
        
        return maxArea;
    }
    
    private boolean isWithin(int r, int c, int[][] grid) {
        return (
            (r >= 0) && (c >= 0) &&
            (r < grid.length) && (c < grid[0].length)
        );
    }
    
    private int dfs(
        int[][] grid, 
        boolean[][] visited, 
        int rowStart, 
        int colStart
    ) {
        int connectedComponents = 0;
        
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[]{rowStart, colStart});
        visited[rowStart][colStart] = true;
        
        while(!stack.isEmpty()) {
            int[] top = stack.pop();
            int row = top[0], col = top[1];
            
            // increment connected land
            connectedComponents++;
            
            // add children
            for(int[] dir: movements) {
                int rowNew = row + dir[0];
                int colNew = col + dir[1];
                
                // ignore if out of bounds
                if(!isWithin(rowNew, colNew, grid))
                    continue;
                
                // ignore if already visited
                if(visited[rowNew][colNew] == true)
                    continue;
                
                // ignore if NOT land
                if(grid[rowNew][colNew] != LAND)
                    continue;
                
                // 'visit' the children
                stack.add(new int[]{rowNew, colNew});
                visited[rowNew][colNew] = true;
            }
        }
        
        return connectedComponents;
    }
    
    
    
    private int bfs(
        int[][] grid, 
        boolean[][] visited, 
        int rowStart, 
        int colStart
    ) {
        int connectedComponents = 0;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{rowStart, colStart});
        visited[rowStart][colStart] = true;
        
        while(!queue.isEmpty()) {
            int[] top = queue.remove();
            int row = top[0], col = top[1];
            
            // increment connected land
            connectedComponents++;
            
            // add children
            for(int[] dir: movements) {
                int rowNew = row + dir[0];
                int colNew = col + dir[1];
                
                // ignore if out of bounds
                if(!isWithin(rowNew, colNew, grid))
                    continue;
                
                // ignore if already visited
                if(visited[rowNew][colNew] == true)
                    continue;
                
                // ignore if NOT land
                if(grid[rowNew][colNew] != LAND)
                    continue;
                
                // 'visit' the children
                queue.add(new int[]{rowNew, colNew});
                visited[rowNew][colNew] = true;
            }
        }
        
        return connectedComponents;
    }
}
