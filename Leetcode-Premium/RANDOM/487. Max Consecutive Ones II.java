class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int numOnesLast = 0;
        int numOnesCurrent = 0;
        boolean doesZeroExist = false;
        
        int maxNumOnes = 0;
        
        for(int num: nums) {
            // DFA: if zero, break else, continue, if first state ==> something else
            if(num == 1) {
                numOnesCurrent++;
                // Just for those who have **only 1's**
                maxNumOnes = Math.max(maxNumOnes, numOnesCurrent);
            }
            else {
                doesZeroExist = true;
                // zero has been encountered
                int possibleMax = numOnesCurrent + numOnesLast + 1; // include the 0
                maxNumOnes = Math.max(maxNumOnes, possibleMax);
                
                numOnesLast = numOnesCurrent;
                numOnesCurrent = 0;
            }
        }
        
        // Last check (Assume very last one is a zero) for boundary checking
        if(doesZeroExist) {
            int possibleMax = numOnesCurrent + numOnesLast + 1;
            maxNumOnes = Math.max(maxNumOnes, possibleMax);            
        }
        
        return maxNumOnes;
    }
}