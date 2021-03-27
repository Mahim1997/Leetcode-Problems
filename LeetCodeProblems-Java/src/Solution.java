import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
Given an array nums of distinct integers, return all the possible permutations.
You can return the answer in any order.

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */

/*
First we can sort.
Then, we can follow a tree-DFS order.

For each index, we can take that many elements.
Then remove that element, move to the forward index.
 */
class Solution {
    public void backtrack(List<List<Integer>> ans,
                          List<Integer> current_ans,
                          int index,
                          int []nums,
                          List<Integer> valid_nums){

        if(index == 0){
            current_ans = new ArrayList<>();
            for(int x: nums) valid_nums.add(x);
        }
        for(int i=0; i<nums.length; i++){
            current_ans.add(valid_nums.get(index));
            System.out.println("i = " + i + " , index = " + index + " , current_ans = " + current_ans
            + " , valid_nums = " + valid_nums);
//                valid_nums.remove(0);
            index++;
//                backtrack(ans, current_ans, index, nums, valid_nums);
        }
        if(index == nums.length - 1){
            ans.add(new ArrayList<>(current_ans));
            current_ans.clear();
        }
        System.out.println("ans = " + ans);


    }

    public List<List<Integer>> permute(int[] nums) {
        // Sort
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> current_ans = new ArrayList<>();
        List<Integer> valid_nums = new ArrayList<>();
//        for(int x: nums) valid_nums.add(x);

        backtrack(ans, current_ans, 0, nums, valid_nums);

        return ans;
    }
}