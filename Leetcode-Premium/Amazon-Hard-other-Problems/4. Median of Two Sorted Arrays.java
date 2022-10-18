class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int aLen = nums1.length, bLen = nums2.length;
        int low = 0, high = aLen;
        int total = aLen + bLen;
        int half = (total + 1)/2;
        
        // System.out.println("aLen = " + aLen + ", bLen = " + bLen);
        
        while(low <= high) { // guaranteed to have a median
            int aPart = low + (high - low)/2;
            int bPart = half - aPart;
            
            // System.out.println("aPart = " + aPart + ", bPart = " + bPart);
            
            // Compute boundary values
            int aLeft = ((aPart - 1) >= 0) ? nums1[aPart - 1] : Integer.MIN_VALUE;
            int aRight = (aPart < aLen) ? nums1[aPart] : Integer.MAX_VALUE;
            
            int bLeft = ((bPart - 1) >= 0) ? nums2[bPart - 1] : Integer.MIN_VALUE;
            int bRight = (bPart < bLen) ? nums2[bPart] : Integer.MAX_VALUE;
            
            // System.out.println("aLeft = " + aLeft + ", bLeft = " + bLeft + ", aRight = " + aRight + ", bRight = " + bRight);
            
            // Compare and proceed
            if((aLeft <= bRight) && (bLeft <= aRight)) { // Found
                if((total%2) != 0) {
                    // odd -> right side
                    return Math.max(aLeft, bLeft); 
                }
                else {
                    // even -> average
                    return 0.5*(Math.max(aLeft, bLeft) + Math.min(aRight, bRight));
                }
            }
            else if(aLeft > bRight) {
                // need to go left
                high = aPart - 1;
            }
            else {
                // need to go right
                low = aPart + 1;
            }
        }
        
        return -1; // guaranteed to be a median
    }
}