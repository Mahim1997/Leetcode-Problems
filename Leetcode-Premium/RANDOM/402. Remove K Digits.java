class Solution {
    public String removeKdigits(String num, int k) {
        return getString(num, k);
    }
    
    private String removeTrailingZeros(String num){
        StringBuilder bld = new StringBuilder();
        
        int strlen = num.length();
        boolean initialZero = false;
        for(int i=0; i<strlen; i++){
            char c = num.charAt(i);
            if((initialZero == false) && (c == '0'))
                continue;
            
            initialZero = true;
            bld.append(c);
        }
        
        return bld.toString();
    }
    
    private String getString(String num, int k){
        
        num = removeTrailingZeros(num);
        int strlen = num.length();
        // base cases
        if(strlen == 0){
            return "0";
        }
        if(k == 0){
            return num;
        }
        
        // Get the peak's index
        int idx = 0;
        boolean allSorted = true;
        for(int i=0; i<(strlen - 1); i++){
            if(num.charAt(i+1) < num.charAt(i)){
                idx = i;
                allSorted = false;
                break;
            }
        }
        
        // [0, idx - 1] + [idx + 1, strlen]
        String newNum = num.substring(0, idx) + 
                        num.substring(idx + 1, strlen);
        
        // ALL ARE SORTED && peak is at the very end
        if((allSorted == true) && 
           (num.charAt(strlen - 1) > num.charAt(0) )){
            
            // remove the last character only
            newNum = num.substring(0, strlen - 1);            
        }
        
        
        // recursive
        return getString(newNum, k - 1);
    }
}