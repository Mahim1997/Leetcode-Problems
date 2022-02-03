class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // already sorted
        int left = 0;
        int right = numbers.length - 1;
        
        while(left < right){
            int sum = numbers[left] + numbers[right];
            if(sum == target){
                // 1-indexed
                return new int[]{left+1, right+1};
            }
            if(sum < target){
                left++;
            }
            else{
                right--;
            }
        }
        
        return new int[]{-1, -1};
    }
}