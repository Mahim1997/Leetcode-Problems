
import java.util.List;

/**
 *
 * @author viper
 */
public class Main {

    public static void printSingleList(List<Integer> list) {
        list.forEach((x) -> {
            System.out.print(x + " ");
        });
        System.out.println("");
    }

    public static void printMultiList(List<List<Integer>> list) {
        list.forEach((x) -> {
            printSingleList(x);
        });
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> answers = solution.combinationSum(candidates, target);
        Main.printMultiList(answers);
    }
}
