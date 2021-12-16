public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        return forLoop(n);
    }
    private int forLoop(int n){
        int ans = 0;
        int temp = n;        
        int cnt = 0;
        int rightMostBit;
        while(cnt < 32){
            ans = ans << 1;
            rightMostBit = temp & 1;
            ans = ans | rightMostBit;
            temp = temp >> 1;
            cnt++;
        }
        
        return (ans);
    }
}