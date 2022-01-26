class Solution {
    /*  a -> e
        e -> a, i
        i -> a,e,o,u
        o -> i,u
        u -> a
    */
    
    /*
        a: e,i,u
        e: a,i
        i: e,o
        o: i
        u: i,o
    */
    
    int MOD = 1000000000+7;
    
    public int countVowelPermutation(int n) {
        if(n == 1)
            return 5;
        
        long[] prevArr = new long[5];
        Arrays.fill(prevArr, 1); // all 1's
        
        long[] currentArr = new long[5];
        for(int itr=2; itr<=n; itr++){
            currentArr[0] = (prevArr[1] + prevArr[2] + prevArr[4])%MOD;
            currentArr[1] = (prevArr[0] + prevArr[2])%MOD;
            currentArr[2] = (prevArr[1] + prevArr[3])%MOD;
            currentArr[3] = (prevArr[2])%MOD;
            currentArr[4] = (prevArr[2] + prevArr[3])%MOD;
            
            // replace prevArr <- currArr
            for(int i=0; i<5; i++){
                prevArr[i] = currentArr[i];
            }
        }
        
        // sum.
        long sum = 0;
        
        for(long x: currentArr){
            sum = (sum + x)%MOD;
        }
        
        // sum = sum%MOD;
        
        return (int) sum;
    }
}