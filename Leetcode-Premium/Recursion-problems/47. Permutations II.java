class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        
        // sort the nums for ordering
        Arrays.sort(nums);
        
        // convert to list
        List<Integer> numsList = Arrays.stream(nums).boxed().
                        collect(Collectors.toList());
        
        // custom function
        backtrack(ans, numsList, new ArrayList<>());
        
        return ans;
    }
    
    private void backtrack(List<List<Integer>> ans,
                          List<Integer> numsList,
                          List<Integer> soFarList){
        // numsList will change, remove each element, and recurse
        // soFarList will be added
        
        
        // base case
        if(numsList.isEmpty()){
            ans.add(new ArrayList<>(soFarList));
            return;
        }
        
        // recursion
        for(int i=0; i<numsList.size(); i++){
            // check for duplicates
            if((i > 0) && (numsList.get(i) == numsList.get(i - 1)))
                continue;
            
            // create copy lists
            List<Integer> newNumsList = new ArrayList<>(numsList);
            List<Integer> newSoFar = new ArrayList<>(soFarList);
            
            // add & remove [change states]
            newNumsList.remove(i); // remove 'i'th element
            newSoFar.add(numsList.get(i)); // add 'i'th element

            // recurse
            backtrack(ans, newNumsList, newSoFar);
        }
    }
}