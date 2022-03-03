class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        // edge case
        if(nums.length < 3)
            return 0;
        
        // return -1;
        // return bruteForce(nums);
        return optimized(nums);
    }
    
    private int optimized(int[] nums){
        // not dp, but using O(n) solution
        // idx (i, k) go upto nums[idx] - nums[idx - 1] NOT equal.
        // len = (k + 1) - i, #subarray(len) = (len + 1) - 3
        
        int count = 0;
        int lenSoFar = 0;
        for(int i=2; i<nums.length; i++){
            if((nums[i] - nums[i - 1]) ==
              (nums[i - 1] - nums[i - 2])){
                lenSoFar++;
            }else{
                count += getSlicesAmountOptimized(lenSoFar+2);
                lenSoFar = 0;
            }
        }
        
        // System.out.println("lenSoFar = " + lenSoFar);
        count += getSlicesAmountOptimized(lenSoFar+2);
        
        return count;
    }
    
    // O(1) sum
    private int getSlicesAmountOptimized(int n){
        // Using arithmetic series summation formulae
        return (
            (((n + 1) * (n - 4)) / 2) + 3
        );
    }
    
    // O(n) sum
    private int getSlicesAmount(int n){
        int sum = 0;
        if(n < 3){return 0;}
        
        for(int k=3; k<=n; k++){
            int term = (n + 1) - k;
            sum += term;
        }
        // System.out.println("Returning sum = " + sum);
        return sum;
    }
    
    // O(n^2)
    private int bruteForce(int[] nums){
        int count = 0;
        for(int i=0; i<(nums.length - 2); i++){
            int diff = nums[i+1] - nums[i];
            boolean flag = false;
            for(int j=i+1; j<(nums.length); j++){
                int currDiff = nums[j] - nums[j-1];
                if(currDiff != diff){
                    flag = true;
                    break;
                }
                
                int len = (j + 1 - i);
                // System.out.println("i = " + i + ", j = " 
                //                    + j + ", len = " + len);
                if(len >= 3){
                    count++;
                }
            }
            // if(!flag)
            //     count++;
        }
        
        return count;
    }
}