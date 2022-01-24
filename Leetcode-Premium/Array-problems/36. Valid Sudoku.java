class Solution {
    private char[][] board;
    
    private boolean isRowValid(int row){
        Set<Character> set = new HashSet<>();
        for(int col=0; col<9; col++){
            char c = board[row][col];
            if(c == '.'){continue;}
            if(set.contains(c))
                return false;
            set.add(c);
        }
        return true;
    }
    
    private boolean isColumnValid(int col){
        Set<Character> set = new HashSet<>();
        for(int row=0; row<9; row++){
            char c = board[row][col];
            if(c == '.'){continue;}
            if(set.contains(c))
                return false;
            set.add(c);
        }
        return true;
    }
    
    private boolean isGridValid(int grid){
        // grid = 0, (0,0) -> (2,2)
        // grid = 1, (0,3) -> (2,5)
        // grid = 3, (0,6) -> ()
        
        int rStart=0, rEnd=0, cStart=0, cEnd=0;
        if(grid == 0){
            rStart = 0; cStart = 0;
            rEnd = 2; cEnd = 2;
        }
        else if(grid == 1){
            rStart = 0; cStart = 3;
            rEnd = 2; cEnd = 5;
        }
        else if(grid == 2){
            rStart = 0; cStart = 6;
            rEnd = 2; cEnd = 8;
        }
        else if(grid == 3){
            rStart = 3; cStart = 0;
            rEnd = 5; cEnd = 2;
        }
        else if(grid == 4){
            rStart = 3; cStart = 3;
            rEnd = 5; cEnd = 5;
        }
        else if(grid == 5){
            rStart = 3; cStart = 6;
            rEnd = 5; cEnd = 8;
        }
        else if(grid == 6){
            rStart = 6; cStart = 0;
            rEnd = 8; cEnd = 2;
        }
        else if(grid == 7){
            rStart = 6; cStart = 3;
            rEnd = 8; cEnd = 5;
        }
        else if(grid == 8){
            rStart = 6; cStart = 6;
            rEnd = 8; cEnd = 8;
        }

        Set<Character> set = new HashSet<>();
        for(int i=rStart; i<=rEnd; i++){
            for(int j=cStart; j<=cEnd; j++){
                char c = this.board[i][j];
                if(c == '.')
                    continue;
                if(set.contains(c) == true)
                    return false;
                set.add(c);
            }
        }
        return true;
    }
    
    public boolean isValidSudoku(char[][] board) {
        this.board = board;
        
        // Row check
        for(int row=0; row<9; row++){
            if(isRowValid(row) == false)
                return false;
        }
        
        // Col check
        for(int col=0; col<9; col++){
            if(isColumnValid(col) == false)
                return false;
        }
        
        // Subgrid check
        for(int grid=0; grid<9; grid++){
            if(isGridValid(grid) == false)
                return false;
        }
        
        
        return true;
    }
}