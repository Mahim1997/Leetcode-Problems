class Board {
    public int size;
    
    private char[][] board;
    private char QUEEN = 'Q';
    private char BLANK = '.';
    
    public Board(int n) {
        this.board = new char[n][n];
        this.size = n;
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                this.board[i][j] = BLANK;
            }
        }
    }
    
    private int[][] movements = {
        {-1, -1}, {-1, 0}, {-1, 1},
        {1, -1}, {1, 0}, {1, 1},
        {0, 1}, {0, -1}
    };
    
    private boolean isWithin(int row, int col) {
        return (
            (row >= 0) && (col >= 0) &&
            (row < this.board.length) && (col < this.board[0].length)
        );
    }
    
    private boolean isQueen(int row, int col) {
        return (this.board[row][col] == QUEEN);
    }
    
    private String getRow(int row) {
        StringBuilder bld = new StringBuilder();        
        for(int c=0; c<this.board.length; c++)
            bld.append(this.board[row][c]);
        return bld.toString();
    }
    
    public boolean isUnderAttack(int row, int col) {
        for(int[] move: movements) {
            int rowNew = row, colNew = col;
            while(isWithin(rowNew, colNew)) {
                if(isQueen(rowNew, colNew)) { return true; }
                
                rowNew += move[0];
                colNew += move[1];
            }
        }
        return false;
    }
    
    public List<String> getList() {
        List<String> list = new ArrayList<>();
        for(int row=0; row<this.board.length; row++)
            list.add(getRow(row));
        return list;
    }
    
    public void placeQueen(int row, int col) {
        this.board[row][col] = QUEEN;
    }
    
    public void removeQueen(int row, int col) {
        this.board[row][col] = BLANK;
    }
    
    @Override
    public String toString() {
        List<String> list = getList();
        StringBuilder bld = new StringBuilder();        
        for(String s: list)
            bld.append(s).append("\n");
        return bld.toString();
    }
}

class Solution {
    private Board board;
    
    public List<List<String>> solveNQueens(int n) {
        this.board = new Board(n);
        List<List<String>> ans = new ArrayList<>();
        
        backtrack(board, ans, 0);
        
        return ans;
    }
    
    private void backtrack(Board board, List<List<String>> ans, int row) {
        for(int col=0; col<board.size; col++) {
            if(board.isUnderAttack(row, col) == false) {
                board.placeQueen(row, col);
                
                if(row == (board.size - 1)) {
                    // need to include
                    ans.add(board.getList());
                }
                else {
                    backtrack(board, ans, row + 1);
                }
                
                board.removeQueen(row, col);
            }
        }
    }
}
