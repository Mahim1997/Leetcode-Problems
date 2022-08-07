class Solution {
    public int[] sortArray(int[] nums) {
        // Merge sort
        int n = nums.length;
        
        // Base case
        if(n == 1) {
            return nums;
        }
        
        // Recursive DnC
        int half = n/2;
        int[] left  = sortArray(Arrays.copyOfRange(nums, 0, half)); // [left, right)
        int[] right = sortArray(Arrays.copyOfRange(nums, half, n));        
        return merge(left, right);
    }
    
    private int[] merge(int[] left, int[] right) {
        int n1 = left.length, n2 = right.length;
        int[] arr = new int[n1 + n2];
        int i1 = 0, i2 = 0;
        int idx = 0;
        
        while((i1 < n1) && (i2 < n2)) {
            if(left[i1] < right[i2]) {
                arr[idx] = left[i1];
                i1++;
            }
            else {
                arr[idx] = right[i2];
                i2++;
            }
            idx++;
        }
        
        // Maintain remaining ones
        while(i1 < n1) {
            arr[idx++] = left[i1++];
        }
        while(i2 < n2) {
            arr[idx++] = right[i2++];
        }
        return arr;
    }
}










