class Solution {
    public int[] countBits(int n) {
        return usingRightMostSetBitPattern(n);
        // return usingLeftShiftPattern(n);
    }
    
    private int[] usingRightMostSetBitPattern(int n){
        int[] ans = new int[n + 1];
        ans[0] = 0;
        
        for(int x=1; x<=n; x++){
            // eg. x    = 0011101011010 1 000000
            // eg. x-1  = 0011101011010 0 111111
            // x&(x-1)  = 0011101011010 0 000000
            // Hence, numOnes(x) = numOnes(x&(x-1)) + 1
            // the rightmost set bit is cleared, so needs extra 1
            int rightMostOneBitClearNum = x & (x - 1);
            ans[x] = ans[rightMostOneBitClearNum] + 1;
        }
        
        return ans;
    }
    
    private int[] usingLeftShiftPattern(int n){
        /*
            Any number can be written as 2i if even, or 2i+1 if odd
            When even, simply left shift (same #1's as num/2)
            When odd, left shift with OR 1 (#1's as num/2 + 1)
        */  
        int[] ans = new int[n + 1];
        ans[0] = 0; // base case
        for(int i=1; i<=n; i++){    // n times
            // ans[i] = getNumOneBits(i);  // O(32) i.e. O(1)
            
            ans[i] = ans[i/2];
            if((i%2) == 1) // odd --> add extra 1
                ans[i]++;
        }
        
        return ans;
    }
    
    private int getNumOneBits(int num){
        // calculate the number of '1' bits
        int temp = num;
        int numOnes = 0;
        while(temp > 0){
            // check last bit
            if((temp & 1) == 1)
                numOnes++;
            
            // right shift
            temp = temp >> 1;
        }
        
        return numOnes;
    }
}