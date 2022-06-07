class MySet {
    private int MAX_SIZE = 2000; // -300 to 300, with few things
    
    private boolean[][] visited;
    private int size;
    
    public MySet() {
        this.visited = new boolean[MAX_SIZE][MAX_SIZE]; // false
        this.size = 0;
    }
    
    private void add(int row, int col) {
        this.visited[row][col] = true;
        this.size++;
    }
    
    private void remove(int row, int col) {
        this.visited[row][col] = false;
        this.size--;
    }

    private boolean contains(int row, int col) {
        return this.visited[row][col];
    }
    
    public void add(MyPair p) {
        this.add(p.row, p.col);
    }
    
    public void remove(MyPair p) {
        this.remove(p.row, p.col);
    }
    
    public boolean contains(MyPair p) {
        return this.contains(p.row, p.col);
    }
    
    public int size() {
        return this.size;
    }
}

class MyPair {
    public int row;
    public int col;
    
    public static int OFFSET = 500; // 300 + big number
    
    public MyPair(int r, int c) {
        this.row = r;
        this.col = c;
    }
    
    public void applyOffset(int offsetRow, int offsetCol) {
        this.row += offsetRow;
        this.col += offsetCol;
    }
    
    @Override
    public boolean equals(Object o) {
        MyPair other = (MyPair) o;
        return (
            (this.row == other.row) &&
            (this.col == other.col)
        );
    }
    
    @Override
    public int hashCode() {
        int hash = 13;
        hash = hash*7 + this.row;
        hash = hash*7 + this.col;
        return hash;
    }
    
    @Override
    public String toString() {
        return "(" + this.row + "," + this.col + ")";
    }
}

class Solution {
    private int getDistance(MyPair p1, MyPair p2) {
        return Math.abs(
            (p1.row - p2.row) + 
            (p1.col - p2.col)
        );
    }
    
    private int[][] movements = {
        {-2, -1}, {-2, 1},
        {-1, -2}, {-1, 2},
        {1, -2}, {1, 2},
        {2, -1}, {2, 1}
    };
    
    public int minKnightMoves(int x, int y) {
        MyPair source = new MyPair(0, 0);
        MyPair dest = new MyPair(y, x); // x is col, y is row
    
        source.applyOffset(MyPair.OFFSET, MyPair.OFFSET);
        dest.applyOffset(MyPair.OFFSET, MyPair.OFFSET);
        
        // BFS
        Queue<MyPair> queue = new LinkedList<>();
        queue.add(source);
        
        MySet visited = new MySet();
        
        int level = 0;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
                        
            for(int i=0; i<size; i++) {
                // for each child
                MyPair p = queue.remove();
                
                // check if destination
                if(p.equals(dest)) {
                    return level;
                }
                
                for(int[] move: movements) {
                    MyPair child = new MyPair(p.row, p.col);
                    child.applyOffset(move[0], move[1]);
                    
                    // check if contains
                    if(!visited.contains(child)) {
                        // add to queue AND also add to visited
                        queue.add(child);
                        visited.add(child);
                    }
                }
            }
            
            level++;
        }
        
        return level;
    }
}



