import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author viper
 */
// CTRL + ALT + L -> Indent

public class Main {


    public static void main(String[] args) {
        
        int[][] matrix = Utils.generateMatrixFromStringLeetcode("[[-6,-1],[3,1],[12,3]]");
        Utils.printMatrix(matrix);

        int ans = new Solution().maxPoints(matrix);
        System.out.println(ans);

    }
}
