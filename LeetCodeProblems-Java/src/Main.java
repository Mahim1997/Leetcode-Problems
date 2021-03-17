
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static void printArray(int []arr){
        System.out.println(Arrays.stream(arr).
                mapToObj(x -> String.valueOf(x)).
                collect(Collectors.joining(", ")));
    }


    public static void printMultiList(List<List<Integer>> list) {
        list.forEach((x) -> {
            printSingleList(x);
        });
    }

    public static void printMultipleList(List<List<Integer>> ans){
        ans.forEach(list -> printSingleList(list));
                // list.stream().map(x -> String.valueOf(x)).collect(Collectors.joining(", ")));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int []nums = {10,1,2,7,6,1,5};
        int target = 8;

        printArray(nums);
        System.out.println("Target: " + target);

        List<List<Integer>> ans = solution.combinationSum2(nums, target);

        printMultipleList(ans);

    }
}
