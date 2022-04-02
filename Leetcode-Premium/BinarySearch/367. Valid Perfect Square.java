class Solution {
    public boolean isPerfectSquare(int num) {
        // Edge cases
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
        
        // linear -> 
        /*
            for(int i=1; i<(num/2); i++){
                if(i*i == num)
                    return true;
            }
            return false;
        */
    }
}