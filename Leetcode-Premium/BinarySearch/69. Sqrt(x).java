class Solution {
    boolean areSameSigns(int num1, int num2){
        return ((num1 > 0) && (num2 > 0))
            || ((num1 <= 0) && (num2 <= 0));
    }
    
    public int mySqrt(int xInt) {
        if(xInt == 0){return xInt;} // edge case
        // if(xInt == 2){return 1;} // edge case
        // if(xInt == 3){return 1;}
        
        long x = (long) xInt;
        long l = 1, r = (long) x/2; // should be smaller than /2
        
        while(l < r){
            long m = l + (r - l)/2;
            
            long prod = m*m;
            
            // breaking condition 1
            if(prod == x){return (int) m;} // found exact root
            
            long prodPlus1 = (m+1)*(m+1);
            
            // breaking condition 2
            if((prod < x) && (prodPlus1 > x)){
                // System.out.println("prod = " + prod + ", prod+1 = " + prodPlus1 + ", m = " + m);
                return (int) m;
            }
            
            
            if(prod > x){ // search left side
                r = m - 1;
            }else{
                l = m + 1;
            }
            
        }
        
        return (int) l;
    }
}