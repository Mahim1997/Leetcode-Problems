class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        
        List<Integer> numsList = Arrays.stream(nums).boxed().
                                    collect(Collectors.toList());

        backtrack(list, numsList, new ArrayList<>());
        
        return list;
    }
    
    private void backtrack(List<List<Integer>> answer,
                            List<Integer> numsList,
                            List<Integer> soFarList){
        
        if(numsList.size() == 0){
            answer.add(new ArrayList<>(soFarList));
            soFarList.clear();
            return;
        }
        

        for(int i=0; i<numsList.size(); i++){
            
            List<Integer> newNumsList = new ArrayList<>(numsList);
            List<Integer> newSoFarList = new ArrayList<>(soFarList);
            
            newSoFarList.add(numsList.get(i));
            newNumsList.remove(i);
            
            
            backtrack(answer, newNumsList, newSoFarList);
            
        }
    }
}