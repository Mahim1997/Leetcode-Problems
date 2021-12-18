/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     public int get(int index) {}
 * }
 */

class Solution {
    static int MAX_LIMIT = 2147483647; // 2^31 - 1 
    static int MAX_SECRET_LENGTH = 10000; // 10^4
    
    private int getLeftMostIndex(ArrayReader reader, int target){
        int left = 0;
        // int right = MAX_SECRET_LENGTH - 1; // 10^4 is secret.length
        
        int right = target - reader.get(0) + 1; // O(1) for right most limit
        //WORST CASE: [-1,0,1,2,3,4,5] target=5 -> 5--1+1 = 7
        
        int mid;
        while(left <= right){
            mid = left + (right - left)/2;
            int currentVal = reader.get(mid);

            // NEED this condition: 
            // currentVal != MAX_LIMIT AND nextVal == MAX_LIMIT
            
            if(currentVal == target)
                return mid;
            
            if((currentVal == MAX_LIMIT) || 
                (target < currentVal)){ // search left side
                right = mid - 1;
            }
            else if(target > currentVal){ // current != MAX_LIMIT
                left = mid + 1;
            }
        }
        
        return -1;
    }
    
    public int search(ArrayReader reader, int target) {
        return getLeftMostIndex(reader, target);
        
    }
}