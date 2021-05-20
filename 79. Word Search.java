class Solution {
	public boolean exist(char[][] board, String word) {
        // Find all the positions with the starting letter.
        int firstChar = word.charAt(0);

        // call the initialization visitedMap function
        boolean[][] visitedMap = new boolean[board.length][board[0].length];
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == firstChar){
                    // Tree wise search DFS style
                    if(dfsSearch(board, i, j, word, 1, visitedMap) == true){
                        return true; // elif false -> continue with next matched
                    }
                }
            }
        }
		// System.out.println("No starting characters found !! Return false.");
        return false;
    }

    private boolean dfsSearch(char[][] board, int i, int j, String word,
                             int level, boolean[][] visitedMap){
        // System.out.println("At level = " + level + " , i = " + i + " , j = " + j);
        

        // borders checking
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length){
            // System.out.println("RETURNING FALSE boundary check");
            return false;
        }
		// System.out.println(">> visitedMap[i][j] = " + visitedMap[i][j]);
        
        // visited check [already visited before.]
        if(visitedMap[i][j] == true){
            // System.out.println("RETURNING FALSE visited already i = " + i + " , j = " + j);
            return false;
        }
        // char checking [level starts at level=1]
        if(board[i][j] != word.charAt(level-1)){
            // System.out.println("RETURNING FALSE level check, board[i][j] = " + board[i][j] + ", word.index level-1 = " + word.charAt(level-1));
            return false;
        }

        // all false checking conditions will be done above.
        // all satisfied or not checking [final state found !]
        if(level == word.length()){ // check for true case after false checks over
            // System.out.println("RETURNING TRUE");
            return true;
        }
        
        
        visitedMap[i][j] = true; // visited flag: set
        
        // recursive checking [DFS: backtracking]
        
        // left checking [border checking done above]
        boolean left = dfsSearch(board, i, j-1, word, level+1, visitedMap);
        boolean right = dfsSearch(board, i, j+1, word, level+1, visitedMap);
        boolean up = dfsSearch(board, i-1, j, word, level+1, visitedMap);
        boolean down = dfsSearch(board, i+1, j, word, level+1, visitedMap);
        
        
        visitedMap[i][j] = false; // visited flag: reset
        
        return (left || right || up || down);
    }

	// INPLACE VISITED CHECKING
	private boolean dfsSearch(char[][] board, int i, int j, String word, int level){

        // borders checking
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length){
            // System.out.println("RETURNING FALSE boundary check");
            return false;
        }
        // visited check [already visited before.]
        if(board[i][j] == '$'){ // visited flag done in char array
            return false;
        }
        // char checking [level starts at level=1]
        if(board[i][j] != word.charAt(level-1)){
            return false;
        }

        // all false checking conditions will be done above.
        // all satisfied or not checking [final state found !]
        if(level == word.length()){ // check for true case after false checks over
            return true;
        }

        char prevChar = board[i][j];
        board[i][j] = '$'; // inplace visited flag: set
        
        // recursive checking [DFS: backtracking]
		// left checking [border checking done above]
        boolean left = dfsSearch(board, i, j-1, word, level+1);
        boolean right = dfsSearch(board, i, j+1, word, level+1);
        boolean up = dfsSearch(board, i-1, j, word, level+1);
        boolean down = dfsSearch(board, i+1, j, word, level+1);
        
        // visitedMap[i][j] = false; // visited flag: reset
        board[i][j] = prevChar; // inplace visited flag: reset
        
        return (left || right || up || down);
    }
    
}

