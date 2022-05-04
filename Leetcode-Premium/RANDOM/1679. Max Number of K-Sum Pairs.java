class Solution {
    public int maxOperations(int[] nums, int k) {
        // not possible using set, need hashmap & counting
        Map<Integer, Integer> map = new HashMap<>();
        
        int countTotal = 0;
        
        for(int num: nums){
            int diff = k - num;
            int countDifference = map.getOrDefault(diff, 0);
            int countCurrentNum = map.getOrDefault(num, 0);
            
            if(countDifference > 0){
                // Take the pair [decrease the count of 'diff']
                countTotal++;
                map.put(diff, countDifference - 1);
            }
            else{
                // Don't take the pair
                // only update current num's count
                map.put(num, countCurrentNum + 1);                
            }
            
        }
        
        return countTotal;
    }
}