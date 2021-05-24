class Solution {
    private boolean usingLoop(int n){
        if(n < 1){
            return false;
        }
        if(n == 1){
            return true;
        }
        
        n--; // n = n-1
        
        // 0000011111111
        
        // 0000100000000
        
        
        // check if all 1's
        while(n != 0){
            
            if((n & 1) == 0){
                break;
            }
            
            n = n >> 1;
        }
        
        return (n == 0);
    }
    public boolean isPowerOfTwo(int n) {
        if(n < 1){
            return false;
        }
        // if(n == 1){
        //     return true;
        // }
        return ((n & (n - 1)) == 0);
    }
}