
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

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = {1,2,3};
//        int[] nums = {6,2,1,5,4,3,0};
//        int[] nums = {2,3,1};
//        int[] nums = {2,3,1,3,3};

        printArray(nums);

        solution.nextPermutation(nums);

//        System.out.println("After solution.nextPermutation() call. ");
        printArray(nums);

    }
}
