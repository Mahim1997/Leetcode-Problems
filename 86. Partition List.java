
class Solution {

    private void printNode(ListNode head) {
        ListNode temp = head;
        while (temp.next != null) {
            System.out.print(temp.val + ", ");
        }
        System.out.println("");
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode leftListNode = new ListNode(-1);
        ListNode rightListNode = new ListNode(-2);

        // smaller than in t1
        ListNode temp = head;

        ListNode leftHead = leftListNode;
        ListNode rightHead = rightListNode;

        // one pass
        while (temp != null) {
            if (temp.val < x) { // < x condition
                leftListNode.next = new ListNode(temp.val);
                leftListNode = leftListNode.next;
            } else { // >= x condition
                rightListNode.next = new ListNode(temp.val);
                rightListNode = rightListNode.next;
            }
            temp = temp.next;
        }

        leftHead = leftHead.next;
        rightHead = rightHead.next;

        if (leftHead == null) { // first list i.e. < x doesn't exist.
            return rightHead;
        }

        leftListNode.next = rightHead;

        return leftHead;
    }
}
