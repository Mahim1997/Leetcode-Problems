import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {

    public static void printArray(int []arr){
        System.out.println(Arrays.stream(arr).
                mapToObj(String::valueOf).
                collect(Collectors.joining(", ")));
    }

    public static void printSingleList(List<Integer> list) {
        System.out.println(list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
    }
    public static void printMultipleList(List<List<Integer>> ans){
        System.out.println(
            ans.stream()
                .map(single_list ->
                        single_list.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "))
                )
                .collect(Collectors.joining("\n"))
        );

    }


    private static String getKeysWithSpecifiedValue(Map<Integer, Integer> map, int val){
        return map.keySet()
                .stream()
                .filter(t -> {return map.get(t) == val;})
                .map(x -> String.valueOf(x))
                .collect(Collectors.joining(", "));
    }
    public static void printMap(Map<Integer, Integer> map, int left_partition, int right_partition){
        System.out.print("LEFT:  ");
        System.out.println(getKeysWithSpecifiedValue(map, left_partition));

        System.out.print("RIGHT: ");
        System.out.println(getKeysWithSpecifiedValue(map, right_partition));
    }

    public static void printMatrix(int[][] matrix) {
        System.out.println(
            Arrays.stream(matrix)
                    .map(single_row ->
                            Arrays.stream(single_row)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(", "))
                    )
                .collect(Collectors.joining("\n"))
        );
    }
    public static void printMatrixCharacter(char[][] matrix){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static String getMatrixRepresentation(String s) {
        s = s.replace("[", "{").replace("]", "}").replace("\"", "\'");
        s = "char[][] matrix = " + s + ";";
        return s;
    }

    public static int[][] get2DMatrixSequential(int row, int col) {
        int[][] matrix = new int[row][col];
        int element = 10;
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
               matrix[i][j] = (++element);
            }
        }
        return matrix;
    }
    public static String getMatrixLeetcodeForm(int[][] matrix){
        return Arrays.stream(matrix)
                .map(single_arr -> Arrays.stream(single_arr)
                        .mapToObj(x -> String.valueOf(x))
                        .collect(Collectors.joining(",", "[", "]"))
                ).collect(Collectors.joining(",", "[", "]"));
    }
}
















