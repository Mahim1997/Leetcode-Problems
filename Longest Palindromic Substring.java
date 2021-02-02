class Solution {
    public String longestPalindrome(String s) {
        int[][]table = new int[s.length()][s.length()];
        
        // Initialization
        for(int i=0; i<table.length; i++){
            for(int j=0; j<table.length; j++){
                table[i][j] = -1; // initialize everything to -1
            }
        }
        
        // Base Cases
        for(int i=0; i<table.length; i++){
            table[i][i] = 1; // (start = i, end = i) -> length = 1
        }
        for(int i=0; i<(table.length - 1); i++){
            if(s.charAt(i) == s.charAt(i+1)){
                table[i][i+1] = 2; // start = i, end = i+1 -> length = 2                
            }else{
                // table[i][i+1] = 0; // keep at 0
            }
        }
        

        
        // Recursion case.
        for(int len=2; len<table.length; len++){
            for(int row=0; row<table.length-2; row++){
                int col = row + len;
                if(col >= table.length) continue;
                if(s.charAt(row) == s.charAt(col)){
                    if (table[row+1][col-1] != -1)
                        table[row][col] = 2 + table[row+1][col-1];
                    // System.out.println("table[row][col]  " + row + " , " + col + " = " + table[row][col] + " , table[row+1][col-1] = " + table[row+1][col-1]);
                }else{
                    // table[row][col] = 0; // Math.max(table[row][col-1], table[row+1][col]);
                }
            }
        }
        
        // for(int i=0; i<(table.length-2); i++){
        //     for(int j=i+2; j<table[i].length; j++){
        //         if(s.charAt(i) == s.charAt(j)){
        //             table[i][j] = table[i+1][j-1] + 2;
        //         }else{
        //             table[i][j] = Math.max(table[i][j-1], table[i+1][j]);
        //         }
        //     }
        // }
        
        /////////// Print table.
        // for(int i=0; i<table.length; i++){
        //     for(int j=0; j<table[i].length; j++){
        //         System.out.printf("%2d ", table[i][j]);
        //     }
        //     System.out.println("");
        // }
        
        int start_idx = 0; // iterating the DP table // row
        int end_idx = table.length - 1; // col
        
        int max_val = -1; // or any negative value.
        for(int i=0; i<table.length; i++){
            for(int j=i; j<table.length; j++){
                if(table[i][j] >= max_val){
                    max_val = table[i][j];
                    start_idx = i;
                    end_idx = j;
                }
            }
        }
        char[] ans = new char[max_val]; // put in these indices.
        // System.out.println("start_idx = " + start_idx + " , end_idx = " + end_idx);
        
        int start_char_idx = 0; // for putting in the character array
        int end_char_idx = ans.length - 1;
        
        while(start_idx <= end_idx){ // keep looping until start_idx EXCEEDS end_idx
            if(s.charAt(start_idx) != s.charAt(end_idx)){ // don't add anything into ans.
                if(table[start_idx+1][end_idx] >= table[start_idx][end_idx-1]){
                    start_idx++;
                }else{
                    end_idx--;
                }
            }
            else{ // EQUAL, add into ans. s[start_idx] == s[end_idx]
                if(start_idx == end_idx){ // one-element position
                    ans[start_char_idx++] = s.charAt(start_idx);
                    break;
                }else{ // >= two-elements position
                    ans[start_char_idx++] = s.charAt(start_idx);
                    ans[end_char_idx--] = s.charAt(end_idx);

                    start_idx++;
                    end_idx--;                    
                }
            }
                
            // table[start_idx][end_idx]
        }
        
        


        return new String(ans);
    }
}