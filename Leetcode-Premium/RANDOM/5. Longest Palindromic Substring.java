class Solution {
    public String longestPalindrome(String s) {
        int strlen = s.length();
        String bestAns = "";
        int maxLen = Integer.MIN_VALUE;

        int bestLeft = -1, bestRight = -1;
        
        for(int idx=0; idx<strlen; idx++) {
            // expand around center
            int[] leftArr = {idx, idx};
            int[] rightArr = {idx, idx + 1}; // odd, even
            
            for(int j=0; j<2; j++) {
                int left = leftArr[j];
                int right = rightArr[j];
                
                int[] params = getLengthPalindromeAroundCenter(s, 
                                                               left,
                                                               right);
                
                // System.out.println("For idx = " + idx + ", left = " + left + ", right = " + right + ", params => " + Arrays.toString(params));
                
                int leftLocal = params[0] + 1;
                int rightLocal = params[1] - 1;
                
                int diff = rightLocal - leftLocal + 1;
                
                if(diff > maxLen) {
                    maxLen = diff;
                    bestLeft = leftLocal;   // already moves 1 step
                    bestRight = rightLocal;
                }

            }
            
        }
        
        // System.out.println("Outside, bestLeft = " + bestLeft + ", bestRight = " + bestRight);
        
        if(bestLeft == -1){
            bestAns = "";
        }
        else{
            // exclusive to the right
            bestAns = s.substring(bestLeft, bestRight + 1); 
        }
        
        return bestAns;
    }
    
    private int[] getLengthPalindromeAroundCenter(
        String s, 
        int left, 
        int right
    ) {
        int strlen = s.length();
        
        while((left >= 0) && (right < strlen)) {
            if(s.charAt(left) != s.charAt(right))
                break;
            
            left--;
            right++;
        }
        
        return new int[]{left, right};
    }
}