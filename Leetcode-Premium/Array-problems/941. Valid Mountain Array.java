class Solution {
    public boolean validMountainArray(int[] arr) {
        if(arr.length < 3)
            return false;
        
        if(arr.length == 3){
            if(arr[0] < arr[1]){
                if(arr[1] > arr[2]){
                    return true;
                }
            }
            return false;
        }
        
        int n = arr.length;
        int idx = 0;
        
        // go UP
        while(idx < (n - 1)){
            if(arr[idx] >= arr[idx + 1])
                break;
            idx++;
        }
        
        if(idx == 0) // can't be initial number
            return false;
        if(idx == (n - 1)) // can't be the last number
            return false;
        
        
        // go DOWN
        while(idx < (n - 1)){
            if(arr[idx] <= arr[idx + 1])
                break;
            idx++;
        }
            
        return (idx == (n - 1)); // should be the last index
    }
}