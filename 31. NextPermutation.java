import java.util.Arrays;
import java.util.stream.Collectors;

// Next Permutation Problem

class Solution {

    int getRightMostIndexUnsorted(int[] nums){
        for(int i=nums.length-1; i>=1; i--){
            int num_right = nums[i];
            int num_left = nums[i-1];
            if(num_left < num_right) {
                return i;
            }
        }
        return 0;
    }

    private void swap(int[] nums, int idx1, int idx2){
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    // Inclusive of indices.
    private void reverse(int[] nums, int first, int last){
        for(int i=first; i<=(first + last)/2; i++){
            swap(nums, i, (last - i + first));
        }
    }

    // Indices inclusive. [Start comparison from right-side, corner cases with equal elements are caught]
    int getRightMostClosestMax(int[] nums, int first, int last, int num_comparison){
        int idx_closest_max = first;
        int diff_smallest = Integer.MAX_VALUE;
//        for(int i=first; i<=last; i++){
        for(int i=last; i>=first; i--){
            if(nums[i] > num_comparison){
                int diff_curr = nums[i] - num_comparison;
                if(diff_curr < diff_smallest){
                    diff_smallest = diff_curr;
                    idx_closest_max = i;
                }
            }
        }
        return idx_closest_max;
    }

    public void nextPermutation(int[] nums) {
        if(nums.length <= 1) return; // one element array.

        // Get the index of element from right side which breaks the order.
        // eg. 6,2,1,5,4,3,0 -> idx with 5 contains the sorted items in opposite order.
        int idx_unsorted = this.getRightMostIndexUnsorted(nums);
//        System.out.println("[f 1] nextPermutation(), idx_unsorted = " + idx_unsorted);

        // The case of max. permutation eg. 3,2,1
        if(idx_unsorted == 0){ // Simply reverse (i.e. sort in ascending order)
            reverse(nums, 0, nums.length - 1);
            return;
        }

        // Get the closest maximum of the left-num, to the right-array.
        int num_left_side = nums[idx_unsorted - 1];
        int idx_closest_max = getRightMostClosestMax(nums, idx_unsorted, nums.length - 1, num_left_side);
//        System.out.println("[f 2] nextPermutation(), idx_closest_max = " + idx_closest_max + " , num_left = " + num_left_side);

        // Swap one to the left, and this closest_max
        swap(nums, idx_unsorted-1, idx_closest_max);

//        System.out.println("After first swap, arr: \n" +
//                Arrays.stream(nums).
//                mapToObj(x -> String.valueOf(x)).
//                collect(Collectors.joining(", "))
//        );


        // Reverse from idx_unsorted to the right.
        reverse(nums, idx_unsorted, nums.length - 1);
    }
}