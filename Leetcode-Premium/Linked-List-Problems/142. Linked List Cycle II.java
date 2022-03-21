/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    private ListNode getCommonNode(ListNode head){
        ListNode slow = head, fast = head;
        
        while((fast != null) && (fast.next != null) && (slow != null)){
            slow = slow.next;       // single speed
            fast = fast.next.next;  // double speed
            
            if(slow == fast)
                return slow;
        }
        
        return null;
    }
    
    private ListNode getFirstNode(ListNode head, ListNode commonNode){
        ListNode slow1 = head, slow2 = commonNode;
        while(slow1 != slow2){
            slow1 = slow1.next;
            slow2 = slow2.next;
        }
        return slow1;
    }
    
    public ListNode detectCycle(ListNode head) {
        // edge cases
        if(head == null)
            return null;
        if(head.next == head) // cycle self
            return head;
        
        // First get common node of cycle [null if no cycle]
        ListNode commonNode = getCommonNode(head);
        if(commonNode == null)
            return null; // no cycle exists
        
        // Then start from head, and from that common node
        return getFirstNode(head, commonNode);        
    }
}



