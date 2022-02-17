class Solution {
    
    // SIGNATURE METHOD
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        
        // Otherwise, take into a set
        // O(n log n)
        // Arrays.sort(nums);
        
        List<List<Integer>> ans = new ArrayList<>();
        
        
        
        Map<Integer, Integer> mapCounts = getMapOfCounts(nums);
        Map<Integer, List<List<Integer>>> mapSubsets = getSubsetsPerNumber(mapCounts);
        
        // System.out.println("mapSubsets = " + mapSubsets);
            
        // One-by-one, apply the cartesian product
        for(int key: mapSubsets.keySet()){
            applyCartesianProduct(ans, mapSubsets.get(key));
        }
        
        // add the empty set
        ans.add(new ArrayList<>());
        
        return ans;   
    }
    
    private void applyCartesianProduct( List<List<Integer>> subset1,
                                        List<List<Integer>> subset2){
        // copy to subset1
        List<List<Integer>> copySubset1 = new ArrayList<>(subset1);
        
        // First copy everything of set2 to set1
        subset1.addAll(subset2);
        
        // Then join by cartesian product
        for(List<Integer> list1: copySubset1){
            for(List<Integer> list2: subset2){
                List<Integer> newList = new ArrayList<>(list1);
                newList.addAll(list2);
                
                // add this 'joined' list to subset1
                subset1.add(newList);
            }
        }
    }
    
    private Map<Integer, List<List<Integer>>> getSubsetsPerNumber(
        Map<Integer, Integer> counts){
        // key: number, val: subsets of that number
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
    
        for(int num: counts.keySet()){
            int freq = counts.get(num);
            List<List<Integer>> subsets = new ArrayList<>();
            
            // per frequency
            // // do not include the empty set
            for(int f=1; f<=freq; f++){
                List<Integer> list = getNumberOfTimes(num, f);
                subsets.add(list);
            }
            
            // add to that map
            map.put(num, subsets);
        }
        
        return map;
    }
    
        
    private List<Integer> getNumberOfTimes(int num, int freq){
        List<Integer> list = new ArrayList<>();
        
        
        for(int i=1; i<=freq; i++)
            list.add(num);
        
        return list;
    }
    
    private Map<Integer, Integer> getMapOfCounts(int[] nums){
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        return map;
    }
}