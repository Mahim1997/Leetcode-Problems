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
    public ListNode deleteDuplicates(ListNode head) {
        // edge cases: len == 0 or 1
        if((head == null) || (head.next == null))
            return head;
        
        int DUMMY_VALUE = -1000000; // outside real value range
        ListNode dummy = new ListNode(DUMMY_VALUE, head);
        
        ListNode itr1 = dummy;
        while(itr1 != null){
            ListNode itr2 = itr1.next;
            // System.out.println("itr2.val = " + itr2.val);
            // Cases 1 & 2
            if((itr2 == null) || (itr2.next == null)){break;}
            
            boolean isUnique = true;
            
            // move the second pointer
            while((itr2.next != null) && (itr2.val == itr2.next.val)){
                itr2 = itr2.next;
                isUnique = false;
            }
            
            // adjust links accordingly
            if(isUnique){
                itr1.next = itr2;
                
                // move itr1 to itr1.next for next iteration
                itr1 = itr1.next;
            }else{
                // itr1 has already moved to correct position
                itr1.next = itr2.next;
            }
            
        }
        
        return dummy.next;
    }
}