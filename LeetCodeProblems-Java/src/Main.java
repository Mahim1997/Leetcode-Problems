import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author viper
 */
public class Main {

    static void printArary(int []arr){
        System.out.println(
            Arrays.stream(arr)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","))
        );
    }

    public static void main(String[] args) {
//        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
//
//        Solution sol = new Solution();
//        sol.maximalRectangle(matrix);
        Integer[] arr = {1,2,3,4,5,6,7,8,9};
        int ans=0;

        List<Integer> list = new ArrayList<>(Arrays.asList(arr));
        System.out.println(list);

        int sum = 0;
        sum = list.stream().reduce(sum, Integer::sum);
        System.out.print(sum);
    }
}
