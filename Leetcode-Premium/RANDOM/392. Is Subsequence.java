class Solution {
    public boolean isSubsequence(String s, String t) {
        // true if 's' is a subsequence of 't'
        int sLength = s.length(), tLength = t.length();
        
        // edge case
        
        // 1. empty strings
        if(sLength == 0){
            return true;
        }
        if(tLength == 0){
            return (sLength == 0);
        }
        
        // 2. 's' is bigger than 't'
        if(sLength > tLength){
            return false;
        }
        
        int sPtr = 0, tPtr = 0;
        
        while((sPtr < sLength) && (tPtr < tLength)){
            if(s.charAt(sPtr) == t.charAt(tPtr)){
                sPtr++;
            }
            tPtr++;
            
            if(sPtr == sLength)
                return true;
        }
        
        return false;
    }
}