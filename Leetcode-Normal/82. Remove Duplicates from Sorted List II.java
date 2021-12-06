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
        if(head == null || head.next == null)
            return head;
        
        
        ListNode dummy = new ListNode(-101);
        dummy.next = head;
        ListNode temp = dummy.next;
        
        boolean removeNode = false;
        ListNode prev = dummy;
        
        // delete korle, then prev same jaga tei thakbe
        // delete na korle, prev move korbe

        while(temp.next != null){
            if(temp.val == temp.next.val){
                removeNode = true;
                temp.next = temp.next.next;
            }
            else{
                if(removeNode){
                    prev.next = temp.next;
                    removeNode = false;
                }else{
                    prev = temp;
                }
                
                temp = temp.next;
            }
        }
        
        // extra removal.
        if(removeNode){
            if(prev.next != null){
                prev.next = prev.next.next;
            }
        }
        
        return dummy.next;
    }
}