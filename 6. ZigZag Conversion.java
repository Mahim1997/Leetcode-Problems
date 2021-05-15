class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1)
            return s;
        if(s.length() == 1)
            return s;
        
        String ans = "";
        
        // pattern is followed [see notebook]
        
        // gap_left will decrease per row, gap_right increase per row
        int gap_left = 2*(numRows - 1);
        int gap_right = 0; 
        
        int[] gaps = {gap_left, gap_right};
        
        
        for(int row=0; row<numRows; row++){ // per row, add the char at alternating gaps
            int start_idx = row; // start at the row'th index
            while(start_idx < s.length()){ // while inside
                // System.out.println("> while, row = " + row + " , start_idx = " + start_idx + " , gaps = " + gaps[0] + " , " + gaps[1]);
                for(int k=0; k<gaps.length; k++){ // two gaps.
                    int gap = gaps[k]; // get the gap {left/right}
                    if((gap != 0) && (start_idx < s.length())){ // don't add multiple times
                        ans += s.charAt(start_idx);
                    }
                    // System.out.println("+> For loop, start_idx = " + start_idx + " , gap = " + gap);
                    start_idx += gap; // increment this start index
                }
            }
            gaps[0] -= 2;
            gaps[1] += 2; // incr/decr by 2
        }
        
        
        return ans;
    }
}