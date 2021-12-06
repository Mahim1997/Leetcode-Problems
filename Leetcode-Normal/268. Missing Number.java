class Solution {
    public int missingNumber(int[] nums) {
        // sum([0..n]) - sum of array
        int sum = 0;
        for(int x: nums){
            sum += x;
        }

        return ((nums.length*(nums.length+1)) / 2) - sum;
    }
}