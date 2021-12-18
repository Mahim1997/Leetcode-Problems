public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int cnt = 0;
        int numOnes = 0;
        while(cnt < 32){
            if((n & 1) == 1)
                numOnes++;
            n = n >> 1;
            cnt++;
        }
        
        return numOnes;
    }
}