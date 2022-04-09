class Solution {
    public int minProductSum(int[] nums1, int[] nums2) {
        // sort nums1 by ascending
        Arrays.sort(nums1);
        
        // sort nums2 by descending [ascending but reverse order]
        Arrays.sort(nums2);
        
        // get the minimum product by multiplying
        int product = 0;
        
        int n = nums1.length; // also, == nums2.length
        for(int i=0; i<n; i++){
            product += (nums1[i] * nums2[n - 1 - i]);
        }
        
        return product;
    }
}