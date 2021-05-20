
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

    public static int[] generateArrayFromString(String s) {
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
                .map(Utils::generateArrayFromString)
                .collect(Collectors.toList())
                .toArray(new int[0][0]);
    }

    public static void printQueueIntegerArray(Queue<int[]> queue) {
        System.out.println(queue.stream()
                .map(x -> String.valueOf("(" + x[0] + "," + x[1] + ")"))
                .collect(Collectors.joining(", ")));
    }

    public static void printListNode(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val);
            if(temp.next != null)
                System.out.print(", ");
            
            temp = temp.next;
        }
        System.out.println("");
    }

    public static ListNode getListNodeFromString(String s) {
        int[] arr = Utils.generateArrayFromString(s);

        ListNode head = null;
        ListNode temp = null;

        for (int i = 0; i < arr.length; i++) {
//            System.out.println("Doing for i = " + i + " , arr[i] = " + arr[i]);
            if (i == 0) {
                head = new ListNode(arr[i]);
                temp = head;
            } else {
                temp.next = new ListNode(arr[i]);
                temp = temp.next;
            }
        }
        

        // one dummy node.
        return head;
    }

}
