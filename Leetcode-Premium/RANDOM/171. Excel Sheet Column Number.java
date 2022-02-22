class Solution {
    private int getDigit(char c){
        return (c - 'A' + 1); // 'A' == 1, 'Z' == 26
    }
    
    public int titleToNumber(String columnTitle) {
        // base 26
        
        int num = 0;
        int strlen = columnTitle.length();
        for(int i=0; i<strlen; i++){
            char c = columnTitle.charAt(i);
            int digit = getDigit(c);
            num = num*26 + digit;
        }
        return num;
    }
}