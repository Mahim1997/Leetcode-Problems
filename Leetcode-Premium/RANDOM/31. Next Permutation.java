class Solution {
    public void nextPermutation(int[] arr) {
        int n = arr.length;
        
        if(n == 1)
            return;
        
        int idxRightMost = -1;
        int rightMostElement = 0;
        
        for(int i=n-2; i>=0; i--){
            if(arr[i] < arr[i + 1]){
                idxRightMost = i;
                rightMostElement = arr[i];
                break;
            }
        }
        
        // not found
        if(idxRightMost == -1){
            // simply reverse entire array
            reverse(arr, 0, n-1);
            return;
        }
        
        // if it is the second last element, swap last two
        if(idxRightMost == (n - 2)){
            reverse(arr, n-2, n-1);
            return;
        }
        
        // find the SMALLEST num > arr[idxRightMost]
        int idxSmallestRighter = -1;
        int smallestRighter = Integer.MAX_VALUE;
        
        // for(int i=idxRightMost+1; i<n; i++){
        
        // start searching from rightmost side
        for(int i=n-1; i>idxRightMost; i--){
            if((arr[i] < smallestRighter)
              && (arr[i] > arr[idxRightMost])){
                smallestRighter = arr[i];
                idxSmallestRighter = i;
            }
        }
        
        
        // Not possible, because then descending order won't break
        if(idxSmallestRighter == -1){
            System.out.println("2nd min NOT EXISTS RIGHT SIDE !!!");
            return;
        }
        
        // WRONG LOGIC, shifting unnecessary
        // shifting to right side (start from right)
        // for(int i=idxSmallestRighter; i>idxRightMost; i--){
        //     arr[i] = arr[i - 1];
        // }
        
        // swapping
        arr[idxRightMost] = smallestRighter;
        arr[idxSmallestRighter] = rightMostElement;
        
        // reversing
        // sort(arr, idxRightMost+1, n-1);
        reverse(arr, idxRightMost+1, n-1);
    }
    
    // inclusive [low, high]
    private void reverse(int[] arr, int low, int high){
        int len = high - low + 1;
        for(int i=0; i<len/2; i++){
            int leftIdx = low + i;
            int rightIdx = high - i; // inclusive
            
            // swap
            int temp = arr[leftIdx];
            arr[leftIdx] = arr[rightIdx];
            arr[rightIdx] = temp;
        }
    }
}