import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author viper
 */
// CTRL + ALT + L -> Indent

public class Main {
    static void analyze(int[][] matrix){
        Map<Integer, List<Integer[]>> map = new HashMap<>();

        for(int i=0; i<matrix.length; i++){
            int x = matrix[i][0];
            map.putIfAbsent(x, new ArrayList<>());
            Integer[] arr = {matrix[i][0], matrix[i][1]};
            map.get(x).add(arr);
        }

        for(int key: map.keySet()){
            System.out.println(key + ": " +
                    map.get(key).stream().map(x -> "(" + new String(String.valueOf(x[0]) + "," + String.valueOf(x[1])) + ")")
                    .collect(Collectors.joining(", "))

            );
        }
    }

    public static void main(String[] args) {
        
        int[][] matrix = Utils.generateMatrixFromStringLeetcode("[[7,3],[19,19],[-16,3],[13,17],[-18,1],[-18,-17],[13,-3],[3,7],[-11,12],[7,19],[19,-12],[20,-18],[-16,-15],[-10,-15],[-16,-18],[-14,-1],[18,10],[-13,8],[7,-5],[-4,-9],[-11,2],[-9,-9],[-5,-16],[10,14],[-3,4],[1,-20],[2,16],[0,14],[-14,5],[15,-11],[3,11],[11,-10],[-1,-7],[16,7],[1,-11],[-8,-3],[1,-6],[19,7],[3,6],[-1,-2],[7,-3],[-6,-8],[7,1],[-15,12],[-17,9],[19,-9],[1,0],[9,-10],[6,20],[-12,-4],[-16,-17],[14,3],[0,-1],[-18,9],[-15,15],[-3,-15],[-5,20],[15,-14],[9,-17],[10,-14],[-7,-11],[14,9],[1,-1],[15,12],[-5,-1],[-17,-5],[15,-2],[-12,11],[19,-18],[8,7],[-5,-3],[-17,-1],[-18,13],[15,-3],[4,18],[-14,-15],[15,8],[-18,-12],[-15,19],[-9,16],[-9,14],[-12,-14],[-2,-20],[-3,-13],[10,-7],[-2,-10],[9,10],[-1,7],[-17,-6],[-15,20],[5,-17],[6,-6],[-11,-8]]");
        Utils.printMatrix(matrix);


        int ans = new Solution().maxPoints(matrix);
        System.out.println(ans);

    }
}
