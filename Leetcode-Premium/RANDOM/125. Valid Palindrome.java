class Solution {
    private boolean isSmallLetter(char c){
        return ((c >= 'a') && (c <= 'z'));
    }

    private char getLowerCase(char c){
        return (char) (c - 'A' + 'a');
    }
    
    private boolean isCapitalLetter(char c){
        return ((c >= 'A') && (c <= 'Z'));
    }
    
    private boolean isNumber(char c){
        return ((c >= '0') && (c <= '9'));
    }
    
    public boolean isPalindrome(String s) {
        StringBuilder bld = new StringBuilder();
        
        for(char c: s.toCharArray()){
            if(isSmallLetter(c) || isNumber(c)){
                bld.append(c);
            }
            else if(isCapitalLetter(c)){
                bld.append(getLowerCase(c));
            }
            // anything else, ignore
        }
        
        String converted = bld.toString();
        
        // check if empty string
        if(converted.isEmpty() || converted.isBlank())
            return true;
        
        char[] chars = converted.toCharArray();
        int len = chars.length;
        
        for(int i=0; i<(len/2); i++){
            int last = len - 1 - i;
            if(chars[i] != chars[last])
                return false;
        }
        
        return true;
    }
}