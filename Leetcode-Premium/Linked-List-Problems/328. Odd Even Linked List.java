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
    public ListNode oddEvenList(ListNode head) {
        // edge cases: 0 OR 1 nodes
        if((head == null) || (head.next == null))
            return head;
        
        
        ListNode oddHead = new ListNode(-1); // dummy node
        ListNode evenHead = new ListNode(-1);  // 2nd node
        
        ListNode oddItr = oddHead, evenItr = evenHead;
        
        ListNode itr = head;  // 3rd node
        int count = 1;
        
        while(itr != null){
            if(count%2 != 0){   // odd
                oddItr.next = itr;
                oddItr = oddItr.next;
            } 
            else{   // even
                evenItr.next = itr;
                evenItr = evenItr.next;
            }
            
            itr = itr.next;
            count++;
        }
        
        // just put oddHead.next = start of evenHead
        oddItr.next = evenHead.next; // due to dummy
        evenItr.next = null;
        
        return oddHead.next; // INITIAL oddHead == head would be the answer
    }
}