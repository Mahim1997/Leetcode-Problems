
public class NetBeansProj {

    public static void main(String[] args) {
        String s = "[[1,3],[2,6],[5,14],[12,18]]";
        int[][] mat = Utils.generateMatrixFromStringLeetcodeStringInput(s);
//        Utils.printMatrixInteger(mat);
        System.out.println("----------------------------------");
        int[][] merge = new Solution().merge(mat);
        System.out.println("===============================");
        Utils.printMatrixInteger(merge);

//        LRUCache_Problem146();
    }

    private static void LRUCache_Problem146() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4

    }
}
