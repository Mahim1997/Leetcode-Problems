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
    private ListNode merge(ListNode node1, ListNode node2){
        // return back to node1 ... don't create extra nodes
        ListNode dummy = new ListNode(-100000);
        
        ListNode temp = dummy;
        
        while((node1 != null) && (node2 != null)){
            if(node1.val < node2.val){
                temp.next = node1;
                node1 = node1.next;
            }else{
                temp.next = node2;
                node2 = node2.next;
            }
            
            temp = temp.next;
        }
        
        if(node1 == null)
            temp.next = node2;
        else if(node2 == null)
            temp.next = node1;
        
        return dummy.next;
    }
    
    // Slow-Fast pointer approach O(log n)
    private ListNode getMiddleNode(ListNode head){
        ListNode slow = head, fast = head;
        ListNode slowPrevious = slow;
        
        while((fast != null) && (fast.next != null)){       
            // move slow by 1 step
            slowPrevious = slow;
            slow = slow.next;
            fast = fast.next.next;  // move fast by 2 steps
        }
        
        // set as null [to remove left part of middle]
        slowPrevious.next = null;
        
        return slow;
    }

    
    public ListNode sortList(ListNode head) {
        // edge cases
        if((head == null) || (head.next == null))
            return head;
        
        // get middle node
        ListNode middleNode = getMiddleNode(head);
        
        // recursive
        ListNode leftHead = sortList(head);
        ListNode rightHead = sortList(middleNode);
        
        // merge
        return merge(leftHead, rightHead);
    }
}