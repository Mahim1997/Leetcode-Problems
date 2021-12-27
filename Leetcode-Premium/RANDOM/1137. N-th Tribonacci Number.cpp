class Solution {
public:
    map<int, int> cache;
    
    int tribonacci(int n) {
        // T(n) = T(n - 1) + T(n - 2) + T(n - 3)
        // T(0) = 0, T(1) = 1, T(2) = 1
        
        // return topDown(n);
        // return bottomUp(n)
        return bottomUpConstantSpace(n);
    }
    
    int topDown(int n){
        // base cases.
        if(n == 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 1;
        
        // check in cache, if exists, return it.
        auto itr = cache.find(n);
        if(itr != cache.end()){
            return itr->second;
        }
        
        // recursion compute
        int ans = topDown(n - 1) + topDown(n - 2) + topDown(n - 3);
        
        // insert into cache
        cache.insert(pair<int, int>(n, ans));
        
        return ans;
    }
    
    int bottomUp(int n){
        if(n == 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 1;
        
        int dp[n + 1];
        // Base case
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        
        // Recursion -> Iteration
        for(int i=3; i<=n; i++){
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        
        return dp[n];
    }
	
    int bottomUpConstantSpace(int n){
        if(n == 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 1;
        
        int onePrev = 1;
        int twoPrev = 1;
        int threePrev = 0;
        
        int ans;
        
        for(int i=3; i<=n; i++){
            ans = onePrev + twoPrev + threePrev;
            
            threePrev = twoPrev;
            twoPrev = onePrev;
            onePrev = ans;
        }
        
        return ans;
    }
    
};