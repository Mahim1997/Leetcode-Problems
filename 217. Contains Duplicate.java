class Solution {
    private boolean hashMap(int[] nums){
        Map<Integer, Integer> map = new HashMap<>();
        
        
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        
        // System.out.println(map);
        
        for(int key: map.keySet()){
            if(map.get(key) > 1){
                return true;
            }
        }
        
        
        return false;
    }
    
    private boolean usingSet(int[] nums){
        Set<Integer> set = new HashSet<>();
        
        for(int num: nums){
            
            if(set.contains(num)){
                return true;
            }
            set.add(num);
            
        }
        return false;
    }
    
    private boolean usingSort(int[] nums){
        if(nums.length == 1){
            return false;
        }
        
        Arrays.sort(nums);
        for(int i=1; i<nums.length; i++){
            if(nums[i-1] == nums[i]){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean containsDuplicate(int[] nums) {
        return usingSort(nums);
    }
}