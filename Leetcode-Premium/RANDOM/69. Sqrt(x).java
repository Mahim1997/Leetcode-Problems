class Solution {
    
    private int n;
    
    // x^2 = n  --> x - n/x = 0 --> f(x) = x - n/x
    private int f(int x){
        return x - (n / x);
    }
    
    private boolean areSameSigns(int a, int b){
        // can multiply and check, otherwise ... logical checking
        return (
            ((a <= 0) && (b <= 0)) ||
            ((a > 0) && (b > 0))
        );
    }
    
    // Bisection Method
    public int mySqrt(int n) {
        // Edge case of 0
        if(n <= 1){return n;} // 0 OR 1, edge cases
        if(n == 2){return 1;} // 2 will give division by ZERO problem
        
        this.n = n;
        
        // Previously started with [1, n]
        // Bisection Method [2, n] as starting interval
        // O(log n)
        int left = 2;
        int right = n;
        
        int fLeft = f(left);
        int fRight = f(right);
        
        int mid, fMid; //(left + right)/2;
        // stop when [mid, mid+1] changes sign --> mid is the answer
        while(true){
            // mid = (left + right)/2;
            // avoid overflow problems.
            mid = left + (right - left)/2;
            fMid = f(mid);
            
            int fMidPlusOne = f(mid + 1);
            int fMidMinusOne = f(mid - 1);
            
            // terminating condition ->
            // check if sign has reversed in [mid-1, mid] OR [mid, mid+1] interval
            
            if(!areSameSigns(fMid, fMidPlusOne)){
                return mid;
            }
            if(!areSameSigns(fMidMinusOne, fMid)){
                return (mid-1);    
            }
            
            // continue looping
            if(areSameSigns(fLeft, fMid)){
                // [mid, right] will be new interval
                left = mid;
                fLeft = fMid;
            }else{
                // [left, mid] will be new interval
                right = mid;
                fRight = fMid;
            }
        }
        
        // return -1;
    }
}