// Brute Force
class Solution {
    public int maxArea(int[] height) {
        if(height.length <= 1) return height[0];
        
        int max_area = -10000;
        for(int i=0; i<height.length; i++){
            for(int j=1; j<height.length; j++){
                int area = (j - i) * Math.min(height[j], height[i]);
                if(area > max_area){
                    max_area = area;
                }
            }class Solution {
    private boolean nestedLoop(int[] nums, int k){
        if(nums.length == 1){
            return false;
        }
        for(int i=0; i<nums.length-1; i++){
            for(int j=i+1; j<nums.length; j++){
                if(nums[i] == nums[j]){
                    if(Math.abs(i - j) <= k){
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // using hash map
        Map<Integer, Integer> map = new HashMap<>();
        
        int n, prevIdx;
        for(int i=0; i<nums.length; i++){
            n = nums[i];
            if(map.containsKey(n)){
                prevIdx = map.get(n);
                if(Math.abs(i - prevIdx) <= k){
                    return true;
                }else{
                    map.put(n, i);
                }
            }else{
                map.put(n, i);
            }
        }
        
        return false;
        
    }
}