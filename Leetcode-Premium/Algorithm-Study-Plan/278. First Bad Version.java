/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        int mid;
        
        while(left < right){ // left < right -> inclusive case
            mid = left + (right - left)/2;
            
            if(isBadVersion(mid)){
                right = mid; // don't go to mid-1, due to inclusive
            }else{
                left = mid + 1;
            }
            
            
            // need this condition, arr[mid] == true && arr[mid - 1] == false
            // if(isBadVersion(mid) == true){
            //     if(isBadVersion(mid - 1) == false){
            //         return mid;
            //     }else{
            //         // need to search left side.
            //         right = mid - 1;
            //     }
            // }else{ // mid is NOT bad, need to search right side.
            //     left = mid + 1;
            // }
        }
        
        return left;
    }
}

/*
5
4
1
1
10
10
10
9
10
3
55
54
*/