// Definition for singly-linked list.

class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {

    private ListNode swapByValue(ListNode head) {

        return null;
    }

    private ListNode swapByReference(ListNode head) {
        ListNode curr_node = head;
        ListNode dummyHead = new ListNode(-7777, head); // dummy head

        ListNode p1 = head;
        ListNode p2 = dummyHead; // store twice before.
        int numNodesVisited = 0;

        while (curr_node != null) {
            numNodesVisited++; // increment first time.

            if ((numNodesVisited % 2) != 0) { // is odd times movement.
                p1 = curr_node; // save the odd times node.
                curr_node = curr_node.next; // increment pointer/reference.
            } else { // swap here. [even #visits]
                ListNode current_next = curr_node.next; // can be null

                if (current_next != null) {
                    System.out.println("\nBefore swap: p2.val = " + p2.val + " , p1.val = " + p1.val + " , curr.val = " + curr_node.val + " , cNext.val = " + current_next.val);
                }

                p2.next = curr_node; // swapping logic
                p1.next = current_next; // swapping logic
                curr_node.next = p1; // swapping logic

                curr_node = current_next; // go to this node.

//                if (curr_node != null) {
//                    System.out.println("AFTER SWAP: p2.val = " + p2.val + " , p1.val = " + p1.val + " , curr.val = " + curr_node.val + " , cNext.val = " + current_next.val);
//                    System.out.println("p1.next.val = " + p1.next.val + " p2.val = " + p2.val + " , p2.next.val = " + p2.next.val);
//                    this.printList(dummyHead);
//                }
                p2 = p1; // store value
            }

        }

        return dummyHead.next;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return head; // returns null.
        }
        return swapByReference(head); // swaps by value.
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] values = {}; //{1, 2, 3, 4, 5, 6, 7};
        ListNode head = sol.setupValues(values);
        sol.printList(head);

        head = sol.swapPairs(head);
        sol.printList(head);
    }

    private void printList(ListNode head) {
        ListNode itr = head;
        while (itr != null) {
            System.out.print(itr.val + " ");
            itr = itr.next;
        }
        System.out.println("");
    }

    private ListNode setupValues(int[] values) {
        ListNode dummy_head = new ListNode();

        ListNode itr = dummy_head;
        for (int i = 0; i < values.length; i++) {
//            if(i==0) itr.val = values[i]; // only for 1st el. [not needed]
            ListNode node = new ListNode(values[i]);
            itr.next = node;

            itr = itr.next; // move one step next.
        }

        return dummy_head.next;
    }

}
