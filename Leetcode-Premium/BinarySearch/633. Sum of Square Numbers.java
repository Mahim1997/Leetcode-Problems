class Solution {
    
    private boolean isPerfectSquare(int num){
        if(num < 0)
            return false;
        if(num <= 1) // 0, 1
            return true;
        if(num <= 3) // 2, 3
            return false;
        
        int left = 1;
        int right = num/2;
        
        while(left <= right){
            int mid = left + (right - left)/2;
            
            // double squareMid = mid*mid; // overflow
            double dividedBy = (double)num/(double)mid;
            
            // System.out.println("left = " + left + ", right = " + right + ", mid = " + mid + ", db = " + dividedBy);
            
            if(mid == dividedBy)
                return true;
            
            if(mid < dividedBy){
                // go right
                left = mid + 1;
            }
            else{
                // go left
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    public boolean judgeSquareSum(int c) {
        // edge cases
        if((c == 0) || (c == 1))
            return true;
        
        if(isPerfectSquare(c))
            return true; // 0^2 + 
        
        // go with for loop
        int rootC = (int)Math.sqrt(c);
        for(int num1=1; num1<=rootC; num1++){
            int remaining = c - (num1*num1); // c - num^2
            if(isPerfectSquare(remaining))
                return true;
        }
        
        return false;
    }
}

