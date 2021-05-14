public class NetBeansProj {

    public static void main(String[] args) {
//        String s = "[[1,3],[2,6],[5,14],[12,18]]";
//        int[][] mat = Utils.generateMatrixFromStringLeetcodeStringInput(s);
////        Utils.printMatrixInteger(mat);
//        System.out.println("----------------------------------");
//        int[][] merge = new Solution().merge(mat);
//        System.out.println("===============================");
//        Utils.printMatrixInteger(merge);

        String s = "[1,2,3]";
        int[] arr = Utils.generateArrayFromString(s);
        int x = 1;
        ListNode head = Utils.getListNodeFromString(s);
        Utils.printListNode(head);
        
        ListNode partition = new Solution().partition(head, x);
        System.out.println("ANSWER");
        Utils.printListNode(partition);
    }
    
}
