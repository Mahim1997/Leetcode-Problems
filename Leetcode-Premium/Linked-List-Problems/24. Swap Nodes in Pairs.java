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
    
    // Using recursion
    public ListNode swapPairs(ListNode head) {
        
        // num nodes = 0
        if(head == null)
            return head;
        
        // dummy node
        ListNode dummy = new ListNode(-1, head);
        ListNode temp = dummy;
        
        recurse(temp);
        
        return dummy.next;
    }
    
    
    private void recurse(ListNode prevNode){
        // edge cases, when to return
        ListNode currentNode = prevNode.next;
        if(currentNode == null)
            return;
        ListNode nextNode = currentNode.next;     
        if(nextNode == null)
            return;
        
        // do work + call recursion
        ListNode temp = nextNode.next;
        
        prevNode.next = nextNode;
        currentNode.next = temp;
        nextNode.next = currentNode;
        
        recurse(currentNode);
    }

}