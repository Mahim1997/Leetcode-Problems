class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n == 0)
            return;
        if(m == 0){
            for(int i=0; i<nums2.length; i++){
                nums1[i] = nums2[i];
            }
            return;
        }
        
        // Iterate from backwards.
        int idxBackResult = nums1.length - 1;
        int idx1 = m - 1;
        int idx2 = n - 1;
        
        while((idx1 >= 0) && (idx2 >= 0)){
            if(nums1[idx1] > nums2[idx2]){
                nums1[idxBackResult] = nums1[idx1];
                idx1--;
            }else{
                nums1[idxBackResult] = nums2[idx2];
                idx2--;
            }
            idxBackResult--;
        }
        
        
        while(idx1 >= 0){
            nums1[idxBackResult--] = nums1[idx1--];
        }
        
        while(idx2 >= 0){
            nums1[idxBackResult--] = nums2[idx2--];
        }
    }
}