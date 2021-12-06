class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int idxToMap;
        // convert to -ve inside the array itself.
        // index becomes map. [0..n-1] -> [1..n]
        for(int i=0; i<nums.length; i++){
            idxToMap = Math.abs(nums[i]) - 1;
            if(nums[idxToMap] > 0){
                nums[idxToMap] = -nums[idxToMap];
            }
            // nums[idxToMap] = -Math.abs(nums[idxToMap]);
        }
        // find the remaining indices which have positive value.
        for(int i=0; i<nums.length; i++){
            if(nums[i] > 0){
                list.add(i + 1); // 0 base index -> value is idx+1
            }
        }
        
        return list;
    }
}