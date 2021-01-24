
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {1, 2, 3, 10, 11};
        int[] nums2 = {};
        System.out.println("Median: " + sol.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSingleArr(int[] arr) {
        int len = arr.length;
        if (len % 2 == 0) { // even len.
            return 0.5 * (arr[len / 2] + arr[(len - 1) / 2]);
        } else { // odd len.
            return arr[len / 2];
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return findMedianSingleArr(nums2);
        } else if (nums2.length == 0) {
            return findMedianSingleArr(nums1);
        }
        
        

        return 1;
    }
}
