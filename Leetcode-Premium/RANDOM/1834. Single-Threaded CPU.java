class Task {
    int index;
    int startTime;
    int duration;

    public Task(int i, int start, int dur) {
        this.index = i;
        this.startTime = start;
        this.duration = dur;
    }

    @Override
    public String toString() {
        return "[" + this.index + "," + this.startTime + "," + this.duration + "]";
    }
}

/*
    Maintain a treeSet of Task sorted by duration ascending order.
    
    0. Maintain a current_time
    1. Sort all tasks by enqueue time
    2. Keep on processing sorted tasks one by one, and add them to a min heap/treeSet based on duration (shortest job first)
        2 i) If heap/set is empty, process all jobs with enqueue time <= current_time
        2 ii) Else: Update current_time, update order, pop from minHeap 

*/

class Solution {
    public int[] getOrder(int[][] tasksArr) {
        int n = tasksArr.length;
        Task[] tasks = new Task[n];

        for(int i=0; i<n; i++) {
            tasks[i] = new Task(i, tasksArr[i][0], tasksArr[i][1]);
        }

        // Sort by enqueue time [serial time stamps]
        Arrays.sort(
            tasks, 
            (t1, t2) -> (
                (t1.startTime != t2.startTime) ?
                    (t1.startTime - t2.startTime) :
                    (t1.duration - t2.duration)
            )
        );

        // System.out.println("[SORTED] jobs: " + Arrays.toString(tasks));

        // Sort by indices if durations create problems
        PriorityQueue<Task> pq = new PriorityQueue<>(
            (t1, t2) -> 
            (
                (t1.duration != t2.duration) ?
                    (t1.duration - t2.duration) :
                    (t1.index - t2.index)
            )
        );

        int taskIndex = 0;
        int currentTime = tasks[0].startTime;

        int orderIndex = 0;
        int[] order = new int[n];

        while((taskIndex < n) || (orderIndex < n)) {
            // System.out.println("[OUTSIDE] taskIndex = " + taskIndex + ", pq: " + pq + ", currentTime: " + currentTime);

            if(!pq.isEmpty()) {
                Task taskRemoved = pq.remove();
                currentTime += taskRemoved.duration;
                // System.out.println("[FRONT_SIDE] adding index: " + taskRemoved.index + ", currentTime: " + currentTime);
                order[orderIndex++] = taskRemoved.index;
            }

            // Add all tasks that have enqueue time <= currentTime
            while(taskIndex < n) {
                Task taskToAdd = tasks[taskIndex];
                // System.out.println("[INSIDE] taskIndex = " + taskIndex + ", taskToAdd = " + taskToAdd + ", currentTime: " + currentTime);
                if(taskToAdd.startTime <= currentTime) {
                    // System.out.println("[INSIDE] adding task to pq: " + taskToAdd);
                    pq.add(taskToAdd);
                    taskIndex++;
                } 
                else {
                    if(pq.isEmpty()) {
                        currentTime = taskToAdd.startTime;
                    }
                    break;
                }
            }
        }

        return order;
    }
}