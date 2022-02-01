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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // ASSUMING sz >= n (Given in the question)
        
        // dummy node
        ListNode toDeletePrevious = new ListNode(-1, head);
        ListNode tail = head;
        
        // Two pointers
        // Move them at a distance of 'n+1'
        for(int i=0; i<n; i++){
            tail = tail.next;
        }
        
        // if this is the tail node, special case, remove 'head' node
        if(tail == null){
            head = head.next;
            return head;
        }
        
        // normal case
        while(tail != null){
            tail = tail.next;
            toDeletePrevious = toDeletePrevious.next;
        }
        
        // remove the toDeletePrevious.next node
        ListNode toDeleteNode = toDeletePrevious.next;
        toDeletePrevious.next = toDeleteNode.next;
        
        
        return head;
    }
}