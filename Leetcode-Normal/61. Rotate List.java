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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null)
            return head;
        
        ListNode temp = head;
        ListNode tail = null;
        
        int len = 1;
        while(temp.next != null){
            temp = temp.next;
            len++;
        }
        tail = temp; // store the tail.
        
        k = k%len;
        
        temp = head;
        for(int i=0; i<(len - k -1); i++){
            temp = temp.next;
        }
        
        
        
        tail.next = head;
        
        head = temp.next;
        
        temp.next = null;
        
        return head;
    }
}