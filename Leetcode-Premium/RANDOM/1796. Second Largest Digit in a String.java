class Solution {
    private int returnDigit(char c){
        // checking is digit
        if((c >= '0') && (c <= '9')){
            return (int)(c - '0');
        }
        
        return -1;
    }
    
    public int secondHighest(String s) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        
        for(char c: s.toCharArray()){
            int digit = returnDigit(c);
            if(digit == -1)
                continue;
            
            if(digit > largest){
                secondLargest = largest;
                largest = digit;
            }
            else if((digit > secondLargest) &&
                   (digit < largest)){
                secondLargest = digit;
            }
        }
        
        return (secondLargest == Integer.MIN_VALUE) ? 
                        -1 : secondLargest;
    }
}