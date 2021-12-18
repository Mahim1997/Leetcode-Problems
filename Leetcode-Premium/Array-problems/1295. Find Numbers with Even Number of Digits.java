class Solution {
    public int findNumbers(int[] nums) {
        int cnt = 0;
        for(int n: nums){
            if(isEvenDigitUsingLog(n) == true){
                cnt++;
            }
        }
        return cnt;
    }
    
    private boolean isEvenDigitUsingLog(int n){
        int digits = (int)(Math.log10(n)) + 1;
        return (digits%2 == 0);
    }
    
    // Change using bit masking
    private boolean isEvenDigit(int n){
        int cnt=0;
        while(n > 0){
            cnt++;
            n/=10;
        }
        return (cnt%2 == 0);
    }
}