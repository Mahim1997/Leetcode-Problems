
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    private void backtrack(List<List<Integer>> whole_ans,
            List<Integer> combinations, // one solution list.
            int[] candidates,
            int idx,
            int new_target) {
//        System.out.println("idx = " + idx + " , new_target = " + new_target);
        // Base case.
        if (new_target == 0) {
            whole_ans.add(new ArrayList<>(combinations));
            return;
        }

        // Recurse on each idx AND its neighbors
        for (int i = idx; i < candidates.length; i++) {
            int num = candidates[i];
            if (num > new_target) {
                break; // no need to continue looping.
            }
            // else recurse on using this ith index.
            combinations.add(candidates[i]); // push this element.

            // backtrack using THIS ith index as idx, and new target as removing ith candidate
//            new_target -= candidates[i]; // this doesn't give results correctly
            backtrack(whole_ans, combinations, candidates, i, new_target - candidates[i]);

            combinations.remove(combinations.size() - 1); // remove last element.
        }

    }

    // Using backtracking.
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return null;
        }

        Arrays.sort(candidates); // sort initially.
        List<List<Integer>> whole_ans = new ArrayList<>();
        List<Integer> combinations = new ArrayList<>();

        backtrack(whole_ans, combinations, candidates, 0, target);

        return whole_ans;
    }
}
