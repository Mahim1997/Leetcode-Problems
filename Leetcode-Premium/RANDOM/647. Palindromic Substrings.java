class Solution {
    private String str;
    
    private int helperNumSubstrings(int left, int right,
                                    int countInitial) {
        int count = countInitial;
        int strlen = this.str.length();
        
        while((left >= 0) && (right < strlen)) {
            // check palindrome or not
            
            if(this.str.charAt(left) != this.str.charAt(right)){
                // do not continue
                break;
            }
            
            left--;
            right++;
            count++;
        }
        return count;
    }
        
    public int countSubstrings(String s) {
        this.str = s;
    
        int ans = 0;
        
        // this is used up by the 'odd' count
        // ans += s.length(); // firstly take all characters
        
        // O(n^2) ?
        int strlen = s.length();
        
        for(int idx=0; idx<strlen; idx++) {
            int oddSubstringCount = helperNumSubstrings(
                idx - 1,    // left of self
                idx + 1,    // right of self
                1
            );
             
            int evenSubstringCount = helperNumSubstrings(
                idx,        // self
                idx + 1,    // right of self
                0
            );
            
            // System.out.println("idx = " + idx + ", oddSubstringCount = " + oddSubstringCount + ", evenSubstringCount = " + evenSubstringCount);
            
            ans = ans + (oddSubstringCount + evenSubstringCount);
        }

                    
        return ans;
    }
}