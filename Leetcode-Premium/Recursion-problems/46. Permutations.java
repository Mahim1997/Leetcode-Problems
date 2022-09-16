class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        
        // List<Integer> numsList = Arrays.stream(nums).boxed().
        //                             collect(Collectors.toList());

        backtrack(list, nums, 0);
        
        return list;
    }
    
    private void backtrack(List<List<Integer>> answer,
                          int[] nums,
                          int startIdx){
        
        if(startIdx==(nums.length)){
            List<Integer> soFarList = Arrays.stream(nums).boxed().
                                    collect(Collectors.toList());
            answer.add(soFarList);
            return;
        }
        
        for(int i=startIdx; i<nums.length; i++){
            // include current element
            int currentElement = nums[i];
            
            // swap 'i'th element with 'startIdx'th element
            int temp = nums[i];
            nums[i] = nums[startIdx];
            nums[startIdx] = temp;
            
            // recurse
            backtrack(answer, nums, startIdx+1);
            
        // swap back 'i'th element with 'startIdx'th element
            temp = nums[i];
            nums[i] = nums[startIdx];
            nums[startIdx] = temp;
            
            // numsList.add(i, currentElement);
        }
        
        
    }
}