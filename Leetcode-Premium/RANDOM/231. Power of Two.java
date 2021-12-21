class Solution {
    public boolean isPowerOfTwo(int n) {
        /*
        double log = Math.log(n)/Math.log(2);
        double ceil = Math.ceil(log);
        double floor = Math.floor(log);
        return (Math.ceil(log) == Math.floor(log));
        */
        // Power of 2 -> Only one bit is available.
        int numBitsOne = 0;
        while(n > 0){
            if((n&1) == 1){ // this bit is '1'
                numBitsOne++;
            }
            n = n >> 1;
        }
        return (numBitsOne == 1);
    }
}