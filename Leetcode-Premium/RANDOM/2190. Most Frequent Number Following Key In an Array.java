class Solution {
    public int mostFrequent(int[] nums, int keyGiven) {
        int n = nums.length;
        Map<Integer, Integer> counts = new HashMap<>();
        
        int maxNum = 0, maxCount = -1;
        for(int i=0; i<(n - 1); i++){
            if(nums[i] == keyGiven){
                int num = nums[i + 1];
                counts.put(num, counts.getOrDefault(num, 0) + 1);
                
                // update
                if(counts.get(num) > maxCount){
                    maxCount = counts.get(num);
                    maxNum = num;
                }
            }
        }
        
        // take the max
        // for(int key: counts.keySet()){
        //     int val = counts.get(key);
        //     if(val > maxCount){
        //         maxCount = val;
        //         maxNum = key;
        //     }
        // }
        
        return maxNum;
    }
}