class Solution {
    public int findComplement(int num) {
        int ans = 0;
        int mask; // = 1 << 31;
        
        // First flip all the bits
        ans = ~num;
        
        // remove leading 1's
        for(int pos=31; pos>=0; pos--){
            mask = 1 << pos;
            // System.out.println(Integer.toBinaryString(mask));
            if((ans & mask) == 0){
                // System.out.println("Breaking for pos = " + pos);
                break;
            }else{
                ans = ans & ~mask;
            }
        }
        
        // System.out.println(Integer.toBinaryString(ans));
        
        return ans;
    }
}