class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> remainderToLastIndex = new HashMap<>();
        int n = nums.length;
        int sum = 0;
        
        // As '0' remainder is always available
        remainderToLastIndex.put(0, -1);
        
        for(int i=0; i<n; i++) {
            int num = nums[i];
            sum += num;
            int remainder = sum%k;
            
            // System.out.println("For i = " + i + ", num = " + num + ", remainder = " + remainder + ", remainderToLastIndex: " + remainderToLastIndex);
            
            if(remainderToLastIndex.containsKey(remainder)) {
                int lastIndex = remainderToLastIndex.get(remainder);
                if((i - lastIndex) >= 2) {
                    return true;
                }
            }
            else {
                // Always keep the 'leftmost' index
                remainderToLastIndex.put(remainder, i);
            }
        }
        
        return false;
    }
}