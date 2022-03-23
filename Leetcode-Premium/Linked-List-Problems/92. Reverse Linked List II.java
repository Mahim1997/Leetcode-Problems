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
    public ListNode reverseBetween(ListNode head, 
                                   int left, int right) {
        // edge cases
        if((head == null) 
           || (head.next == null) 
           || (left == right)){return head;}
        
        // previous of reversal start position
        ListNode dummy = new ListNode(-1, head);
        
        ListNode previousOfReverse = dummy;
        for(int i=1; i<=(left - 1); i++){
            previousOfReverse = previousOfReverse.next;
        }
        
        int count = (right - left + 1);
        
        ListNode itr = previousOfReverse.next;
        
        ListNode headSoFar = itr;
        ListNode reversalHead = itr;
        ListNode next = null;
        
        while(count > 0){
            next = itr.next;
            itr.next = headSoFar;
            
            headSoFar = itr;
            itr = next;
            
            count--;
        }
        
        reversalHead.next = next;
        previousOfReverse.next = headSoFar;
        
        return dummy.next;
    }
}