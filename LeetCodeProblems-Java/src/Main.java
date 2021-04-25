import java.util.*;

/**
 *
 * @author viper
 */
public class Main {

    public static void main(String[] args) {
        String s = "[[1,11],[2,12],[3,13],[4,14],[5,15],[6,16],[7,17],[8,18],[9,19],[10,20]]";
        s = Utils.getMatrixRepresentation(s);
        System.out.println(s);

        int[][] matrix = Utils.get2DMatrixSequential(10, 2);//{{1,2,3},{4,5,6},{7,8,9}}}
        Utils.printMatrix(matrix);

        System.out.println(Utils.getMatrixLeetcodeForm(matrix));

        System.out.println("\n\n");
        List<Integer> list = new Solution().spiralOrder(matrix);
        Utils.printSingleList(list);
    }
}
//[11,12,13,14,15,16,17,18,26,34,42,50,58,57,56,55,54,53,52,51,43,35,27,19,
// 20,21,22,23,24,25,33,41,49,48,47,46,45,44,36,28,
// 29,30,31,32,40,39,38,37]