class Solution {
    
    private char[] getSum(char aChar, char bChar, char carryChar){
        char[] ans = new char[2];
        char sumChar = '0';
        // char carryChar = '0';
        if((aChar == '0') && (bChar == '0')){
            if(carryChar == '0'){
                sumChar = '0';
                carryChar = '0';
            }
            else{ // carry bit is '1'
                sumChar = '1';
                carryChar = '0';
            }
        }
        else if((aChar == '1') && (bChar == '1')){
            if(carryChar == '0'){
                sumChar = '0';
                carryChar = '1';
            }else{ // carry bit is '1'
                sumChar = '1';
                carryChar = '1';
            }
        }
        else{
            // any one is '1' and other is '0'
            if(carryChar == '0'){
                sumChar = '1';
                carryChar = '0';
            }else{
                sumChar = '0';
                carryChar = '1';
            }
        }
        ans[0] = sumChar;
        ans[1] = carryChar;
        return ans;
    }
    
    // Can use logic gates, S = a XOR b XOR C_in
    public String addBinary(String a, String b) {
        // 'a' is always smaller
        if(a.length() > b.length()){
            String temp = a;
            a = b;
            b = temp;
        }
        
        StringBuilder bld = new StringBuilder();
        
        // double pointers
        int sizeSmall = a.length();
        int sizeLarge = b.length();
        
        int idxSmall = sizeSmall - 1;
        int idxLarge = sizeLarge - 1;
        
        char sumChar = '0';
        char carryChar = '0';
        
        while(idxSmall >= 0){
            char aChar = a.charAt(idxSmall);
            char bChar = b.charAt(idxLarge);
            idxSmall--; idxLarge--;
            // System.out.println(">>> idx = " + idx + ", aChar = " + aChar + ", bChar = " + bChar);
            
            char[] ans = getSum(aChar, bChar, carryChar);
            sumChar = ans[0];
            carryChar = ans[1];
            
            // reverse order ... need to reverse later
            bld.append(sumChar); 
        }
        
        // for(idx=(sizeLarge-1); idx>=sizeSmall; idx--){
        while(idxLarge >= 0){
            
            char bChar = b.charAt(idxLarge);
            idxLarge--;
            
            char[] ans = getSum('0', bChar, carryChar);
            sumChar = ans[0];
            carryChar = ans[1];
            bld.append(sumChar);
        }
        
        if(carryChar == '1'){
            bld.append('1');
        }
        

        return bld.reverse().toString();
        
    }
}