class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums); // ascending order.
        
        int withNegative = 0;
        int allPositive = 0;
        int n = nums.length;
        
        if(nums[0] < 0){
            // contains min
            withNegative = nums[0]*nums[1]*nums[n - 1];
        }
        allPositive = nums[n - 1] * nums[n - 2] * nums[n - 3];
        
        return Math.max(withNegative, allPositive);
    }
}