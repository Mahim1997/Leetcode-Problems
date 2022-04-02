class Solution {
    private boolean isPalindrome(String s, int left, int right){
        // char[] chars = s.toCharArray();
        int n = right - left + 1; //chars.length;
        for(int i=0; i<n/2; i++){
            int leftIdx = i + left;
            int rightIdx = right - 1 - i;
            if(s.charAt(leftIdx) != s.charAt(rightIdx))
                return false;
        }
        return true;
    }
    
    public boolean validPalindrome(String s) {
        // go from left <----> right
        int n = s.length();
        
        // Edge case
        if(n == 1)
            return true;
        
        // find first, non equal => delete two, two substrings, check if they palindrome
        for(int i=0; i<n/2; i++){
            int left = i, right = n - 1 - i;
            // if(chars[left] != chars[right]){
                if(s.charAt(left) != s.charAt(right)){   
                // not equal
                
                // 1. Delete left character [left, right-1]
                // String str1 = s.substring(left, right); // .substring is exclusive function
                // 2. Delete right character [left+1, right]
                // String str2 = s.substring(left+1, right+1);
            
                // return (isPalindrome(str1) || isPalindrome(str2));
                
                return (
                    isPalindrome(s, left, right) ||
                    isPalindrome(s, left+1, right+1)
                );
            }
        }
        
        return true;
    }
}