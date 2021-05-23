public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {        
        
        int cnt_ones = ( (n&(1<<31)) != 0) ? 1 : 0; // sign bit present -> 1
        n = n & ~(1<<31); // reset the sign bit
        
        while(n != 0){
            
            if((n & 1) != 0){ // mask is always 1
                cnt_ones ++;
            }
            
            n = n >> 1;
        }
        
        return cnt_ones;
    }
}