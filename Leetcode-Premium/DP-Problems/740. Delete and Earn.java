class Solution {
    
    List<Integer> listNumbers;
    int numItems;
    private int[] nums;
    
    Map<Integer, Integer> map; // cache
    Map<Integer, Integer> counts;
    
    public int deleteAndEarn(int[] nums) {
        initialize(nums);
        
        
        // return topDown(nums);
        return bottomUp();
    }
    
    void printArr(int[] arr){
        for(int x: arr){System.out.print(x + " ");}
        System.out.println("");
    }
    
    private int bottomUp(){
        System.out.println(this.listNumbers);
        
        // initialize(nums); // initialize ...
        
        int[] dp = new int[this.numItems];
        dp[0] = getCost(0);
        
        if(this.numItems == 1)
            return dp[0];
        
        for(int i=1; i<this.numItems; i++){
            int prevIdx = i - 1;
            if((i >= 1) && 
               (this.listNumbers.get(i) 
                == (this.listNumbers.get(i - 1) + 1))){
                // can't take one next idx
                prevIdx = i - 2;
            }
            int prevMaxProfit;
            if(prevIdx < 0){
                prevMaxProfit = 0;
            }else{
                prevMaxProfit = dp[prevIdx];
            }
            int toTake = getCost(i) + prevMaxProfit;
            int notTake = 0 + dp[i - 1];
            dp[i] = Math.max(toTake, notTake);
        
            // printArr(dp); // DEBUGGING
        }
        
        return dp[this.numItems - 1];
    }
    
    private void initialize(int[] nums){
        this.nums = nums;
        Arrays.sort(this.nums); // sort the numbers
        
        // maintain counts
        this.counts = new HashMap<>();
        this.listNumbers = new ArrayList<>();
    
        // hash map does not maintain order of keys
        for(int num: nums){
            if(this.counts.containsKey(num) == false){
                this.listNumbers.add(num);
            }
            this.counts.put(num, 
                            this.counts.getOrDefault(num, 0) + 1);
        }
    
        this.numItems = this.listNumbers.size();
    }
    
    private int topDown(int[] nums){
        initialize(nums);
        
        this.map = new HashMap<>();
        
        // Just like, House Robber problem.
        return dfs(this.numItems - 1);
    }
    
    private int getCost(int idx){
        int num = this.listNumbers.get(idx); 
        return num * this.counts.get(num);
    }
    
    // dp(i) = MAX[dp(i - 1) + 0, dp(i - 2) + cost[i]]
    private int dfs(int idx){
        // System.out.println("Calling for idx = " + idx);
        
        // base case
        if(idx == 0){
            return getCost(idx); 
        }
        if(idx < 0)
            return 0;
        
        // check map
        if(this.map.containsKey(idx)){
            return this.map.get(idx);
        }
        
        int ans;
        // take IT
        int prevIdx = idx - 1;
        if(idx > 0){
            // CANNOT take previous index
            boolean prevWasOneLess = listNumbers.get(idx) 
                            == (listNumbers.get(idx - 1) + 1);
            // curr == prev + 1
            if(prevWasOneLess)
            {
                prevIdx = idx - 2;
            }
        }
        int costTake = dfs(prevIdx) + getCost(idx);
        
        // don't take IT
        int costNotTake = dfs(idx - 1) + 0; // not take
        
        
        ans = Math.max(costTake, costNotTake);
        
        // System.out.println(">> For idx = " + idx + ", ans = " + ans);
        // insert into map
        this.map.put(idx, ans);
        return ans;
    }
    
}