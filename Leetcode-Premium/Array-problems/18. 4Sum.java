class Solution {

    private List<List<Integer>> twoSum(int[] nums, int start,
                                       int newTarget){
        List<List<Integer>> list = new ArrayList<>();
        
        int left = start;
        int right = nums.length - 1;
        
        int sum;
        while(left < right){
            // check for duplicates
            if((left > start) && (nums[left] == nums[left - 1])){
                left++;
                continue;
            }
            sum = nums[left] + nums[right];
            if(sum == newTarget){
                list.add(new ArrayList<>(List.of(nums[left],
                                                 nums[right])));
                left++;
            }
            else if(sum > newTarget){
                right--;
            }
            else if(sum < newTarget){
                left++;
            }
        }
        
        return list;
    }
    
    
    // Recursion.
    private List<List<Integer>> kSum(int[] nums, int start,
                                     int k, int newTarget){
        
        // System.out.println("Calling for start = " + start 
        //        + ", k = " + k + ", newTarget = " + newTarget);
        
        List<List<Integer>> list = new ArrayList<>();
        
        // base case
        if(k == 2){
            return twoSum(nums, start, newTarget);
           //  System.out.println("For twoSum, start = " + start 
           // + ", newTarget = " + newTarget + ": " + ans.toString());
        }
        // recursive case
        for(int i=start; i<=(nums.length - k); i++){
            // check for duplicates
            if((i > start) && (nums[i] == nums[i - 1]))
               continue;
            
            int newerTarget = newTarget - nums[i];
            List<List<Integer>> ans = kSum(nums, i+1,
                                          k-1, newerTarget);

            // add nums[i] to each list of the answer
            for(List<Integer> temp: ans){
                temp.add(nums[i]);
            }
               
            // push to final list answer    
            if(!ans.isEmpty()){
                list.addAll(ans);
            }
        }
        
        // System.out.println("RETURNING for start = " 
        //                    + start + ", k = " + k + 
        //                    ", newTarget = " + newTarget + ": " 
        //                    + list.toString());
        
        return list;
    }
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums.length < 4){
            return new ArrayList<>();
        }
        // sort the array
        Arrays.sort(nums);
        return kSum(nums, 0, 4, target);
    }
}