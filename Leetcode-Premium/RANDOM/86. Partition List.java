/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    private static int NULL_VALUE = -1000;
    
    public ListNode partition(ListNode head, int x) {        
        ListNode lessThanNode = new ListNode(NULL_VALUE);
        ListNode greaterOrEqualToNode = new ListNode(NULL_VALUE);
        
        ListNode dummy1 = lessThanNode;
        ListNode dummy2 = greaterOrEqualToNode;
        
        ListNode itr = head;
        
        // Form links
        while(itr != null) {
            // System.out.println("itr.val = " + itr.val);
            if(itr.val < x) {
                // System.out.println("Case 1");
                lessThanNode.next = itr;
                lessThanNode = lessThanNode.next;
            }
            else {
                // System.out.println("Case 2");
                greaterOrEqualToNode.next = itr;
                greaterOrEqualToNode = greaterOrEqualToNode.next;
            }
            itr = itr.next;
        }
        
        // Forcefully set to null
        lessThanNode.next = null;
        greaterOrEqualToNode.next = null;
        
        // Adjust two links
        lessThanNode.next = dummy2.next;
        
        return dummy1.next;
    }
}