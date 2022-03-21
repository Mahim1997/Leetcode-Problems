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
    public ListNode removeElements(ListNode head, int val) {
        // edge case: no nodes
        // if(head == null){return null;}
        
        // sentinel node
        ListNode dummy = new ListNode(-1, head); 
        ListNode itr = dummy;
        
        while(itr.next != null){
            ListNode nextItr = itr.next;
            if(nextItr.val == val)
                itr.next = nextItr.next; // change links
            else
                itr = itr.next;
        }
        
        
        return dummy.next;
    }
}