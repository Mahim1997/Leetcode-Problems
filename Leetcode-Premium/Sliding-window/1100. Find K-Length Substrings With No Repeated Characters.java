class Solution {
    private boolean isValid = true;
    private int[] freq = new int[26];
    
    private int getIdx(char c){
        return (c - 'a');
    }
    
    private boolean hasValidity(){
        for(int x: this.freq){
            if(x > 1)
                return false;
        }
        return true;
    }
    
    private void increment(char c){
        // check if freq['c'] > 1 ---> false
        int idx = this.getIdx(c);
        this.freq[idx]++;
        
        if(this.freq[idx] > 1)
            this.isValid = false;
    }
    
    private void decrement(char c){
        int idx = this.getIdx(c);
        this.freq[idx]--;
        
        if(this.freq[idx] <= 1)
            this.isValid = true;
    }
    
    public int numKLenSubstrNoRepeats(String s, int k) {
        // k length window
        
        int strlen = s.length();
        char[] chars = s.toCharArray();
        
        if(strlen < k)
            return 0;
        
        Arrays.fill(this.freq, 0);
        
        int idx = 0;
        for(idx=0; idx<k; idx++){
            increment(chars[idx]);
        }
        
        int counter = 0;
        
        for(idx=k; idx<=strlen; idx++){
            if(this.hasValidity() == true){
                counter++;
            }
            decrement(chars[idx - k]);
    
            if(idx < strlen)
                increment(chars[idx]);
        }
        
        return counter;
    }
}