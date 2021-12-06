class Solution {
    int binarySearch(int arr[], int x, int l, int r)
    {
        // int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
 
            // Check if x is present at mid
            if (arr[m] == x)
                return m;
 
            // If x greater, ignore left half
            if (arr[m] < x)
                l = m + 1;
 
            // If x is smaller, ignore right half
            else
                r = m - 1;
        }
 
        // if we reach here, then element was
        // not present
        return -1;
    }
    
    int binarySearchLeft(int[] arr, int x, int l, int r){
        // eg. 1,2,3,4,4,4,4 ... all to the left will be THIS number.
        if(arr.length == 1)
            return l; // will always come here.
        
        while(l <= r){
            int m = (l + r)/2;
            if(arr[m] == x){
                r = m; // go to this step.
            }
            else if(arr[m] < x){
                l = m + 1; // go to one step right.
            }
            
            if(l == r)
                return l;
        }
        return -1;
    }
    
    int binarySearchRight(int[] arr, int x, int l, int r){
        // eg. 4,4,4,5,6,7,8
        if(arr.length == 1)
            return l;
        
        while(l <= r){
            int m = (int)Math.ceil((l + r)/2.0);
            if(arr[m] > x){
                r = m-1;
            }
            else if(arr[m] == x){
                l = m;
            }
            
            if(l == r)
                return l;
        }
        return -1;
    }
    
    public int[] searchRange(int[] nums, int target) {
        int[] arr = new int[2];
        arr[0] = -1; arr[1] = -1;

        if(nums.length == 0) // base case.
            return arr;
        
        // Left-Right inclusive.
        
        // first index finding.
        int idx = binarySearch(nums, target, 0, nums.length-1);
        
        if(idx == -1)
            return arr; // doesn't exist.
        
        int idx_left = binarySearchLeft(nums, target, 0, idx);
        if(idx_left == -1)
            return arr;
        arr[0] = idx_left;
        
        if(idx == (nums.length - 1)){ // this is the very last one.
            arr[1] = nums.length - 1; // store it as the last.
        }
        else{
            arr[1] = binarySearchRight(nums, target, idx, nums.length-1); // inclusive.
        }
        
        
        return arr;
    }
}