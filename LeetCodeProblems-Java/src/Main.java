import java.util.*;

/**
 *
 * @author viper
 */
public class Main {

    public static void main(String[] args) {
        String s = "[[1]]";
        s = Utils.getMatrixRepresentation(s);
        System.out.println(s);

        int[][] matrix = {{1}};
        int target = 1;

        Utils.printMatrix(matrix);
        boolean b = new Solution().searchMatrix(matrix, target);
        System.out.println(b);
    }
}
