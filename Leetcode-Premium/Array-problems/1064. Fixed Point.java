class Solution {
    public int fixedPoint(int[] arr) {
        // return orderN(arr);
        
        // binary search ??
        return binarySearch(arr);
    }
    
    private int binarySearch(int[] arr){
        int n = arr.length;
        
        // edge case
        if(arr[0] == 0)
            return 0;

        
        int low = 0;
        int high = n - 1;
        
        int soFar = Integer.MAX_VALUE;
        
        while(low <= high){
            int mid = low + (high - low)/2;
            
            // System.out.println("low = " + low + ", "
            //     + "high = " + high + ", mid = " + mid
            //     + ", arr[low] = " + arr[low] + ", "
            //     + ", arr[high] = " + arr[high] + ", "
            //     + ", arr[mid] = " + arr[mid]);
            
            // found
            if(arr[mid] == mid){
                // return mid;
                soFar = Math.min(soFar, mid);
                // search left side, for smaller values
                high = mid - 1;
            }
            else if(arr[mid] < mid){
                // eg. idx = 5, arr[5] = 4
                // [-122, 0, 1, 2, 3, 4, .....]
                // impossible in left section i.e. search right
                low = mid + 1;
            }
            else if(arr[mid] > mid){
                // eg. idx = 5, arr[5] = 6
                // [-,-,-,-,-,6,7,....]
                // impossible in right section i.e. search left
                high = mid - 1;
            }
        }
        
        
        return (soFar == Integer.MAX_VALUE) ? -1 : soFar;
    }
    
    private int orderN(int[] arr){
        for(int i=0; i<arr.length; i++){
            if(arr[i] == i)
                return i;
        }
        return -1;
    }
}