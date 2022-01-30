class Solution {
    
    public boolean isStrobogrammatic(String num) {
        int strlen = num.length();
        
        if(strlen%2 != 0){ // odd number
            char middle = num.charAt(strlen/2);
            if(!isValidMiddle(middle)){return false;}
        }
        
        for(int i=0; i<strlen/2; i++){
            int i2 = strlen - 1 - i;
            if(getReversal(num.charAt(i)) != num.charAt(i2)){
                return false;
            }
        }
        
        return true;
    }
    
    /*
        map.put('6', '9');
        map.put('9', '6');
        map.put('8', '8');
        map.put('1', '1');
        map.put('0', '0');
    */
    private char getReversal(char c){
        if(c == '6'){return '9';}
        if(c == '9'){return '6';}
        if((c == '8') || (c == '1') || (c == '0')){return c;}
        return '$';
    }
    private boolean isValidMiddle(char c){
        return ((c == '8') || (c == '1') || (c == '0'));
    }
    
//     private String getReversalNumber(String num){
//         int strlen = num.length();
//         StringBuilder bld = new StringBuilder();
        
//         for(int i=strlen-1; i>=0; i--){
//             char c = num.charAt(i);
//             char rev = getReversal(c);
//             if(rev == '$')
//                 return null;
//             bld.append(rev);
//         }
        
//         return bld.toString();
//     }


}