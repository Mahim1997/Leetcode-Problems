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
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};

        Solution sol = new Solution();
        sol.maximalRectangle(matrix);
    }
}
