class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int m = nums1.length;
        int n = nums2.length;
        
        int idx1 = 0;
        int idx2 = 0;
        
        while((idx1 < m) && (idx2 < n)){
            if(nums1[idx1] == nums2[idx2]){
                list.add(nums1[idx1]);
                idx1++;
                idx2++;
            }
            else if(nums1[idx1] > nums2[idx2]){
                idx2++;
            }
            else if(nums2[idx2] > nums1[idx1]){
                idx1++;
            }
        }
        
        int[] ans = new int[list.size()];
        
        int idx = 0;
        for(int x: list){
            ans[idx++] = x;
        }
        
        return ans;
    }
}