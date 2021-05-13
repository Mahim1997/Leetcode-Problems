
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {

    private int[] mergeTwoIntervals(int[] arr1, int[] arr2) {
        return new int[]{Math.min(arr1[0], arr2[0]),
            Math.max(arr1[1], arr2[1])};
    }

    private boolean doesTwoIntervalsCoincide(int[] arr1, int[] arr2) {
        // if first interval's end time lies after second's start time then coincides.
        return (arr1[1] > arr2[0]);
    }

    public int[][] merge(int[][] intervals) {

        if (intervals.length == 1) {
            return intervals;
        }

        // Sort wrt end times.
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                // wrt end times.
                return p1[1] - p2[1];
            }

        });
        System.out.println("AFTER SORTING:");
        Utils.printMatrixInteger(intervals);
        System.out.println("---------------------");

        Queue<int[]> queue = new LinkedList<>();

        // Merge two at a time.
        int leftIdx = 0;
        int secondIdx = 1;

        queue.add(intervals[0]);

        List<int[]> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            int[] headQueueInterval = queue.remove();

            // end-point is reached.
            if (leftIdx == intervals.length - 1) {
                break;
            }

            // second interval checking.
            boolean willMerge = false;
            for (secondIdx = leftIdx + 1; secondIdx < intervals.length; secondIdx++) {
                if (doesTwoIntervalsCoincide(headQueueInterval, intervals[secondIdx])) {
                    int[] newInterval = mergeTwoIntervals(headQueueInterval, intervals[secondIdx]);
                    System.out.println("New interval is " + Arrays.toString(newInterval));
                    queue.add(newInterval);
                    willMerge = true;
                } else {
                    System.out.println("Incrementing leftIdx from leftIdx = " + leftIdx + " to " + (leftIdx + 1));
                    leftIdx++;
                    if (willMerge) {
                        list.add(queue.peek());
                    } else {
                        list.add(intervals[leftIdx]);
                    }
                    break;
                }
            }

//            // afterwards
//            leftIdx++;
//            queue.add(intervals[leftIdx]);
        }
//        Utils.printQueueIntegerArray(queue);
        
        // last index checking.
        if(!doesTwoIntervalsCoincide(list.get(list.size() - 1), intervals[intervals.length - 1])){
            list.add(intervals[intervals.length - 1]);
        }else{
            int[] mergedInterval = mergeTwoIntervals(list.get(list.size() - 1), intervals[intervals.length - 1]);
            list.remove(list.size() - 1);
            list.add(mergedInterval);
        }

        System.out.println(list.stream()
                .map(x -> "(" + x[0] + "," + x[1] + ")")
                .collect(Collectors.joining(","))
        );
        return intervals;

    }
}
