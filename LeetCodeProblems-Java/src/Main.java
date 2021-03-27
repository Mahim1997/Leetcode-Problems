import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author viper
 */
public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums = {1,2,3};
        List<List<Integer>> ans = sol.permute(nums);
        Utils.printMultipleList(ans);
    }
}
