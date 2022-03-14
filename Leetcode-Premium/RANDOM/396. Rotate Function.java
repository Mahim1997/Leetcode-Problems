class Solution {
    public int maxRotateFunction(int[] nums) {
        // F(1) - F(0) = SUM(nums) - n*nums[n - 1]
        // F(2) - F(1) = SUM(nums) - n*nums[n - 2]
        // ...
        // F(k) - F(k-1) = SUM(nums) - n*nums[n - k]
        // F(n) - F(n-1) = SUM(nums) - n*nums[0]
        
        int n = nums.length;
        int sum = 0, f_0 = 0, max = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            f_0 += (i * nums[i]);
            sum += nums[i];
        }
        
        int prevTerm = f_0;
        for(int i=1; i<=n; i++){
            int currentTerm = prevTerm + sum - n*nums[n - i];
            max = Math.max(max, currentTerm);
            prevTerm = currentTerm;
        }
        
        return max;
    }
}