class Solution {
    public List<List<Integer>> subsets(int[] numsArr) {
        Set<List<Integer>> ans = new HashSet<>();
        
        List<Integer> nums = new ArrayList<>();
        for(int x: numsArr){nums.add(x);}
        
        ans.add(new ArrayList<>()); // add empty list.
        //// Given that nums.length >= 1
        // if(nums.size() == 0){
        //     return ans; // edge case of no elements
        // }
        
        recurse(ans, nums);
        ans.add(new ArrayList<>(nums)); // add whole list
        
        List<List<Integer>> list = new ArrayList<>(ans);
        return list;
    }
    
    private void recurse(Set<List<Integer>> ans,
                        List<Integer> nums){
        if(nums.size() == 0){
            return;
        }
        
        // add the current list
        ans.add(nums); 
        
        int n = nums.size();
        for(int i=0; i<n; i++){
            // remove
            int num = nums.get(i);
            nums.remove(i);
            
            // backtrack, recurse
            recurse(ans, new ArrayList<>(nums));
            
            // add back
            nums.add(i, num);
        }
    }
}