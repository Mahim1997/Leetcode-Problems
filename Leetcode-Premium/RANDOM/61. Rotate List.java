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
        if(head == null){ // null check
            return head;
        }
        
        ListNode tail = head;
        // int len = getLengthOfList(head, tail);
        int len = 1;
        while(tail.next != null){ // retrieve length and last node
            len++;
            tail = tail.next;
        }
        if(len == 1){ // one element check
            return head;
        }
        
        k = k%len;
        if(k == 0){ // no rotations needed check
            return head;
        }
        
        ListNode newHead = head;
        int num = len - k - 1;
        for(int i=0; i<num; i++){
            newHead = newHead.next;
        }
        ListNode newNext = newHead.next;
        tail.next = head;
        newHead.next = null;
        
        return newNext;
    }
    
}