import java.util.*;

/**
 *
 * @author viper
 */
public class Main {

    public static void main(String[] args) {
//        Solution sol = new Solution();
//
//        int[] nums = {1,2,3};
//        List<List<Integer>> ans = sol.permute(nums);
//        Utils.printMultipleList(ans);

        List<List<Integer>> list = new ArrayList<>();
        List<Integer> l = Arrays.asList(new Integer[]{1,2,3,4,5});
        List<Integer> l1 = Arrays.asList(new Integer[]{3,4,5});
        List<Integer> l2 = Arrays.asList(new Integer[]{2,3,4,5});
        list.add(l);
        list.add(l1);
        list.add(l2);
        Utils.printMultipleList(list);
    }
}
