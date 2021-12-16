class Solution {
    private boolean doesOverflowOccurPositive(int num, int mod){
        return (num > Integer.MAX_VALUE/10) || 
            (((num == Integer.MAX_VALUE/10)) && (mod > (Integer.MAX_VALUE%10)));
    }
    private boolean doesOverflowOccurNegative(int num, int mod){
        return (num < Integer.MIN_VALUE/10) || 
            (((num == Integer.MIN_VALUE/10)) && (mod < (Integer.MIN_VALUE%10)));
    }
    
    public int reverse(int x) {
        if(x == 0)
            return 0;

        int rev = 0;
        int temp = x;
        while(temp!=0){
            int mod = temp%10;
            temp/=10;
            
            if((x < 0) && (doesOverflowOccurNegative(rev, mod))){
                return 0;
            }
            if((x > 0) && (doesOverflowOccurPositive(rev, mod))){
                return 0;
            }
            rev = rev*10 + mod;
        }
        
        return rev;
    }
}