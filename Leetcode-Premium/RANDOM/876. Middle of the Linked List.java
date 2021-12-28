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
    public ListNode middleNode(ListNode head) {
        if(head == null)
            return null;
        
        // return naiveMethod(head);
        return doubleSpeedMethod(head);
    }
    
    private ListNode doubleSpeedMethod(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        
        if(head.next == null) // only one element.
            return head;
        
        while((fast != null) && (fast.next != null)){
            // go at one speed
            slow = slow.next;
            
            // go at double speed
            fast = fast.next;
            fast = fast.next;
        }
        return slow;
    }
    
    private ListNode naiveMethod(ListNode head){
        int numItems = 0;
        ListNode temp = head;
        while(temp != null){
            numItems++;
            temp = temp.next;
        }
        
        if(numItems%2 == 0){
            numItems = (numItems/2);
        }else{
            numItems = (numItems/2);
        }
        
        temp = head;
        for(int i=0; i<numItems; i++){
            temp = temp.next;
        }
        
        return temp;
    }
}