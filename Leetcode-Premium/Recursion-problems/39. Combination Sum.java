class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, 
                                              int target) {
        // sort, so that we can cancel
        Arrays.sort(candidates);
        
        List<List<Integer>> ans = new ArrayList<>();
        
        // recusive backtracking
        int targetSoFar = target;
        backtrack(ans, candidates, targetSoFar, 
                  new ArrayList<>(), 0);
        
        return ans;
    }
    
    private void backtrack(
        List<List<Integer>> ans,
        int[] nums,
        int targetSoFar,
        List<Integer> listSoFar,
        int idx
    ){
        // Base cases
        
        // 1. actually sum is obtained. return & append
        if(targetSoFar == 0){
            ans.add(new ArrayList<>(listSoFar));
            // listSoFar.clear();
            return;
        }

        // 2. exceeded, return
        if(targetSoFar < 0)
            return;
        
        // necessary ??
        // 3. boundary exceeded return
        if(idx == nums.length)
            return;
        
        // don't take
        backtrack(ans, nums, targetSoFar, 
                  new ArrayList<>(listSoFar), idx+1);
        
        // same index => take
        listSoFar.add(nums[idx]);
        backtrack(ans, nums, targetSoFar-nums[idx], 
                 new ArrayList<>(listSoFar), idx);
    
    }
    
}