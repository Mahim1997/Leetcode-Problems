class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        return (x == reverseNumber(x));
    }
    
    int reverseNumber(int x){
        int temp = x, mod;
        int ans = 0;
        
        while(true){
            mod = temp%10;
            ans += mod;
            temp /= 10;
            if(temp == 0)
                break;
            ans *= 10;
        }
        
        return ans;
    }
}