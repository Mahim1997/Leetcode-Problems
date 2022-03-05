class Solution {
    public int deleteAndEarn(int[] nums) {
        // return bottomUp(nums);
        return bottomUpOptimizedSpace(nums);
    }
    
    private Map<Integer, Integer> getCounts(int[] nums){
        Map<Integer, Integer> counter = new HashMap<>();
        for(int num: nums){
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        return counter;
    }
    
    private List<Integer> getSortedKeys(Map<Integer, Integer> counter){
        List<Integer> keys = new ArrayList<>(); // counter.keySet();
        
        // sort  to maintain ordering.
        for(int key: counter.keySet()){keys.add(key);}
        keys.sort((x1, x2) -> (x1 - x2)); // ascending order sorting
        return keys;
    }
    
    private int bottomUpOptimizedSpace(int[] nums){
        Map<Integer, Integer> counter = getCounts(nums);
        List<Integer> keys = getSortedKeys(counter);
        
        int lenUnique = keys.size();
        
        // edge cases: of 0 len, 1 len
        
        // dp[0]
        int twoBefore = keys.get(0) * counter.get(keys.get(0));
        
        // EDGE CASE: size == 1
        if(lenUnique == 1){
            return twoBefore;
        }
        
        // dp[1] = take both || MAX(THIS, dp[0])
        int oneBefore = 0;
        
        if((keys.get(1) - 1) != keys.get(0)){
            // dp[1] = dp[0] + THIS
            oneBefore = keys.get(1) * counter.get(keys.get(1)) + twoBefore;
        }else{
            // dp[1] = MAX(dp[0], THIS)
            oneBefore = Math.max(
                twoBefore,
                keys.get(1) * counter.get(keys.get(1))
            );
        }
        
        // EDGE CASE: size == 2
        if(lenUnique == 2){
            return oneBefore;
        }
        
        int currentAnswer = 0;
        // recursive case
        for(int i=2; i<lenUnique; i++){
            if((keys.get(i) - 1) != keys.get(i - 1)){
                // can take both
                currentAnswer = oneBefore + 
                                keys.get(i) * counter.get(keys.get(i));
            }
            else{
                // can't take --> take max
                // dp[i] = MAX(dp[i-1] + 0, dp[i-2] + COUNT(i))
                currentAnswer = Math.max(
                    oneBefore + 0,
                    twoBefore + keys.get(i) * counter.get(keys.get(i))
                );
            }
            
            // update values
            twoBefore = oneBefore;
            oneBefore = currentAnswer;
        }
        
        return currentAnswer;
    }
    
    private int bottomUp(int[] nums){
        Map<Integer, Integer> counter = getCounts(nums);
        List<Integer> keys = getSortedKeys(counter);
        // house robber technique
        int lenUnique = keys.size();
        
        // dp array: ends at idx 'i'
        int[] dp = new int[lenUnique];
        
        // base case
        int zeroElement = keys.get(0);
        dp[0] = zeroElement * counter.get(zeroElement);
        
        // recursive case
        for(int i=1; i<lenUnique; i++){
            int currElement = keys.get(i), prevElement = keys.get(i - 1);
            
            int currPoints = currElement*counter.get(currElement);
            
            if((currElement - 1) != prevElement){
                // check if can take both
                dp[i] = dp[i - 1] + currPoints;
                
            }
            else{
                // else, take max (EXTRA checking for i'th index?)
                if(i == 1){
                    dp[i] = Math.max(currPoints, dp[i - 1]);
                    continue;
                }
                dp[i] = Math.max(
                    dp[i - 1] + 0,          // can't take current points
                    dp[i - 2] + currPoints  // can take current with keys[i - 2]
                );
            }
            
        }
        
        return dp[lenUnique - 1];
    }
}