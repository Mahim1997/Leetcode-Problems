class Pair {
    int row;
    int col;
    
    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    @Override
    public String toString() {
        return "[" + this.row + "," + this.col + "]";
    }
    
    private boolean isWithin(int ROWS, int COLS) {
        return (
            (row >= 0) && (col >= 0) &&
            (row < ROWS) && (col < COLS)
        );  
    }
    
    public Pair getNextCoordinate(char[][] board) {
        if(this.col == (board[0].length - 1))
            return new Pair(this.row + 1, 0);
        else
            return new Pair(this.row, this.col + 1);
    }
    
    public boolean isOutOfBounds(char[][] board) {
        return !isWithin(board.length, board[0].length);
    }
}

class Helper {
    public static boolean isValidRow(
        char[][] board, 
        char toPlace, 
        Pair current
    ) {
        // only check all columns
        for(int c=0; c<board[0].length; c++) {
            if(c == current.col)
                continue;
            if(board[current.row][c] == toPlace)
                return false;
        }
        return true;
    }

    public static boolean isValidColumn(
        char[][] board, 
        char toPlace, 
        Pair current
    ) {
        // only check all rows
        for(int r=0; r<board.length; r++) {
            if(r == current.row)
                continue;
            if(board[r][current.col] == toPlace)
                return false;
        }
        return true;
    }

    // Inclusive
    private static boolean isWithin(int val, int start, int end) {
        return ((val >= start) && (val < end));
    }
    
    // Assuming 9X9 directly for optimization
    private static int getStart(int val) {
        int[] arr = {0, 3, 6, 9};
        for(int i=0; i<arr.length-1; i++) {
            int val1 = arr[i], val2 = arr[i + 1];
            if(isWithin(val, val1, val2))
                return val1;
        }
        return -1;
    }
    
    private static Pair getStartPair(Pair current, int ROWS, int COLS) {
        return new Pair(
            getStart(current.row), 
            getStart(current.col)
        );
    }
    
    public static boolean isValidSubGrid(
        char[][] board,
        char toPlace,
        Pair current
    ) {
        // check subgrid   
        Pair startPair = getStartPair(current, board.length, board[0].length);
        int row = startPair.row;
        int col = startPair.col;
        
        // Assuming 3X3 grid
        int size = 3;
        
        for(int r=row; r<row+size; r++) {
            for(int c=col; c<col+size; c++) {
                if((current.row == r) && (current.col == c))
                    continue;
                if(board[r][c] == toPlace)
                    return false;
            }   
        }
        return true;
    }
}

class Solution {
    // API
    public void solveSudoku(char[][] board) {
        int n = board.length; // n X n board ==> 9 X 9 board
        backtrack(board, new Pair(0, 0));
        return;
    }
    
    private boolean isValidPlacement(
        char[][] board, 
        char toPlace, 
        Pair current
    ) {
        // check row
        if(!Helper.isValidRow(board, toPlace, current))
            return false;
        
        // check col
        if(!Helper.isValidColumn(board, toPlace, current))
            return false;
            
        // check sub-grid
        if(!Helper.isValidSubGrid(board, toPlace, current))
            return false;
        
        return true;
    }
    
    private static char EMPTY = '.';
    private boolean doneSolvingState = false;
    
    private void backtrack(char[][] board, Pair current) {
        Pair next = current.getNextCoordinate(board);
        
        // non-empty-case
        if(board[current.row][current.col] != EMPTY) {
            // recurse onto the next (row, col)
            if(next.isOutOfBounds(board)) {
                this.doneSolvingState = true;
                return; // DONE recursion
            }
            backtrack(board, next);
        }
        else {
            // find all valid 'candidates'
            for(char charToPlace='1'; charToPlace<='9'; charToPlace++) {
                // System.out.println("to place: " + charToPlace + ", at: " + current);       
                if(!isValidPlacement(board, charToPlace, current))
                    continue;
                
                // place
                board[current.row][current.col] = charToPlace;
                
                // backtrack
                if(next.isOutOfBounds(board)) {
                    this.doneSolvingState = true;
                    return; 
                } 
        
                backtrack(board, next);
                
                // remove, if not done
                if(!this.doneSolvingState)
                    board[current.row][current.col] = EMPTY;
            }
        }
    }
}

