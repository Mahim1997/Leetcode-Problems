
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class Utils {

    public static void printArray(int[] arr) {
        System.out.println(Arrays.stream(arr).
                mapToObj(String::valueOf).
                collect(Collectors.joining(", ")));
    }

    public static void printSingleList(List<Integer> list) {
        System.out.println(list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
    }

    public static void printMultipleList(List<List<Integer>> ans) {
        System.out.println(
                ans.stream()
                        .map(single_list
                                -> single_list.stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining(", "))
                        )
                        .collect(Collectors.joining("\n"))
        );

    }

    public static void printMatrixInteger(int[][] matrix) {
        System.out.println(
                Arrays.stream(matrix)
                        .map(single_row
                                -> Arrays.stream(single_row)
                                .mapToObj(String::valueOf)
                                .collect(Collectors.joining(", "))
                        )
                        .collect(Collectors.joining("\n"))
        );
    }

    private static void printMatrixCharacter(char[][] matrix) {
        for (char[] arr : matrix) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[j] + " ");
            }
            System.out.println();
        }
    }

    private static int[] generateArrayFromStr(String s) {
        if (s == null) {
            return null;
        } else if (s.trim().equals("")) {
            return new int[]{};
        }

        return Arrays.stream(s.trim()
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .split(","))
                .mapToInt(Integer::valueOf)
                .toArray();
    }

    public static int[][] generateMatrixFromStringLeetcodeStringInput(String s) {
        if (s == null) {
            return null;
        } else if (s.trim().equals("")) {
            return new int[][]{};
        }

        return Arrays.stream(s.trim()
                .replace(" ", "")
                .split("],\\["))
                .map(Utils::generateArrayFromStr)
                .collect(Collectors.toList())
                .toArray(new int[0][0]);
    }

    public static void printQueueIntegerArray(Queue<int[]> queue) {
        System.out.println(queue.stream()
                .map(x -> String.valueOf("(" + x[0] + "," + x[1] + ")"))
                .collect(Collectors.joining(", ")));
    }

}
